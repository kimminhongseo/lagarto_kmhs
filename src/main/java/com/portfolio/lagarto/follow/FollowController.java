package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.FollowEntity;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class FollowController {

    @Autowired
    private FollowService fservice;
    @Autowired
    private UserService uservice;

    @GetMapping("/profile")
    public void profile(){};

    @PostMapping("/follow/{uid}")
    public String follow(@PathVariable String uid, HttpSession hs, Model model){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = (UserEntity)object;
        UserEntity iuserYou = uservice.selUser(entity);

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        fservice.follow(followEntity);
        return "FollowOk";
    }

    @GetMapping("/unfollow/{uid}")
    public String unfollow(@PathVariable String uid, HttpSession hs, Model model){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = (UserEntity)object;
        UserEntity iuserYou = uservice.selUser(entity);

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        fservice.unfollow(followEntity);
        return "unFollowOK";
    }


}