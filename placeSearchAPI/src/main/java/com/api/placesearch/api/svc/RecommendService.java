package com.api.placesearch.api.svc;


import com.api.placesearch.api.dto.response.KeywordRecommendResponseDTO;
import org.springframework.data.domain.Pageable;


public interface RecommendService {

    KeywordRecommendResponseDTO recomandSearchKeyword(Pageable pageable);


}
