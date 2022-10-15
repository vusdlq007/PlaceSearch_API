package com.api.placesearch.api.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


//@AllArgsConstructor
@Data
public class SearchInfoResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "검색키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "누적 조회 건수", dataType = "integer")
    private Integer totalViews;

    @ApiModelProperty(value = "업데이트 시간")
    private LocalDateTime updatedAt;



    public static class Builder {

        private Integer resCode;
        private String resMessage;
        private String keyword;
        private Integer totalViews;
        private LocalDateTime updatedAt;


        public Builder(Integer resCode, String resMessage) {
            this.resCode = resCode;
            this.resMessage = resMessage;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;

            return this;
        }

        public Builder totalViews(Integer totalViews) {
            this.totalViews = totalViews;

            return this;
        }


        public SearchInfoResponseDTO build() {
            return new SearchInfoResponseDTO(this);
        }
    }

    private SearchInfoResponseDTO(Builder builder) {
        resCode = builder.resCode;
        resMessage = builder.resMessage;
        keyword = builder.keyword;
        totalViews = builder.totalViews;
    }

}
