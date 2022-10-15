package com.api.placesearch.service;



import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.SearchPlaceService;
import com.api.placesearch.api.svc.impl.SearchPlaceServiceImpl;
import com.api.placesearch.cmm.constant.ResponseCode;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class SearchPlaceServiceTest {


    SearchPlaceService searchPlaceService;

    @Autowired
    SearchResultRepository searchResultRepository;

    @Before
    public void before(){
        this.searchPlaceService = new SearchPlaceServiceImpl(searchResultRepository);
    }

    @Test
    @DisplayName("장소 검색 성공")
    public void searchPlaceSuccess() throws Exception {

        SearchResponseDTO responseDTO = new SearchResponseDTO.Builder(ResponseCode.PLACE_SEARCH_SUCCESS.getErrorCode(), ResponseCode.PLACE_SEARCH_SUCCESS.getMessage())
                .keyword("제주도")
                .curPage(1)
                .build();

        // when
        //then
        Assertions.assertThat(searchPlaceService.searchPlace("제주도",5,1,null).getResCode()).isEqualTo(responseDTO.getResCode());
    }


    @Test
    @DisplayName("장소 검색 실패, 잘못된요청")
    public void searchPlaceFail() throws Exception {


        SearchResponseDTO responseDTO = new SearchResponseDTO.Builder(ResponseCode.PLACE_SEARCH_SUCCESS.getErrorCode(), ResponseCode.PLACE_SEARCH_SUCCESS.getMessage())
                .keyword("제주도")
                .curPage(9999)
                .build();

        // when
        //then
        Assertions.assertThat(searchPlaceService.searchPlace("제주도",5,9999,null).getResCode()).isEqualTo(responseDTO.getResCode());
    }
}
