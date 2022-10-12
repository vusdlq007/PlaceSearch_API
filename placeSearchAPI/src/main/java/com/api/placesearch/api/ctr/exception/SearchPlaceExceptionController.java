package com.api.placesearch.api.ctr.exception;

import com.api.placesearch.api.ctr.SearchPlaceRestController;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateError;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice(basePackageClasses = SearchPlaceRestController.class)
@Slf4j
public class SearchPlaceExceptionController {


    @ExceptionHandler({
            JpaSystemException.class,
            HibernateError.class
    })
    protected ResponseEntity<?> handleDBException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.SEARCH_LOG_FAIL_DB_ERROR.getErrorCode(), ResponseCode.SEARCH_LOG_FAIL_DB_ERROR.getMessage()).build();

        return ResponseEntity.status(ResponseCode.SEARCH_LOG_FAIL_DB_ERROR.getErrorCode()).body(errorResponse);
    }

    // 500
    @ExceptionHandler()
    protected ResponseEntity<?> handleSearchPlaceException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.PLACE_SEARCH_FAIL.getErrorCode(), ResponseCode.PLACE_SEARCH_FAIL.getMessage()).build();

        e.printStackTrace();
        return ResponseEntity.status(ResponseCode.PLACE_SEARCH_FAIL.getErrorCode()).body(errorResponse);
    }


}
