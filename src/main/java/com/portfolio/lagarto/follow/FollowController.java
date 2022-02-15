package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.FollowEntity;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class FollowController {

    @Autowired
    private FollowService fservice;
    @Autowired
    private UserService uservice;

    @GetMapping("/profile")
    public void profile(){};

    @PostMapping("/follow/{iuser}")
    public String follow(@PathVariable int iuser, HttpSession hs, Model model){
        System.out.println(iuser);
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = uservice.selUser(entity);
        UserEntity iuserYou = (UserEntity)object;

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        fservice.follow(followEntity);
        return "FollowOk";
    }

    @DeleteMapping("/unfollow/{iuser}")
    public String unfollow(@PathVariable int iuser, HttpSession hs, Model model){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = uservice.selUser(entity);
        UserEntity iuserYou = (UserEntity)object;

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        fservice.unfollow(followEntity);
        return "unFollowOK";
    }


}