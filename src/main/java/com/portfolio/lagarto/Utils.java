package com.portfolio.lagarto;

import com.portfolio.lagarto.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Date;

@Component
public class Utils {

    @Autowired
    private HttpSession hs;

    public void setLoginUser(UserEntity entity) {
        hs.setAttribute(Const.LOGIN_USER, entity);
    }

    public UserEntity getLoginUser() {
        return (UserEntity) hs.getAttribute(Const.LOGIN_USER);
    }

    public int getLoginUserPk() {
        return getLoginUser() == null ? 0 : getLoginUser().getIuser();
    }


    //    XSS공격 접근제한 메소드
    public static String replaceStr(String str) {
        return str.replace("<", "&lt")
                .replace(">", "&gt")
                .replace(".", "&#46")
                .replace("'", "&#39")
                .replace("#", "&#35");
    }
    
        //랜덤으로 비밀번호 주기
    public static String randomPw(){
        String password = "";
        for (int i = 0; i<=12;i++){
            int rNum = (int)(Math.random() * 10);
            String strNum = Integer.toString(rNum);
            password += strNum;
        }
        String encrypted = BCrypt.hashpw(password, BCrypt.gensalt());
        return encrypted;
    }

    public static String tempPw(int size){
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '~', '!', '@', '#', '$', '%', '&'
        };

        String password = "";
        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i = 0; i < size; i++) {
            idx = secureRandom.nextInt(len);
            stringBuffer.append(charSet[idx]);
        }

        return stringBuffer.toString();
    }

    public static String randomIn(){
        String password = "";
        for (int i = 0; i<=5;i++){
            int rNum = (int)(Math.random() * 10);
            String strNum = Integer.toString(rNum);
            password += strNum;
        }
        return password;
    }


    //id 뒷자리 4번째부터 *로치환
    public static String subString(String str) {
        return str.substring(0, 4) + str.substring(4).replaceAll("\\S", "*");
    }

    public static String hideEmail(String str, int platform_cd) {
        int index = str.indexOf("@");
        String id;
        String domain;

        id = str.substring(0, index);
        domain = str.substring(index + 1);

        String hideId = subString(id);

        if (platform_cd == 1) {
            return hideId + "@" + domain;
        } else {
            return hideId;
        }
    }
}
