package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.response.SearchResponseDTO;


public interface RecomandService {

    SearchResponseDTO recomandSearchKeyword(String category, Integer disCnt);


}
