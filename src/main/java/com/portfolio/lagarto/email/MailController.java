package com.portfolio.lagarto.email;


import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/ajax")
public class MailController {
    @Autowired private MailService service;
    @Autowired
    private Utils utils;
    @Autowired //필요한 메소드 자동찾기
    private UserService service1;

    @PostMapping("/mail")
    public String mail(@RequestParam String uid){
        try {
            return service.sendMail(uid);
        }catch (Exception e){
            return null;
        }

    }

    @GetMapping("/authkey")
    public int authkey(UserEntity entity, HttpSession hs,int authKey){
        if (authKey == 0){
            return 0;
        }else {
            UserEntity db = (UserEntity) hs.getAttribute(Const.LOGIN_USER);
            if(service.authTrue(entity) == 1){
                db.setAuth(true);
                return service.authTrue(entity);
            }
        }
        return 2;
    }

}