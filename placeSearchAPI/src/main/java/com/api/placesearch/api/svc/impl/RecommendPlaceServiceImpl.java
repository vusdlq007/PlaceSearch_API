package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.RecommendKeywordDTO;

import com.api.placesearch.api.dto.response.KeywordRecommendResponseDTO;

import com.api.placesearch.api.entity.Place;

import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.RecommendService;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RecommendPlaceServiceImpl implements RecommendService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    SearchResultRepository searchResultRepository;


    public RecommendPlaceServiceImpl(SearchResultRepository searchResultRepository) {
        this.searchResultRepository = searchResultRepository;
    }

    /**
     * 가장 많이 검색된 순서로 상위 키워드 리턴.
     *
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public KeywordRecommendResponseDTO recomandSearchKeyword(Pageable pageable) {

        Page<Place> searchLogs = searchResultRepository.findByOrderByViewsDesc(pageable);
        List<Place> keywordInfoArr = searchLogs.getContent();
        List<RecommendKeywordDTO> keywordList = new ArrayList<>();

        for (Place keywordEntity : keywordInfoArr) {
            keywordList.add(RecommendKeywordDTO.builder()
                    .keyword(keywordEntity.getKeyword())
                    .totalViews(keywordEntity.getViews())
                    .createdAt(keywordEntity.getCreatedAt())
                    .build()
            );
        }

        return new KeywordRecommendResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_SUCCESS.getErrorCode(), ResponseCode.RECOMAND_SEARCH_SUCCESS.getMessage())
                .displayCnt(pageable.getPageSize())
                .curPage(pageable.getPageNumber())
                .totalCnt(keywordList.size())
                .recomandKeywords(keywordList)
                .build();

    }
}
