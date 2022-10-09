package com.api.placesearch.api.ctr;

import com.api.placesearch.api.dto.SearchRequestDTO;
import com.api.placesearch.api.dto.SearchResponseDTO;
import com.api.placesearch.api.svc.SearchPlaceLogService;
import com.api.placesearch.api.svc.SearchPlaceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(tags = {" 장소 검색 로그 조회 컨트롤러"})
@SwaggerDefinition(tags = {
        @Tag(name = "장소 검색 로그 조히 컨트롤러", description = "장소 검색간에 쌓인 로그를 확인하기 위한 기능 제공")
})
@RequestMapping("/v1/api/place/log")
@RestController
public class SearchLogRestController {

    @Autowired
    private SearchPlaceLogService searchPlaceLogService;


    /**
     * 장소 검색 로그 조회.
     * @param
     * @return
     */
    @ApiOperation(value="장소 검색 로그 확인", notes="이전에 조회된 로그들을 페이징 정보를 받아 조회 처리한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 응답"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("")
    public SearchResponseDTO searchPlace(Pageable pageable){

        return searchPlaceLogService.searchLog(pageable);
    }


}
