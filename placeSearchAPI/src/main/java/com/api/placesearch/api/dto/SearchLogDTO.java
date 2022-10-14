package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchLogDTO {


    @ApiModelProperty(value = "사용자 기기 MAC 주소")
    private String macAdd;

    @ApiModelProperty(value = "기기 OS", dataType = "string")
    private String os;

    @ApiModelProperty(value = "검색키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "누적 조회 건수", dataType = "integer")
    private Integer totalViews;

    @ApiModelProperty(value = "로그 생성 시간")
    private LocalDateTime createdAd;

    public static class Builder {

        private String macAdd;
        private String os;
        private String keyword;
        private Integer totalViews;
        private LocalDateTime createdAd;


        public Builder(String keyword,String os) {
            this.keyword = keyword;
            this.os = os;
        }

        public Builder macAdd(String macAdd) {
            this.macAdd = macAdd;

            return this;
        }

        public Builder totalViews(Integer totalViews) {
            this.totalViews = totalViews;

            return this;
        }

        public Builder createdAd(LocalDateTime createdAd) {
            this.createdAd = createdAd;

            return this;
        }


        public SearchLogDTO build() {
            return new SearchLogDTO(this);
        }
    }

    private SearchLogDTO(Builder builder) {
        os = builder.os;
        macAdd = builder.macAdd;
        keyword = builder.keyword;
        totalViews = builder.totalViews;
        createdAd = builder.createdAd;
    }


}
