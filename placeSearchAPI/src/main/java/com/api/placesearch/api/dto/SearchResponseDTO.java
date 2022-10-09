package com.api.placesearch.api.dto;

import com.api.placesearch.api.entity.Place;
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

    @ApiModelProperty(value = "조회 정보 리스트 페이징 데이터")
    private List<Place> searchInfoList;

    public SearchResponseDTO() { }

    public SearchResponseDTO(Integer resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }


    public SearchResponseDTO(Integer resCode, String resMessage, List<Place> searchInfoList) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.searchInfoList = searchInfoList;
    }


}
