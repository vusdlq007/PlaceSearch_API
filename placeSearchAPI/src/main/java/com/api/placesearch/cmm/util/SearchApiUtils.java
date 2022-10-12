package com.api.placesearch.cmm.util;

import com.api.placesearch.api.dto.KaKaoSearchResponseDTO;
import com.api.placesearch.api.dto.NaverSearchResponseDTO;
import com.api.placesearch.api.dto.PlaceDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.cmm.constant.Constants;
import com.api.placesearch.cmm.constant.KaKaoUrlCode;
import com.api.placesearch.cmm.constant.NaverUrlCode;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class SearchApiUtils {

    @Value("${custom.service.kakao.api-key}")
    String kakaoApiKey;

    @Value("${custom.service.naver.client-id}")
    String naverClientId;

    @Value("${custom.service.naver.client-secret}")
    String naverClientSecret;


    @Autowired
    WebclientUtil webClient;


    /**
     * KAKAO 키워드로 장소 검색하기 API 호출. (Non-blocking)
     *
     * @param keyword
     * @param size
     * @param page
     * @param sort
     * @return
     * @throws SSLException
     */
    public Mono<KaKaoSearchResponseDTO> callKaKaoSearchPlaceApi(String keyword, Integer size, Integer page, String sort) throws SSLException {

        WebClient client = webClient.getWebClient(KaKaoUrlCode.KAKAO_BASE.label(), false);


        return client.mutate()
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path(KaKaoUrlCode.KAKAO_LOCAL_JSON.label())
                                .queryParam("query", keyword)
                                .queryParam("page", page == null ? 1 : page)
                                .queryParam("size", size == null ? 5 : size)
                                .queryParam("sort", sort == null ? "accuracy" : sort)
                                .build())
                .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(KaKaoSearchResponseDTO.class);
    }

    /**
     * NAVER 키워드로 장소 검색하기 API 호출. (Non-blocking)
     *
     * @param keyword
     * @param size
     * @param sort
     * @return
     * @throws SSLException
     */
    public Mono<NaverSearchResponseDTO> callNaverSearchPlaceApi(String keyword, Integer size, String sort) throws SSLException {

        WebClient client = webClient.getWebClient(NaverUrlCode.NAVER_BASE.label(), false);

        return client.mutate()
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path(NaverUrlCode.NAVER_LOCAL_JSON.label())
                                .queryParam("query", keyword)
                                .queryParam("display", size == null ? 3 : size)
                                .queryParam("sort", sort == null ? "random" : sort)
                                .build())
                .header(NaverUrlCode.NAVER_HEADER_CLIENT_ID.label(), naverClientId)
                .header(NaverUrlCode.NAVER_HEADER_CLIENT_SECRET.label(), naverClientSecret)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(NaverSearchResponseDTO.class);
    }

    /**
     * KaKao API, Naver API 검색 결과 정렬 후 결과 리턴.
     *
     * @param kakaoResult
     * @param naverResult
     * @return
     */
    public SearchResponseDTO searchAggregation(KaKaoSearchResponseDTO kakaoResult, NaverSearchResponseDTO naverResult) {

        ArrayList<PlaceDTO> places = new ArrayList();
        int kakaoCnt = kakaoResult.getDocuments().size();
        int naverCnt = naverResult.getDisplay();
        int totalCnt = kakaoCnt >= naverCnt ? kakaoCnt : naverCnt;
        String mostCntApi = kakaoCnt >= naverCnt ? Constants.KAKAO.label() : Constants.NAVER.label();
        ArrayList<PlaceDTO> priorityItem = new ArrayList();
        ArrayList<PlaceDTO> latterItem = new ArrayList();

        LinkedHashMap<String, KaKaoSearchResponseDTO.Document> kakaoResultMap = Util.kakaoArrayListToHashMap(kakaoResult);
        LinkedHashMap<String, NaverSearchResponseDTO.Items> naverResultMap = Util.naverArrayListToHashMap(naverResult);


        // 둘다 존재하는지 체크. (둘다 존재하면 맨 앞에 추가.)
        switch (mostCntApi) {

            // 카카오가 더 많은 검색결과가 나왔을때.
            case "kakao":

                for (String keyword : kakaoResultMap.keySet()) {
                    if (naverResultMap.containsKey(keyword)) {
                        // 둘다 존재함. (카카오를 우선으로 셋팅.)
                        KaKaoSearchResponseDTO.Document item = kakaoResultMap.get(keyword);
                        PlaceDTO place = new PlaceDTO.Builder(item.getPlaceName(), item.getAddressName(), item.getRoadAddressName())
                                .phone(item.getPhone())
                                .category(item.getCategoryName())
                                .build();
                        // 우선 정렬 배열에 담음.
                        priorityItem.add(place);
                    } else {
                        // 카카오만 존재함.
                        KaKaoSearchResponseDTO.Document item = kakaoResultMap.get(keyword);
                        PlaceDTO place = new PlaceDTO.Builder(item.getPlaceName(), item.getAddressName(), item.getRoadAddressName())
                                .phone(item.getPhone())
                                .category(item.getCategoryName())
                                .build();
                        // 후선 정렬 배열에 담음.
                        latterItem.add(place);
                    }
                }
                break;

            // 네이버가 더 많은 검색결과가 나왔을때.
            case "naver":

                for (String keyword : naverResultMap.keySet()) {
                    if (kakaoResultMap.containsKey(keyword)) {
                        // 둘다 존재함. (카카오를 우선으로 셋팅.)
                        KaKaoSearchResponseDTO.Document item = kakaoResultMap.get(keyword);
                        PlaceDTO place = new PlaceDTO.Builder(item.getPlaceName(), item.getAddressName(), item.getRoadAddressName())
                                .phone(item.getPhone())
                                .category(item.getCategoryName())
                                .build();
                        // 우선 정렬 배열에 담음.
                        priorityItem.add(place);
                    } else {
                        // 네이버만 존재함.
                        NaverSearchResponseDTO.Items item = naverResultMap.get(keyword);
                        PlaceDTO place = new PlaceDTO.Builder(Util.tagRemove(item.getTitle()), item.getAddress(), item.getRoadAddress())
                                .phone(item.getTelephone())
                                .category(item.getCategory())
                                .build();
                        // 후선 정렬 배열에 담음.
                        latterItem.add(place);
                    }
                }
                break;

            default:
                log.error("ErrorCode:%d, ErrorMSG:%s", ResponseCode.PLACE_SEARCH_AGG_FAIL.getErrorCode(), ResponseCode.PLACE_SEARCH_AGG_FAIL.getMessage());
                break;

        }

        places.addAll(priorityItem);
        places.addAll(latterItem);

        //최종 응답값 셋팅.
        SearchResponseDTO responseDTO = new SearchResponseDTO.Builder(ResponseCode.PLACE_SEARCH_SUCCESS.getErrorCode(),ResponseCode.PLACE_SEARCH_SUCCESS.getMessage())
                .displayCnt(totalCnt)
                .places(places)
                .build();



        return responseDTO;
    }


}
