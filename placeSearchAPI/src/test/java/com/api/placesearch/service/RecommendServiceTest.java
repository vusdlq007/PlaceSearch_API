package com.api.placesearch.service;



import com.api.placesearch.api.dto.response.KeywordRecommendResponseDTO;
import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.api.repo.SearchResultRepository;
import com.api.placesearch.api.svc.RecommendService;
import com.api.placesearch.api.svc.SearchPlaceService;
import com.api.placesearch.api.svc.impl.RecommendPlaceServiceImpl;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class RecommendServiceTest {


    RecommendService recommendService;

    @Autowired
    SearchResultRepository searchResultRepository;

    @Before
    public void before(){
        this.recommendService = new RecommendPlaceServiceImpl(searchResultRepository);
    }

    @Test
    @DisplayName("추천 장소 검색 키워드 조회 성공")
    public void searchPlaceSuccess() throws Exception {

        KeywordRecommendResponseDTO responseDTO = new KeywordRecommendResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_SUCCESS.getErrorCode(), ResponseCode.RECOMAND_SEARCH_SUCCESS.getMessage())
                .build();

        // when
        //then
        Assertions.assertThat(recommendService.recomandSearchKeyword(PageRequest.of(1,1)).getResCode()).isEqualTo(responseDTO.getResCode());
    }


    @Test
    @DisplayName("추천 장소 검색 키워드 조회 실패, 잘못된요청")
    public void searchPlaceFail() throws Exception {


        KeywordRecommendResponseDTO responseDTO = new KeywordRecommendResponseDTO.Builder(ResponseCode.RECOMAND_SEARCH_FAIL.getErrorCode(), ResponseCode.RECOMAND_SEARCH_FAIL.getMessage())
                .build();

        // when
        //then
        Assertions.assertThat(recommendService.recomandSearchKeyword(PageRequest.of(999,99999)).getResCode()).isEqualTo(responseDTO.getResCode());
    }
}
