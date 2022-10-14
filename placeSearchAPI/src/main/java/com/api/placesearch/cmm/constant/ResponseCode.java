package com.api.placesearch.cmm.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ResponseCode {
    NOT_FOUND("PAGE NOT FOUND",404,"COMMON-ERR-404"),
    NOT_ALLOW("COMMON-ERR-403",403,"NOT ALLOW"),
    INTER_SERVER_ERROR("INTER SERVER ERROR",500,"COMMON-ERR-500"),
    SUCCESS("SUCCESS",200,"COMMON-SUCC-200"),

    PLACE_SEARCH_SUCCESS("SUCCESS",200,"장소 리스트 조회에 성공하였습니다."),
    PLACE_SEARCH_FAIL("INTER SERVER ERROR",500,"장소 리스트 조회에 실패하였습니다."),
    PLACE_SEARCH_NULL_FAIL("INTER SERVER NULL ERROR",600,"장소 리스트 검색 결과가 없습니다."),
    PLACE_SEARCH_ILLEGAL_ARG_FAIL("INTER SERVER ERROR",500,"장소 리스트 조회에 실패하였습니다. 현재 조회하려는 페이지 수가 총 페이지 수보다 많거나 적습니다."),
    PLACE_SEARCH_AGG_FAIL("INTER SERVER ERROR",502,"장소 리스트 조회간 정보 종합중 실패하였습니다. 시스템 로그를 확인해주세요."),
    KAKAO_PLACE_SEARCH_SUCCESS("SUCCESS",200,"카카오 키워드 장소 조회 API 호출에 성공하였습니다."),
    KAKAO_PLACE_SEARCH_FAIL("INTER SERVER ERROR",540,"카카오 키워드 장소 조회 API 호출에 실패하였습니다."),
    NAVER_PLACE_SEARCH_SUCCESS("SUCCESS",200,"네이버 키워드 장소 조회 API 호출에 성공하였습니다."),
    NAVER_PLACE_SEARCH_FAIL("INTER SERVER ERROR",550,"네이버 키워드 장소 조회 API 호출에 실패하였습니다."),
    SEARCH_INFO_UPDATE_SUCCESS("SUCCESS",200,"키워드 정보 업데이트에 성공하였습니다."),
    SEARCH_INFO_UPDATE_FAIL("KEYWORD INFO IS NOT EXIST OR INTER SERVER ERROR",505,"키워드 정보 업데이트에 성공하였습니다."),
    SEARCH_LOG_SUCCESS("SUCCESS",200,"키워드 조회 로그 조회에 성공하였습니다."),
    SEARCH_LOG_FAIL("INTER SERVER ERROR",500,"키워드 정보 조회에 실패하였습니다. 키워드 정보가 존재하지 않습니다."),
    SEARCH_LOG_FAIL_DB_ERROR("DB ERROR",520,"키워드 정보 조회에 실패하였습니다. DB 시스템 관리자에게 문의하여 주세요."),
    RECOMAND_SEARCH_SUCCESS("SUCCESS",200,"추천 장소 검색 키워드 조회에 성공하였습니다."),
    RECOMAND_SEARCH_FAIL("INTER SERVER ERROR",510,"추천 장소 검색 키워드 조회에 실패하였습니다.");



    @Getter
    private String status;
    @Getter
    private int errorCode;
    @Getter
    private String message;

}
