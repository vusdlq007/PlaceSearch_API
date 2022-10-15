package com.api.placesearch.api.ctr.exception;

import com.api.placesearch.aop.logging.SearchLogging;
import com.api.placesearch.api.ctr.RecommendRestController;
import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.cmm.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateError;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackageClasses = {SearchLogging.class, RecommendRestController.class })
@Slf4j
public class RecommendPlaceExceptionController {


    @ExceptionHandler({
            JpaSystemException.class,
            HibernateError.class
    })
    protected ResponseEntity<?> handleDBException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_DB_FAIL.getErrorCode(), ResponseCode.RECOMAND_SEARCH_DB_FAIL.getMessage()).build();

        return ResponseEntity.status(ResponseCode.RECOMAND_SEARCH_DB_FAIL.getErrorCode()).body(errorResponse);
    }


    @ExceptionHandler()
    protected ResponseEntity<?> handleSearchPlaceException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_FAIL.getErrorCode(), ResponseCode.RECOMAND_SEARCH_FAIL.getMessage()).build();

        return ResponseEntity.status(ResponseCode.RECOMAND_SEARCH_FAIL.getErrorCode()).body(errorResponse);
    }


    @ExceptionHandler({
            IllegalArgumentException.class
    })
    protected ResponseEntity<?> handleSearchPlaceIllegalArgException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_ILLEGAL_ARG_FAIL.getErrorCode(), ResponseCode.RECOMAND_SEARCH_ILLEGAL_ARG_FAIL.getMessage()).build();

        return ResponseEntity.status(ResponseCode.RECOMAND_SEARCH_ILLEGAL_ARG_FAIL.getErrorCode()).body(errorResponse);
    }

    @ExceptionHandler({
            NullPointerException.class
    })
    protected ResponseEntity<?> handleSearchPlaceNullPointException(Exception e) {
        final SearchResponseDTO errorResponse = new SearchResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_NULL_FAIL.getErrorCode(), ResponseCode.RECOMAND_SEARCH_NULL_FAIL.getMessage()).build();

        return ResponseEntity.status(ResponseCode.RECOMAND_SEARCH_NULL_FAIL.getErrorCode()).body(errorResponse);
    }


}
