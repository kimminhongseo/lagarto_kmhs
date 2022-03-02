package com.portfolio.lagarto;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.follow.FollowService;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserInformation {
    @Autowired //필요한 메소드 자동찾기
    private UserService service;
    @Autowired
    private FollowService fservice;

    @GetMapping("/userInformation")
    public void userInformation(Model model) {
        model.addAttribute(Const.Follower, fservice.FollowList());
        model.addAttribute(Const.Following, fservice.FollowingList());
    }

    @PostMapping("/userInformation")
    @ResponseBody
    public UserEntity userInformation(@RequestBody UserEntity entity) {
        System.out.println(entity);
        return service.selUser(entity);
    }
}
