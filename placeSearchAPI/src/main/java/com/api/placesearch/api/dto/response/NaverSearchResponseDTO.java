package com.api.placesearch.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class NaverSearchResponseDTO {

    @ApiModelProperty(value = "검색 일자/시간 정보")
    private Date lastBuildDate;

    @ApiModelProperty(value = "총 검색 결과 수")
    private Integer total;

    @ApiModelProperty(value = "검색 페이지")
    private Integer start;

    @ApiModelProperty(value = "한페이지에 표기할 사이즈")
    private Integer display;


    @ApiModelProperty(value = "검색 결과 정보 배열")
    private ArrayList<Items> items;


    public NaverSearchResponseDTO() {
    }


    public NaverSearchResponseDTO(Date lastBuildDate, Integer total, Integer start, Integer display, ArrayList<Items> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }
    @Data
    public static class Items {
        @JsonProperty("title")
        private String title;
        @JsonProperty("link")
        private String link;
        @JsonProperty("category")
        private String category;
        @JsonProperty("description")
        private String description;
        @JsonProperty("telephone")
        private String telephone;
        @JsonProperty("address")
        private String address;
        @JsonProperty("roadAddress")
        private String roadAddress;
        @JsonProperty("mapx")
        private String mapx;
        @JsonProperty("mapy")
        private String mapy;
    }
    

}
