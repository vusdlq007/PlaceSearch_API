package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.repo.SearchLogRepository;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.SearchPlaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class SearchPlaceLogServiceImpl implements SearchPlaceLogService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    SearchLogRepository searchLogRepository;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public SearchPlaceLogServiceImpl(SearchLogRepository searchLogRepository){
        this.searchLogRepository = searchLogRepository;
    }


    @Override
    public SearchResponseDTO searchLog(Pageable pageable) {
        return null;
    }
}
