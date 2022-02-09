package com.portfolio.lagarto.email;

import com.portfolio.lagarto.model.UserDto;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ajax")
public class MailController {
    @Autowired private MailService service;


    @GetMapping("/mail")
    public String mail(@RequestParam String uid){
        return service.sendMail(uid);

    }

    @GetMapping("/authkey")
    public int authkey(UserEntity entity){
        return service.authTrue(entity);
    }

}
