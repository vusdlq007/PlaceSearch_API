package com.api.placesearch.api.svc;




import com.api.placesearch.api.dto.SearchLogDTO;
import com.api.placesearch.api.dto.response.SearchLogResponseDTO;
import com.api.placesearch.api.dto.response.SearchResponseDTO;
import org.springframework.data.domain.Pageable;


public interface SearchPlaceLogService {

    SearchLogResponseDTO searchLog(String Keyword, Pageable pageable);

    SearchResponseDTO putLog(SearchLogDTO searchResInfo);

}
