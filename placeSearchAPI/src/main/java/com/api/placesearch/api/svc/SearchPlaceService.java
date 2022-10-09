package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;


public interface SearchPlaceService {

    SearchResponseDTO searchPlace(String keyword, Integer disCnt, String sort);

    SearchResponseDTO searchPlaceDetail(SearchRequestDTO requestDTO);


}
