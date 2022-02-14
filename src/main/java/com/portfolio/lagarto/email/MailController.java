package com.portfolio.lagarto.email;


import com.portfolio.lagarto.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/ajax")
public class MailController {
    @Autowired private MailService service;


    @GetMapping("/mail")
    public String mail(@RequestParam String uid){
        try {
            return service.sendMail(uid);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/authkey")
    public int authkey(UserEntity entity){
        return service.authTrue(entity);
    }

}