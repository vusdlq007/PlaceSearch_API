package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;

import javax.net.ssl.SSLException;


public interface SearchPlaceService {

    SearchResponseDTO searchPlace(String keyword, Integer size, Integer page, String sort) throws SSLException;

    SearchResponseDTO searchPlaceDetail(SearchRequestDTO requestDTO);


}
