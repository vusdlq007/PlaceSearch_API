package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.KaKaoSearchResponseDTO;
import com.api.placesearch.api.dto.NaverSearchResponseDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.SearchPlaceService;

import com.api.placesearch.cmm.constant.KaKaoUrlCode;
import com.api.placesearch.cmm.constant.ResponseCode;
import com.api.placesearch.cmm.util.SearchApiUtils;
import com.api.placesearch.cmm.util.WebclientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;


import javax.net.ssl.SSLException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@Transactional
public class SearchPlaceServiceImpl implements SearchPlaceService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Value("${custom.service.kakao.api-key}")
    String kakaoApiKey;

    @Value("${custom.service.naver.client-id}")
    String naverClientId;

    @Value("${custom.service.naver.client-secret}")
    String naverClientSecret;


    @Autowired
    WebclientUtil webClient;

    @Autowired
    SearchApiUtils searchApiUtils;

    @Autowired
    SearchResultRepository searchResultRepository;


    public SearchPlaceServiceImpl(SearchResultRepository searchResultRepository) {
        this.searchResultRepository = searchResultRepository;
    }


    @Override
    public SearchResponseDTO searchPlace(String keyword, Integer size, Integer page, String sort) throws SSLException {

        Mono<KaKaoSearchResponseDTO> kakaoResult = null;
        Mono<NaverSearchResponseDTO> naverResult = null;

        //Kakao API 호출.
        try {
            kakaoResult = searchApiUtils.callKaKaoSearchPlaceApi(keyword, size, page, sort);
            kakaoResult.subscribeOn(Schedulers.boundedElastic());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ErrorCode:%d, ErrorMSG:%s",ResponseCode.KAKAO_PLACE_SEARCH_FAIL.getErrorCode(),ResponseCode.KAKAO_PLACE_SEARCH_FAIL.getMessage());
        }

        //Naver API 호출.
        try{
            naverResult = searchApiUtils.callNaverSearchPlaceApi(keyword, size, sort);
            naverResult.subscribeOn(Schedulers.boundedElastic());
        }catch (Exception e){
            e.printStackTrace();
            log.error("ErrorCode:%d, ErrorMSG:%s",ResponseCode.NAVER_PLACE_SEARCH_FAIL.getErrorCode(),ResponseCode.NAVER_PLACE_SEARCH_FAIL.getMessage());
        }

        // 각 API 호출이 모두 완료되면 동기로 응답값 종합.
       Tuple2<KaKaoSearchResponseDTO, NaverSearchResponseDTO> tuple2 = Mono.zip(kakaoResult,naverResult).block();

       SearchResponseDTO response = searchApiUtils.searchAggregation(tuple2.getT1(),tuple2.getT2());
        //1. NaverSearchResponseDTO 초기화.
        //2. subscribe로 받는법.
        //3. 호출 결과 종합.
        //4. DB에 로그 쌓는 AOP구현 (로그 테이블 + 조회수 테이블)
        //5. Redis 연동 후 TOP 10 키워드 Redis에 1분에 한번씩 넣는 cronjob으로 넣고 요청은 읽어가도록 구현.


         return response;
    }





}
