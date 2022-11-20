package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public class SearchLogDTO {

    @ApiModelProperty(value = "로그 seq")
    private Long seq;

    @ApiModelProperty(value = "사용자 기기 MAC 주소")
    private String macAdd;

    @ApiModelProperty(value = "기기 OS", dataType = "string")
    private String os;

    @ApiModelProperty(value = "검색키워드", dataType = "string")
    private String keyword;


    @ApiModelProperty(value = "로그 생성 시간")
    private LocalDateTime createdAt;


}
