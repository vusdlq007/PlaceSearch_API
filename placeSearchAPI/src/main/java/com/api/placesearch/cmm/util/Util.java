package com.api.placesearch.cmm.util;

import com.api.placesearch.api.dto.response.KaKaoSearchResponseDTO;
import com.api.placesearch.api.dto.response.NaverSearchResponseDTO;

import java.util.LinkedHashMap;
import java.util.Random;

public class Util {

    public static Long generateUUID(int n) {
        Random rd = new Random();//랜덤 객체 생성
        StringBuilder uuidStr = new StringBuilder();
        String uuid = null;

        // 9자리만 셋팅
        for (int i = 0; i < n; i++) {
            uuidStr.append(Integer.toString(rd.nextInt(9)));
        }
        uuid = uuidStr.toString();
        return Long.parseLong(uuid);
    }

    public static String generateAccountUUID(int n) {
        // 특정자리수만큼 uuid 생성.
        Long uuid = generateUUID(n);
        String accountInt = String.valueOf(uuid.intValue());
        String forwordStr = accountInt.substring(0,4);
        String afterStr = accountInt.substring(4);

        StringBuilder strBuilber = new StringBuilder();
        strBuilber.append(forwordStr).append("-").append(afterStr);

        return strBuilber.toString();
    }

    /**
     * html태그 제거 반환.
     * @param item
     * @return
     */
    public static String tagRemove(String item){
        return item.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    /**
     * 공백 제거 반환.
     * @param item
     * @return
     */
    public static String spaceRemove(String item){
        return item.replaceAll("\\s+", "");
    }

    /**
     * KAKAO API DOC 결과 Map에 담아 반환.
     * @param kakaoResult
     * @return
     */
    public static LinkedHashMap kakaoArrayListToHashMap(KaKaoSearchResponseDTO kakaoResult){
        LinkedHashMap<String,KaKaoSearchResponseDTO.Document> result = new LinkedHashMap<>();
        
        for(KaKaoSearchResponseDTO.Document doc : kakaoResult.getDocuments()){
            result.put(spaceRemove(doc.getPlaceName()),doc);
        }

        return result;
    }

    /**
     * NAVER API ITEM 결과 Map에 담아 반환.
     * @param naverResult
     * @return
     */
    public static LinkedHashMap naverArrayListToHashMap(NaverSearchResponseDTO naverResult){
        LinkedHashMap<String,NaverSearchResponseDTO.Items> result = new LinkedHashMap<>();

        for(NaverSearchResponseDTO.Items item : naverResult.getItems()){
            item.setTitle(tagRemove(item.getTitle()));
            result.put(spaceRemove(item.getTitle()),item);
        }

        return result;
    }

}
