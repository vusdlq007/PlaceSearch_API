package com.api.placesearch.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlaceDTO {

    @ApiModelProperty(value = "장소명")
    private String placeName;

    @ApiModelProperty(value = "지번 주소")
    private String addressName;

    @ApiModelProperty(value = "도로명 주소")
    private String roadAddressName;

    @ApiModelProperty(value = "전화번호")
    private String phone;

    @ApiModelProperty(value = "카테고리")
    private String category;


    public static class Builder {
        private String placeName;
        private String addressName;
        private String roadAddressName;
        private String phone;
        private String category;
        
        // 필수 입력값
        public Builder(String placeName, String addressName, String roadAddressName){
            this.placeName = placeName;
            this.addressName = addressName;
            this.roadAddressName = roadAddressName;
        }
        
        public Builder phone(String phone){
            this.phone = phone;
            
            return this;
        }
        
        public Builder category(String category){
            this.category = category;

            return this;
        }
        public PlaceDTO build(){
            return new PlaceDTO(this);
        }
    }

    private PlaceDTO(Builder builder){
        placeName = builder.placeName;
        addressName = builder.addressName;
        roadAddressName = builder.roadAddressName;
        phone = builder.phone;
        category = builder.category;
    }


}
