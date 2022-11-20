package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder
public class PlaceDTO {

    @ApiModelProperty(value = "장소명")
    private String placeName;

    @ApiModelProperty(value = "지번 주소")
    private String addressName;

    @ApiModelProperty(value = "도로명 주소")
    private String roadAddressName;

    @ApiModelProperty(value = "전화번호")
    private String phone;

    @ApiModelProperty(value = "카테고리")
    private String category;


}
