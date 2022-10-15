package com.api.placesearch.api.dto.response;

import com.api.placesearch.api.dto.SearchLogDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


//@AllArgsConstructor
@Data
public class SearchLogResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "검색 키워드", dataType = "string")
    private String keyword;

    @ApiModelProperty(value = "총 검색 건수", dataType = "integer")
    private Integer totalCnt;


    @ApiModelProperty(value = "로그 정보 리스트 데이터")
    private List<SearchLogDTO> searchLogs;


    public static class Builder {

        private Integer resCode;
        private String resMessage;
        private String keyword;
        private Integer totalCnt;
        private List<SearchLogDTO> searchLogs;

        public Builder(Integer resCode, String resMessage) {
            this.resCode = resCode;
            this.resMessage = resMessage;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;

            return this;
        }


        public Builder totalCnt(Integer totalCnt) {
            this.totalCnt = totalCnt;

            return this;
        }

        public Builder searchLogs(List<SearchLogDTO> searchLogs) {
            this.searchLogs = searchLogs;

            return this;
        }

        public SearchLogResponseDTO build() {
            return new SearchLogResponseDTO(this);
        }
    }

    private SearchLogResponseDTO(Builder builder) {
        resCode = builder.resCode;
        resMessage = builder.resMessage;
        keyword = builder.keyword;
        totalCnt = builder.totalCnt;
        searchLogs = builder.searchLogs;
    }

}
