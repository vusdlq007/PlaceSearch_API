package com.api.placesearch.cmm.constant;

public enum Constants {
    KAKAO("kakao"),
    NAVER("naver"),
    ;


    private final String label;

    Constants(String label) {
        this.label = label;
    }

    public String label(){
        return label;
    }

}
