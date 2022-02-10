package com.example.chapter6.Util;

import java.util.Random;

public class Util {

    /**
     * strLength 만큼 랜덤 문자열 생성
     * @param strLength
     * @return
     */
    public static String generateRandomString(int strLength) {
        Random random = new Random();

//        String result = "";
        StringBuilder stringBuilder = new StringBuilder(strLength);

        for(int i=0;i<strLength;i++) {
            //  a 부터, z-a 사이의 아무거나~
            char tmp = (char) ('a' + random.nextInt('z'-'a'));
//            result += tmp;
            stringBuilder.append(tmp);
        }

        return stringBuilder.toString();
    }
}
