package com.portfolio.lagarto.user;


import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.enums.JoinResult;
import com.portfolio.lagarto.model.UserDto;
import com.portfolio.lagarto.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired //필요한 메소드 자동찾기
    private UserService service;
    @Autowired
    private Utils utils;


    @GetMapping("/login")
    public String login(Model model) {
        if (0 != utils.getLoginUserPk()){
            return "redirect:/main";
        }
        model.addAttribute("title", "로그인");
        return "user/login";
    }

    @PostMapping("/login")
    public String loginproc(UserEntity entity, Model model) {
        int result =service.loginSel(entity);
        if (result == 1){//로그인성공
            return "redirect:/main";
        }
        model.addAttribute("title", "로그인");
        model.addAttribute(Const.MSG, Const.ERR_Login);
        return "user/login";
    }

     @PostMapping("/apiLogin")
     @ResponseBody
     public int loginProc(@RequestBody UserEntity entity){
        UserEntity dbentity = service.selUser(entity);
        if (dbentity == null){
            String pw = Utils.randomPw();
            entity.setUpw(pw);
            service.apiInsUser(entity);
            return 1;
        }
        utils.setLoginUser(dbentity);
        System.out.println(utils.getLoginUserPk());
        return 0;
     }

    @GetMapping("/certification")
    public void certification() {

    }

    @ResponseBody
    @PostMapping("/certification")
    public Map<String, Integer> certificationProc(@ModelAttribute("userEntity") UserEntity entity) {
        Map<String, Integer> result = new HashMap<>();

        // 중복된 번호
        int contactCheck = 0;

        service.contactCheck(entity);
        JoinResult joinRslt = entity.getResult();

        // 사용 가능한 번호
        if (joinRslt == JoinResult.AVAILABLE_CONTACT) {
            contactCheck = 1;
        }

        result.put("result", contactCheck);
        System.out.println("result : " + contactCheck);
        return result;
    }

    @GetMapping("/join")
    public void join(@ModelAttribute("entity") UserEntity entity, Model model) {
        model.addAttribute("UID", Const.UID);
        model.addAttribute("UPW", Const.UPW);
        model.addAttribute("CONTACT_FIRST", Const.CONTACT_FIRST);
        model.addAttribute("CONTACT_SECOND", Const.CONTACT_SECOND);
        model.addAttribute("CONTACT_THIRD", Const.CONTACT_THIRD);
    }

    @PostMapping("/join")
    public String joinProc(UserEntity entity, Model model) {
        int result = service.insUser(entity);

        if (result != 1) {
            model.addAttribute("err", "회원가입에 실패하였습니다.");
            System.out.println("회원가입 실패");
        }

        // TODO : email 전송 기능 구현 후 email 인증 페이지로 이동 후 회원가입 처리

        return "redirect:/user/login";
    }

    @GetMapping("/disc/{cd}")
    public String disc(@PathVariable String cd) {
        return "/user/disc/" + cd;
    }

    @PostMapping("/apiJoin")
    public void apiJoinProc(UserEntity entity) {
        System.out.println(entity.getNickname());
        service.facebookIns(entity);
    }

    @PostMapping("/uidChk")
    @ResponseBody
    public Map<String, Integer> emailCount(@RequestBody UserEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("uid : " + entity.getUid());
        result.put("result", service.uidCheck(entity));
        return result;
    }


    @PostMapping("/contChk")
    @ResponseBody
    public Map<String, Integer> contactCount(@RequestBody UserEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        System.out.println("contact : " + entity.getContact_first() + entity.getContact_second() + entity.getContact_third());
        result.put("result", service.contactCheck(entity));
        return result;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, UserEntity entity) {
        if (0 != utils.getLoginUserPk()){
            System.out.println(entity.isAuth());
            UserEntity db = service.authKey(entity);
            model.addAttribute("authKey", db.isAuth());
            return "/user/mypage";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/passwordCurrent")
    @ResponseBody
    public Map<String, Integer> passwordSel(@RequestBody UserDto dto){
        System.out.println(dto.getIuser());
        System.out.println(dto.getUpw());
        System.out.println(dto.getNewUpw());
        int userEntity = service.passwordSel(dto);

        Map<String, Integer> result = new HashMap<>();
        if(userEntity != 0){
            result.put("result", 1);
            return result;
        }
        result.put("result", 0);
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main";
    }
}

