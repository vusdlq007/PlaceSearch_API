package com.api.placesearch.api.svc;



import com.api.placesearch.api.dto.SearchInfoResponseDTO;
import com.api.placesearch.api.dto.SearchLogDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import org.springframework.data.domain.Pageable;


public interface SearchPlaceLogService {

    SearchResponseDTO searchLog(Pageable pageable);

    SearchResponseDTO putLog(SearchLogDTO searchResInfo);

}
