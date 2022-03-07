package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.FollowEntity;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public int follow(@PathVariable int iuser, HttpSession hs, Model model){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = uservice.selUser(entity);
        UserEntity iuserYou = (UserEntity)object;
        if (object == null){
            return 0;
        }
        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        if (followEntity.getIuserYou() == followEntity.getIuserMe()){
            return 2;
        }
        fservice.follow(followEntity);
        return 1;
    }

    @DeleteMapping("/unfollow/{iuser}")
    public void unfollow(@PathVariable int iuser, HttpSession hs, Model model){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = uservice.selUser(entity);
        UserEntity iuserYou = (UserEntity)object;

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());

        fservice.unfollow(followEntity);
    }

    @GetMapping("/isfollow/{iuser}")
    public int isFollow(@PathVariable int iuser, HttpSession hs){
        UserEntity entity = new UserEntity();
        entity.setIuser(iuser);
        Object object = hs.getAttribute(Const.LOGIN_USER);
        UserEntity iuserMe = uservice.selUser(entity);
        UserEntity iuserYou = (UserEntity)object;

        FollowEntity followEntity = new FollowEntity();
        followEntity.setIuserMe(iuserMe.getIuser());
        followEntity.setIuserYou(iuserYou.getIuser());
        //0302균기 : 여기서 null받아와져서 에러가 떠짐. (detail.js에서 session.login == null)이면
        if (followEntity.getIuserMe() == followEntity.getIuserYou()){
            return 2;
        }
        return fservice.isFollow(followEntity);
    }

}