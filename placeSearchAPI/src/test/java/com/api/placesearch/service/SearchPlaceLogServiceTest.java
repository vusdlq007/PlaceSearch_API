package com.api.placesearch.service;



import com.api.placesearch.api.dto.SearchLogDTO;
import com.api.placesearch.api.dto.response.SearchLogResponseDTO;
import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.api.repo.SearchLogRepository;

import com.api.placesearch.api.svc.SearchPlaceLogService;

import com.api.placesearch.api.svc.impl.SearchPlaceLogServiceImpl;

import com.api.placesearch.cmm.constant.ResponseCode;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class SearchPlaceLogServiceTest {


    SearchPlaceLogService searchPlaceLogService;

    @Autowired
    SearchLogRepository searchLogRepository;

    @Before
    public void before(){
        this.searchPlaceLogService = new SearchPlaceLogServiceImpl(searchLogRepository);
    }

    @Test
    @DisplayName("로그 조회 성공")
    public void searchLogSuccess() throws Exception {

        SearchLogResponseDTO responseDTO = new SearchLogResponseDTO.Builder(ResponseCode.SEARCH_LOG_SUCCESS.getErrorCode(), ResponseCode.SEARCH_LOG_SUCCESS.getMessage())
                .keyword("제주도")
                .build();

        // when
        //then
        Assertions.assertThat(searchPlaceLogService.searchLog("제주도",PageRequest.of(1,1)).getResCode()).isEqualTo(responseDTO.getResCode());
    }


    @Test
    @DisplayName("로그 조회 실패, 잘못된요청")
    public void searchLogFail() throws Exception {


        SearchLogResponseDTO responseDTO = new SearchLogResponseDTO.Builder(ResponseCode.SEARCH_LOG_FAIL.getErrorCode(), ResponseCode.SEARCH_LOG_FAIL.getMessage())
                .keyword("czzㅇ")
                .build();

        // when
        //then
        Assertions.assertThat(searchPlaceLogService.searchLog("czzㅇ",PageRequest.of(9999,50)).getResCode()).isEqualTo(responseDTO.getResCode());
    }

    @Test
    @DisplayName("로그 저장 성공")
    public void searchPlaceSuccess() throws Exception {

        SearchLogDTO searchLog = new SearchLogDTO.Builder("카카오뱅크","window10")
                .macAdd("ACB-DDS-123-vvfd")
                .createdAt(LocalDateTime.now())
                .build();

        SearchResponseDTO responseDTO = new SearchResponseDTO.Builder(ResponseCode.SEARCH_LOG_UPDATE_SUCCESS.getErrorCode(), ResponseCode.SEARCH_LOG_UPDATE_SUCCESS.getMessage())
                .keyword("제주도")
                .curPage(1)
                .build();

        // when
        //then
        Assertions.assertThat(searchPlaceLogService.putLog(searchLog).getResCode()).isEqualTo(responseDTO.getResCode());
    }

}
