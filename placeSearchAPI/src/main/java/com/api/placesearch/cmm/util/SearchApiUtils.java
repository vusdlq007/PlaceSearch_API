package com.api.placesearch.cmm.util;

import com.api.placesearch.api.dto.KaKaoSearchResponseDTO;
import com.api.placesearch.api.dto.NaverSearchResponseDTO;
import com.api.placesearch.cmm.constant.KaKaoUrlCode;
import com.api.placesearch.cmm.constant.NaverUrlCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;

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
                                .queryParam("display", size == null ? 5 : size)
                                .queryParam("sort", sort == null ? "random" : sort)
                                .build())
                .header(NaverUrlCode.NAVER_HEADER_CLIENT_ID.label(), naverClientId)
                .header(NaverUrlCode.NAVER_HEADER_CLIENT_SECRET.label(), naverClientSecret)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(NaverSearchResponseDTO.class);
    }
}
