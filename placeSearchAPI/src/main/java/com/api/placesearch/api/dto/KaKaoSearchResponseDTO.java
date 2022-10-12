package com.api.placesearch.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.LinkedHashMap;

@Data
public class KaKaoSearchResponseDTO {


    @ApiModelProperty(value = "검색 메타 정보")
    private Meta meta;

    @ApiModelProperty(value = "검색 결과 정보 배열")
    private Document[] documents;


    public KaKaoSearchResponseDTO() {
    }

    public KaKaoSearchResponseDTO(Document[] documents, Meta meta) {
        this.documents = documents;
        this.meta = meta;
    }

    static class Document {
        @JsonProperty("id")
        private String id;
        @JsonProperty("place_name")
        private String placeName;
        @JsonProperty("distance")
        private String distance;
        @JsonProperty("place_url")
        private String placeUrl;
        @JsonProperty("category_name")
        private String categoryName;
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("road_address_name")
        private String roadAddressName;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("category_group_code")
        private String category_group_code;
        @JsonProperty("category_group_name")
        private String category_group_name;
        @JsonProperty("x")
        private String x;
        @JsonProperty("y")
        private String y;
    }

    static class Meta{
        @JsonProperty("same_name")
        private LinkedHashMap sameName;
        @JsonProperty("pageable_count")
        private Integer pageableCount;
        @JsonProperty("total_count")
        private Integer totalCount;
        @JsonProperty("is_end")
        private boolean isEnd;
    }

}
