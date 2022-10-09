package com.api.placesearch.cmm.util;

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

}
