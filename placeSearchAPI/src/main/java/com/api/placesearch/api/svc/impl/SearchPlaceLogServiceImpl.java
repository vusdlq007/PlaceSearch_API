package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.SearchInfoResponseDTO;
import com.api.placesearch.api.dto.SearchLogDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.entity.Place;
import com.api.placesearch.api.entity.SearchLog;
import com.api.placesearch.api.repo.SearchLogRepository;

import com.api.placesearch.api.svc.SearchPlaceLogService;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class SearchPlaceLogServiceImpl implements SearchPlaceLogService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    SearchLogRepository searchLogRepository;


    public SearchPlaceLogServiceImpl(SearchLogRepository searchLogRepository){
        this.searchLogRepository = searchLogRepository;
    }


    /**
     * 키워드 로그 조회.
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public SearchResponseDTO searchLog(Pageable pageable) {
        return null;
    }

    /**
     * 키워드 조회 로그 저장.
     * @param searchResInfo
     * @return
     */
    @Override
    @Transactional
    public SearchResponseDTO putLog(SearchLogDTO searchResInfo) {

        searchLogRepository.save(new SearchLog(searchResInfo.getMacAdd(), searchResInfo.getOs(), searchResInfo.getKeyword(), searchResInfo.getCreatedAd()));

        return new SearchResponseDTO.Builder(ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getErrorCode(), ResponseCode.SEARCH_INFO_UPDATE_SUCCESS.getMessage())
                .keyword(searchResInfo.getKeyword())
                .totalCnt(searchResInfo.getTotalViews())
                .build();

    }
}
