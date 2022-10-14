package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.SearchInfoResponseDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;

import javax.net.ssl.SSLException;


public interface SearchPlaceService {

    SearchResponseDTO searchPlace(String keyword, Integer size, Integer page, String sort) throws SSLException;

    SearchInfoResponseDTO saveSearchInfo(String keyword);

}
