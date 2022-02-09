package com.portfolio.lagarto.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class MailController {
    @Autowired private MailService service;
    @GetMapping("/mail")
    public void mail(){
        service.sendMail();
    }
}
