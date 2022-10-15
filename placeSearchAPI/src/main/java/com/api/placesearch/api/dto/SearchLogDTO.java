package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
    private LocalDateTime createdAd;

    public static class Builder {

        private Long seq;
        private String macAdd;
        private String os;
        private String keyword;
        private LocalDateTime createdAd;


        public Builder(String keyword, String os) {
            this.keyword = keyword;
            this.os = os;
        }

        public Builder seq(Long seq) {
            this.seq = seq;

            return this;
        }

        public Builder macAdd(String macAdd) {
            this.macAdd = macAdd;

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
        seq = builder.seq;
        os = builder.os;
        macAdd = builder.macAdd;
        keyword = builder.keyword;
        createdAd = builder.createdAd;
    }


}
