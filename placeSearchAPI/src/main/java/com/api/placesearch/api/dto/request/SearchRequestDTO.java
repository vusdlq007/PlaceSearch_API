package com.api.placesearch.api.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


//@AllArgsConstructor
@Builder
public class SearchRequestDTO {

    @ApiModelProperty(value = "장소 키워드", example = "곱창집")
    private String placeKeyworkd;

    // 추후 추가될 유저명.
    @ApiModelProperty(value = "사용자명", example = "김윤권", required = true)
    private String userName;

    @ApiModelProperty(value = "한 번에 표시할 검색 결과 갯수", example = "5", required = true)
    private Integer displayCnt;

    @ApiModelProperty(value = "검색결과 정렬기준", example = "sim", required = true)
    private String sort;

}
