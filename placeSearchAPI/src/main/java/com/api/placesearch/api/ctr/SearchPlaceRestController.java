package com.api.placesearch.api.ctr;

import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.svc.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLException;

@Slf4j
@CrossOrigin("*")
@Api(tags = {" 장소 검색 서비스 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "장소 검색 서비스 컨트롤러", description = "장소 검색 이용에 필요한 기능 제공")
})
@RequestMapping("/v1/api/place")
@RestController
public class SearchPlaceRestController {

    @Autowired
    private SearchPlaceService searchPlaceService;


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
    @GetMapping("")
    public SearchResponseDTO searchPlace(@RequestParam
                                         @ApiParam(
                                                 name = "검색 키워드",
                                                 type = "String",
                                                 value = "",
                                                 example = "",
                                                 required = true)
                                                 String keyword,
                                         @ApiParam(
                                                 name = "보여질 갯수",
                                                 type = "Integer",
                                                 example = "5")
                                                 Integer size,
                                         @ApiParam(
                                                 name = "보여질 페이지 위치",
                                                 type = "Integer",
                                                 example = "5")
                                                 Integer page,
                                         @ApiParam(
                                                 name = "정렬 기준 (sim,조회수순)",
                                                 type = "String",
                                                 example = "accuracy")
                                                 String sort
                                         ) throws SSLException {

        return searchPlaceService.searchPlace(keyword, size, page, sort);
    }


}
