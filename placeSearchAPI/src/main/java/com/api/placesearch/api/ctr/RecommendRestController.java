package com.api.placesearch.api.ctr;

import com.api.placesearch.api.dto.response.SearchResponseDTO;
import com.api.placesearch.api.svc.RecomandService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RecomandService recomandPlaceService;


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
    public SearchResponseDTO searchPlace(@RequestParam
                                         @ApiParam(
                                                 name = "추천 받을 키워드 카테고리",
                                                 type = "String",
                                                 value = "",
                                                 example = "",
                                                 required = true)
                                                 String category,
                                         @ApiParam(
                                                 name = "보여줄 갯수",
                                                 type = "Integer",
                                                 example = "10")
                                                 Integer disCnt
                                         ) {

        return recomandPlaceService.recomandSearchKeyword(category,disCnt);
    }

}
