package com.api.placesearch.cmm.constant;

public enum NaverUrlCode {
    NAVER_BASE("https://openapi.naver.com"),
    NAVER_LOCAL_JSON("/v1/search/local.json"),
    NAVER_LOCAL_XML("/v1/search/local.xml"),
    NAVER_HEADER_CLIENT_ID("X-Naver-Client-Id"),
    NAVER_HEADER_CLIENT_SECRET("X-Naver-Client-Secret"),
    ;

    private final String label;

    NaverUrlCode(String label) {
        this.label = label;
    }

    public String label(){
        return label;
    }

}
