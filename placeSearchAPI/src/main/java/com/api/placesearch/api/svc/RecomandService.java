package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;


public interface RecomandService {

    SearchResponseDTO recomandSearchKeyword(String category, Integer disCnt);


}
