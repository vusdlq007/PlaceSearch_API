package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecommendKeywordDTO {


    @ApiModelProperty(value = "검색키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "검색된 횟수")
    private Integer totalViews;


    @ApiModelProperty(value = "조회 기준 일시")
    private LocalDateTime createdAt;

    public static class Builder {


        private String keyword;
        private Integer totalViews;
        private LocalDateTime createdAt;


        public Builder(String keyword, Integer totalViews) {
            this.keyword = keyword;
            this.totalViews = totalViews;
        }


        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;

            return this;
        }


        public RecommendKeywordDTO build() {
            return new RecommendKeywordDTO(this);
        }
    }

    private RecommendKeywordDTO(Builder builder) {

        keyword = builder.keyword;
        totalViews = builder.totalViews;
        createdAt = builder.createdAt;
    }


}
