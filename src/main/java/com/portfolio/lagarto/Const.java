package com.portfolio.lagarto;

import java.util.regex.Pattern;

public class Const {
    public static final String UPLOAD_IMG_PATH = "D:/upload/images";

    public static final String LOGIN_USER = "loginUser";

    public static final String ERR_Login = "로그인 실패";
    public static final String MSG = "msg";
    public static final String UID = "^(?=.{8,50}$)([0-9a-z]([_]?[0-9a-z])*?)@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
    public static final String UPW = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:',<.>/?]{8,16})$";
    public static final String CONTACT_FIRST = "^(010|011|016|017|018|019)$";
    public static final String CONTACT_SECOND = "^([0-9]{4})$";
    public static final String CONTACT_THIRD = "^([0-9]{4})$";
    public static final String KoreanEngle = "^[a-zA-Z0-9가-힣]*$";
    public static final String PassWordCurrent = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*\\\\W).{8,2,}$";
    public static final String Follower = "follower";
    public static final String Following = "following";
    public static final String Money = "money";
    public static final String Count = "count";
    public static boolean checkUid(String email) {
        return email.matches(UID);
    }
    public static boolean checkNick(String nickname) {
        System.out.println(nickname);
        System.out.println(nickname.matches(KoreanEngle));
        return nickname.matches(KoreanEngle);
    }


    //Auction부분
    public static final String AUCTION_MENU_LIST = "auctionMenuList";
    public static final String DATA = "data";
    //Auction부분


    public static class Platform {
        public static final int GENERAL = 1;
        public static final int NAVER = 2;
        public static final int KAKAO = 3;
        public static final int GOOGLE = 4;
        // TODO : FACEBOOK 삭제, GOOGLE 4번 변경 (DB user_platform_type 컬럼 수정 해야 됨)
    }
}
