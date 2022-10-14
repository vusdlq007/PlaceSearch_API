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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.ArrayList;
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
     * @param sort
     * @return
     * @throws SSLException
     */
    public Mono<KaKaoSearchResponseDTO> callKaKaoSearchPlaceApi(String keyword, String sort) throws SSLException {

        WebClient client = webClient.getWebClient(KaKaoUrlCode.KAKAO_BASE.label(), false);


        return client.mutate()
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path(KaKaoUrlCode.KAKAO_LOCAL_JSON.label())
                                .queryParam("query", keyword)
                                .queryParam("size", 10)
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
     * @param sort
     * @return
     * @throws SSLException
     */
    public Mono<NaverSearchResponseDTO> callNaverSearchPlaceApi(String keyword, String sort) throws SSLException {

        WebClient client = webClient.getWebClient(NaverUrlCode.NAVER_BASE.label(), false);

        return client.mutate()
                .build()
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path(NaverUrlCode.NAVER_LOCAL_JSON.label())
                                .queryParam("query", keyword)
                                .queryParam("display", 10)
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
    public SearchResponseDTO searchAggregation(KaKaoSearchResponseDTO kakaoResult, NaverSearchResponseDTO naverResult, String word, Integer size, Integer page) {

        ArrayList<PlaceDTO> places = new ArrayList();
        int kakaoCnt = kakaoResult.getDocuments().size();
        int naverCnt = naverResult.getDisplay();
        int totalCnt = 0;
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
                    // 둘다 존재 하거나 카카오에만 존재함. (카카오를 우선으로 셋팅.)
                    KaKaoSearchResponseDTO.Document item = kakaoResultMap.get(keyword);
                    PlaceDTO place = new PlaceDTO.Builder(item.getPlaceName(), item.getAddressName(), item.getRoadAddressName())
                            .phone(item.getPhone())
                            .category(item.getCategoryName())
                            .build();
                    // 우선 정렬 배열에 담음.
                    priorityItem.add(place);
                }

                //더 적게 나온 네이버 검색결과 갯수만큼 키 존재여부를 보고 없으면 후선 list에 추가해둠.(카카오 검색결과 우선적용 & 최대한 적은 시간복잡도를 가지기 위한 로직.)
                for (String keyword : naverResultMap.keySet()) {
                    // 네이버만 존재함. 특수문자,공백을 제거한 장소명 비교.
                    if (!kakaoResultMap.containsKey(keyword)) {
                        NaverSearchResponseDTO.Items naverItem = naverResultMap.get(keyword);
                        // 후선 정렬 배열에 담음.
                        latterItem.add(new PlaceDTO.Builder(naverItem.getTitle(), naverItem.getAddress(), naverItem.getRoadAddress())
                                .phone(naverItem.getTelephone())
                                .category(naverItem.getCategory())
                                .build());
                    }
                }

                break;

            // 네이버가 더 많은 검색결과가 나왔을때.
            case "naver":

                //더 적게 나온 카카오 검색결과 갯수만큼 키 존재여부를 보고 없으면 우선 list에 추가해둠.(카카오 검색결과 우선적용 & 최대한 적은 시간복잡도를 가지기 위한 로직.)
                for (String keyword : kakaoResultMap.keySet()) {
                    // 카카오만 존재함. 특수문자,공백을 제거한 장소명 비교.
                    if (!naverResultMap.containsKey(keyword)) {
                        KaKaoSearchResponseDTO.Document item = kakaoResultMap.get(keyword);
                        // 우선 정렬 배열에 담음.
                        priorityItem.add(new PlaceDTO.Builder(item.getPlaceName(), item.getAddressName(), item.getRoadAddressName())
                                .phone(item.getPhone())
                                .category(item.getCategoryName())
                                .build());
                    }
                }

                for (String keyword : naverResultMap.keySet()) {
                    // 네이버만 존재함.
                    NaverSearchResponseDTO.Items naverItem = naverResultMap.get(keyword);
                    PlaceDTO naverPlace = new PlaceDTO.Builder(naverItem.getTitle(), naverItem.getAddress(), naverItem.getRoadAddress())
                            .phone(naverItem.getTelephone())
                            .category(naverItem.getCategory())
                            .build();
                    // 후선 정렬 배열에 담음.
                    latterItem.add(naverPlace);
                }
                break;

            default:
                log.error("ErrorCode:%d, ErrorMSG:%s", ResponseCode.PLACE_SEARCH_AGG_FAIL.getErrorCode(), ResponseCode.PLACE_SEARCH_AGG_FAIL.getMessage());
                break;

        }

        places.addAll(priorityItem);
        places.addAll(latterItem);


        //실제 중복제거된 검색 결과 건수.
        totalCnt = places.size();

        Pageable pageable = PageRequest.of(page, size);

        int len = places.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), len);

        // 페이징 처리. 최초 페이지는 0부터 시작.
        final Page<PlaceDTO> placesPage = new PageImpl<>(places.subList(start, end), pageable, len);


        //최종 응답값 셋팅.
        SearchResponseDTO responseDTO = new SearchResponseDTO.Builder(ResponseCode.PLACE_SEARCH_SUCCESS.getErrorCode(), ResponseCode.PLACE_SEARCH_SUCCESS.getMessage())
                .keyword(word)
                .displayCnt(size)
                .curPage(page)
                .totalCnt(totalCnt)
                .places((List<PlaceDTO>) placesPage.getContent())
                .build();


        return responseDTO;
    }


}
