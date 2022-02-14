package com.portfolio.lagarto.email;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.UserDto;
import com.portfolio.lagarto.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailMapper mapper;

    @Autowired
    private Utils utils;

    public String sendMail(String uid){
        ArrayList<String> toUserList = new ArrayList<>();
        toUserList.add(uid);
        System.out.println(uid);

        int toUserSize = toUserList.size();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));

        String authKey = Utils.randomIn();
        simpleMailMessage.setSubject("인증메일");
        simpleMailMessage.setText("인증번호 : " +   authKey  + " 입니다.");

        javaMailSender.send(simpleMailMessage);
        return authKey;
    }

    public int authTrue(UserEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.authTrue(entity);
    }
}