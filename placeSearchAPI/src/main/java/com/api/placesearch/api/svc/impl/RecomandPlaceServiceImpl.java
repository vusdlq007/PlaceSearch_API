package com.api.placesearch.api.svc.impl;

import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.RecomandService;
import com.api.placesearch.api.svc.SearchPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Transactional
public class RecomandPlaceServiceImpl implements RecomandService {

    @Value("${custom.service.timezone}")
    String timeZone;

    @Autowired
    SearchResultRepository searchResultRepository;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");


    public RecomandPlaceServiceImpl(SearchResultRepository searchResultRepository){
        this.searchResultRepository = searchResultRepository;
    }

    @Override
    public SearchResponseDTO recomandSearchKeyword(String category, Integer disCnt) {
        return null;
    }
}
