package com.api.placesearch.api.ctr;

import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.svc.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
                                                 Integer disCnt,
                                         @ApiParam(
                                                 name = "정렬 기준 (sim,조회수순)",
                                                 type = "String",
                                                 example = "sim")
                                                 String sort
                                         ) {

        return searchPlaceService.searchPlace(keyword,disCnt,sort);
    }

    /**
     * 장소 상세 조회.
     *
     * @param
     * @return
     */
    @ApiOperation(value = "특정 장소에 대한 상세 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 응답"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("")
    public SearchResponseDTO searchPlaceDetail(SearchRequestDTO requestDTO) {

        return searchPlaceService.searchPlaceDetail(requestDTO);
    }

}
