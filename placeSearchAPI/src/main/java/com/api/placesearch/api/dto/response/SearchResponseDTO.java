package com.api.placesearch.api.dto.response;

import com.api.placesearch.api.dto.PlaceDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;


//@AllArgsConstructor
@Data
public class SearchResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "검색 키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "한페이지에 보여질 건수", dataType = "integer")
    private Integer displayCnt;

    @ApiModelProperty(value = "현재 페이지", dataType = "integer")
    private Integer curPage;

    @ApiModelProperty(value = "총 검색 건수", dataType = "integer")
    private Integer totalCnt;


    @ApiModelProperty(value = "조회 정보 리스트 데이터")
    private List<PlaceDTO> places;


    public static class Builder {

        private Integer resCode;
        private String resMessage;
        private String keyword;
        private Integer displayCnt;
        private Integer curPage;
        private Integer totalCnt;
        private List<PlaceDTO> places;

        public Builder(Integer resCode, String resMessage) {
            this.resCode = resCode;
            this.resMessage = resMessage;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;

            return this;
        }

        public Builder displayCnt(Integer displayCnt) {
            this.displayCnt = displayCnt;

            return this;
        }

        public Builder curPage(Integer curPage) {
            this.curPage = curPage;

            return this;
        }

        public Builder totalCnt(Integer totalCnt) {
            this.totalCnt = totalCnt;

            return this;
        }

        public Builder places(List<PlaceDTO> places) {
            this.places = places;

            return this;
        }

        public SearchResponseDTO build() {
            return new SearchResponseDTO(this);
        }
    }

    private SearchResponseDTO(Builder builder) {
        resCode = builder.resCode;
        resMessage = builder.resMessage;
        keyword = builder.keyword;
        displayCnt = builder.displayCnt;
        curPage = builder.curPage;
        totalCnt = builder.totalCnt;
        places = builder.places;
    }

}
