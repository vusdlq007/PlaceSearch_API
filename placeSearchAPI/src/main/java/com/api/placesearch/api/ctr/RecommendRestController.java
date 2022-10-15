package com.api.placesearch.api.ctr;

import com.api.placesearch.api.dto.response.KeywordRecommendResponseDTO;
import com.api.placesearch.api.svc.RecommendService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {"검색 추천 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "검색 추천 서비스 컨트롤러", description = "검색 추천 기능 이용에 필요한 기능 제공")
})
@RequestMapping("/v1/api/recomand")
@RestController
public class RecommendRestController {

    // 필드명을 구현체 클래스 명과 맞춰줌.
    @Autowired
    private RecommendService recomandPlaceService;


    /**
     * 장소 검색.
     *
     * @param
     * @return
     */
    @ApiOperation(value = "키워드 장소 검색", notes = "장소 키워드를 받아 카카오,네이버API에 조회 처리 후 결과값을 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 응답"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/place")
    public KeywordRecommendResponseDTO searchPlace(
                                         @ApiParam(name = "페이징 정보")
                                                 Pageable pageable
                                         ) {

        return recomandPlaceService.recomandSearchKeyword(pageable);
    }

}
