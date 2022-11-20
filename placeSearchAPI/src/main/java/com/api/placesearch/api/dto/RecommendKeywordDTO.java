package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public class RecommendKeywordDTO {


    @ApiModelProperty(value = "검색키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "검색된 횟수")
    private Integer totalViews;


    @ApiModelProperty(value = "조회 기준 일시")
    private LocalDateTime createdAt;


}
