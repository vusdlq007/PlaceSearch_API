package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.response.KaKaoSearchResponseDTO;
import com.api.placesearch.api.dto.response.NaverSearchResponseDTO;
import com.api.placesearch.api.dto.response.SearchInfoResponseDTO;
import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.api.entity.Place;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.SearchPlaceService;


import com.api.placesearch.cmm.constant.ResponseCode;
import com.api.placesearch.cmm.util.SearchApiUtils;
import com.api.placesearch.cmm.util.WebclientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.util.Optional;


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


    /**
     * 키워드로 장소 검색.
     * @param keyword
     * @param size
     * @param page
     * @param sort
     * @return
     */
    @Override
    public SearchResponseDTO searchPlace(String keyword, Integer size, Integer page, String sort) {

        Mono<KaKaoSearchResponseDTO> kakaoResult = null;
        Mono<NaverSearchResponseDTO> naverResult = null;

        //Kakao API 호출.
        try {
            kakaoResult = searchApiUtils.callKaKaoSearchPlaceApi(keyword, sort);
            kakaoResult.subscribeOn(Schedulers.boundedElastic());
        } catch (Exception e) {
            log.error("ErrorCode:%d, ErrorMSG:%s",ResponseCode.KAKAO_PLACE_SEARCH_FAIL.getErrorCode(),ResponseCode.KAKAO_PLACE_SEARCH_FAIL.getMessage());
        }

        //Naver API 호출.
        try{
            naverResult = searchApiUtils.callNaverSearchPlaceApi(keyword, sort);
            naverResult.subscribeOn(Schedulers.boundedElastic());
        }catch (Exception e){
            log.error("ErrorCode:%d, ErrorMSG:%s",ResponseCode.NAVER_PLACE_SEARCH_FAIL.getErrorCode(),ResponseCode.NAVER_PLACE_SEARCH_FAIL.getMessage());
        }

        // 각 API 호출이 모두 완료되면 동기로 응답값 종합.
       Tuple2<KaKaoSearchResponseDTO, NaverSearchResponseDTO> tuple2 = Mono.zip(kakaoResult,naverResult).block();

       SearchResponseDTO response = searchApiUtils.searchAggregation(tuple2.getT1(),tuple2.getT2(), keyword, size == null ? 10 : size, page == null ? 0 : page);
        //1. NaverSearchResponseDTO 초기화.
        //2. subscribe로 받는법.
        //3. 호출 결과 종합.
        //4. DB에 로그 쌓는 AOP구현 (로그 테이블 + 조회수 테이블)
        //5. 가장많이 조회된 키워드 10개 조회.
        //5. Redis 연동 후 TOP 10 키워드 Redis에 1분에 한번씩 넣는 cronjob으로 넣고 요청은 읽어가도록 구현.
        //6. 테스트코드 작성.
        //7. 실패케이스 잡기.

        // 조회 수 +1
        saveSearchInfo(keyword);

         return response;
    }

    //더티체크 업데이트 구현.
    /**
     * 키워드 검색 수 업데이트.
     * @param word
     * @return
     */
    @Override
    @Transactional
    public SearchInfoResponseDTO saveSearchInfo(String word) {

        Optional<Place> keywordInfo = searchResultRepository.findByKeyword(word);

        // 최초 조회시 생성.
        if(!keywordInfo.isPresent()) {
            searchResultRepository.save(new Place(word,1,LocalDateTime.now()));

            return new SearchInfoResponseDTO.Builder(ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getErrorCode(), ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getMessage())
                    .keyword(word)
                    .totalViews(1)
                    .build();
        }

        Place keywordEntity = keywordInfo.get();

        Long seq = keywordEntity.getSeq();
        String keyword = keywordEntity.getKeyword();
        Integer views = keywordEntity.getViews() + 1;
        LocalDateTime createdAt = keywordEntity.getCreatedAt();

        keywordEntity.update(seq,keyword,views,createdAt);


        //최종 응답값 셋팅.
        return new SearchInfoResponseDTO.Builder(ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getErrorCode(), ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getMessage())
                .keyword(keyword)
                .totalViews(views)
                .build();
    }


}
