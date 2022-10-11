package com.api.placesearch.cmm.constant;

public enum KaKaoUrlCode {
    KAKAO_BASE("https://dapi.kakao.com"),
    KAKAO_LOCAL_JSON("/v2/local/search/keyword.json"),
    KAKAO_LOCAL_XML("/v2/local/search/keyword.xml")
    ;


    private final String label;

    KaKaoUrlCode(String label) {
        this.label = label;
    }

    public String label(){
        return label;
    }

}
