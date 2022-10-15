package com.api.placesearch.api.dto.response;


import com.api.placesearch.api.dto.RecommendKeywordDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


//@AllArgsConstructor
@Data
public class KeywordRecommendResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "한페이지에 보여질 건수", dataType = "integer")
    private Integer displayCnt;

    @ApiModelProperty(value = "현재 페이지", dataType = "integer")
    private Integer curPage;

    @ApiModelProperty(value = "총 검색 건수", dataType = "integer")
    private Integer totalCnt;


    @ApiModelProperty(value = "조회 정보 리스트 데이터")
    private List<RecommendKeywordDTO> recomandKeywords;


    public static class Builder {

        private Integer resCode;
        private String resMessage;
        private Integer displayCnt;
        private Integer curPage;
        private Integer totalCnt;
        private List<RecommendKeywordDTO> recomandKeywords;

        public Builder(Integer resCode, String resMessage) {
            this.resCode = resCode;
            this.resMessage = resMessage;
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

        public Builder recomandKeywords(List<RecommendKeywordDTO> recomandKeywords) {
            this.recomandKeywords = recomandKeywords;

            return this;
        }

        public KeywordRecommendResponseDTO build() {
            return new KeywordRecommendResponseDTO(this);
        }
    }

    private KeywordRecommendResponseDTO(Builder builder) {
        resCode = builder.resCode;
        resMessage = builder.resMessage;
        displayCnt = builder.displayCnt;
        curPage = builder.curPage;
        totalCnt = builder.totalCnt;
        recomandKeywords = builder.recomandKeywords;
    }

}
