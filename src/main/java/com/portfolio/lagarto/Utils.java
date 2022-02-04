package com.portfolio.lagarto;

import com.portfolio.lagarto.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

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
                .replace("'", "&#39");
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


    //id 뒷자리 4번째부터 *로치환
    public static String subString(String str) {
        return str.substring(0, 4) + str.substring(4).replaceAll("\\S", "*");
    }
}
