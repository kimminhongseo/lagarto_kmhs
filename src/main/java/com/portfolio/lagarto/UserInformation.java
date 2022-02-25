package com.portfolio.lagarto;

import com.portfolio.lagarto.Const;
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

    @GetMapping("/userInformation")
    public void userInformation() {
    }

    @PostMapping("/userInformation")
    @ResponseBody
    public void userInformation(@RequestBody UserEntity entity,Model model) {
        System.out.println(entity.getNickname());
        model.addAttribute(Const.User, service.selUser(entity));
    }
}
