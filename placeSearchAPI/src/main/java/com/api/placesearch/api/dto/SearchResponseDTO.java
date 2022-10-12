package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;


//@AllArgsConstructor
@Data
public class SearchResponseDTO {

    @ApiModelProperty(value = "응답 코드", dataType = "integer", example = "200")
    private Integer resCode;
    @ApiModelProperty(value = "응답 메시지", dataType = "string")
    private String resMessage;

    @ApiModelProperty(value = "한페이지에 보여질 건수", dataType = "integer")
    private Integer displayCnt;


    @ApiModelProperty(value = "조회 정보 리스트 데이터")
    private ArrayList<PlaceDTO> places;


    public static class Builder {

        private Integer resCode;
        private String resMessage;
        private Integer displayCnt;
        private ArrayList<PlaceDTO> places;

        public Builder(Integer resCode, String resMessage){
            this.resCode = resCode;
            this.resMessage = resMessage;
        }

        public Builder displayCnt(Integer displayCnt){
            this.displayCnt = displayCnt;

            return this;
        }



        public Builder places(ArrayList<PlaceDTO> places){
            this.places = places;

            return this;
        }

        public SearchResponseDTO build(){
            return new SearchResponseDTO(this);
        }
    }

    private SearchResponseDTO(Builder builder){
         resCode = builder.resCode;
         resMessage = builder.resMessage;
         displayCnt = builder.displayCnt;
         places = builder.places;
    }

}
