package com.portfolio.lagarto;

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
    public static final String Follower = "follower";
    public static final String Following = "following";

    public static boolean checkUid(String email) {
        return email.matches(UID);
    }



    //Auction부분
    public static final String AUCTION_MENU_LIST = "auctionMenuList";
    public static final String DATA = "data";
    //Auction부분


    public static class Platform {
        public static final int GENERAL = 1;
        public static final int NAVER = 2;
        public static final int KAKAO = 3;
        public static final int FACEBOOK = 4;
        public static final int GOOGLE = 5;
    }
}
