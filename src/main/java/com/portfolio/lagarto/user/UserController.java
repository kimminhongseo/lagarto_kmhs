package com.portfolio.lagarto.user;


import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.SessionManager;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.enums.ForgotIdResult;
import com.portfolio.lagarto.enums.ForgotPwResult;
import com.portfolio.lagarto.enums.JoinResult;
import com.portfolio.lagarto.follow.FollowService;
import com.portfolio.lagarto.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired //필요한 메소드 자동찾기
    private UserService service;
    @Autowired
    private FollowService fservice;
    @Autowired
    private Utils utils;
    @Autowired
    private SessionManager sessionManager;


    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("vo") LoginVo loginVo, @CookieValue(value="auto_id_check", required = false) Cookie rememberCookie) {
        if (0 != utils.getLoginUserPk()){
            return "redirect:/main";
        }

        if (rememberCookie != null) {
            model.addAttribute("rememberCookie", rememberCookie);
            System.out.println("cookie : " + rememberCookie.getValue());
        }
        model.addAttribute("title", "로그인");
        return "/user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public int loginproc(@ModelAttribute("entity") LoginVo loginVo, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        LoginVo loginUser = service.loginSel(loginVo);

        if (loginUser == null) {
            return 2;
        }

        service.putAutoSaveKey(loginVo, response);

        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(loginUser, entity);

        entity.setLast_login_at(loginUser.getLast_login_at());
        session.setAttribute(Const.LOGIN_MEMBER, entity);

        service.updLastLogin(loginVo);

        return 1;
    }

    @PostMapping("/apiLogin")
    @ResponseBody
    public Map<String, Integer> loginProc(@RequestBody UserEntity entity){
        UserEntity dbEntity = service.selApiUser(entity);

        Map<String, Integer> result = new HashMap<>();
        if (dbEntity == null){
            String pw = Utils.randomPw();
            entity.setUpw(pw);
            result.put("result", 0);
        } else {
            utils.setLoginUser(dbEntity);
            System.out.println(utils.getLoginUserPk());
            result.put("result", 1);
        }
        return result;
    }

    @GetMapping("/certification")
    public void certification(@ModelAttribute("entity") UserEntity entity, Model model) {
        model.addAttribute("CONTACT_FIRST", Const.CONTACT_FIRST);
        model.addAttribute("CONTACT_SECOND", Const.CONTACT_SECOND);
        model.addAttribute("CONTACT_THIRD", Const.CONTACT_THIRD);
    }

    @ResponseBody
    @PostMapping("/apiCertification")
    public Map<String, Integer> certificationProc(@RequestBody UserEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        System.out.println(entity);

        String pw = Utils.randomPw();
        entity.setUpw(pw);

        // 중복된 번호
        int contactCheck = 0;

        service.contactCheck(entity);
        JoinResult joinRslt = entity.getResult();

        // 사용 가능한 번호
        if (joinRslt == JoinResult.AVAILABLE_CONTACT) {
            contactCheck = 1;
            service.apiJoin(entity);
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

        System.out.println(entity);

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
    public String mypage(Model model) {
        model.addAttribute(Const.Follower, fservice.FollowList());
        model.addAttribute(Const.Following, fservice.FollowingList());
        if (0 != utils.getLoginUserPk()) {
            return "/user/mypage";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/passwordCurrent")
    @ResponseBody
    public Map<String, Integer> passwordSel(@RequestBody UserDto dto) {
        System.out.println(dto.getIuser());
        System.out.println(dto.getUpw());
        System.out.println(dto.getNewUpw());
        Boolean NewUpw = Pattern.matches(Const.PassWordCurrent, dto.getNewUpw());
        int userEntity = service.passwordSel(dto);

        Map<String, Integer> result = new HashMap<>();
        if (!NewUpw) {
            result.put("result", 2);
            return result;
        }
        if (userEntity != 0) {
            result.put("result", 1);
            return result;
        }
        result.put("result", 0);
        return result;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.removeAttribute("loginVO");
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/information")
    public void information(HttpSession hs) {

    }

    @PostMapping("/nicknameCheck")
    @ResponseBody
    public int nicknameCheck(@RequestBody UserDto userDto) {
        if (!Const.checkNick(userDto.getNickname())) {
            return 2;
        }
        int result = service.nicknameCheck(userDto.getNickname());
        return result;
    }

    @PostMapping("/information")
    public String information(@RequestParam String nickname, @RequestParam String nm, @RequestParam String address_post, @RequestParam String address_primary, @RequestParam String address_secondary, HttpSession hs, Model model) {
        String nickNames = nickname.trim();
        String nms = nm.trim();
        boolean nickNamesB = Pattern.matches(Const.KoreanEngle, nickNames);
        boolean nmsB = Pattern.matches(Const.KoreanEngle, nms);
        UserEntity hsentity = (UserEntity) hs.getAttribute(Const.LOGIN_USER);
        if (hsentity == null) {
            return "/user/login";
        }
        if (nickNamesB && nmsB) {
            if (nickNames.length() > 0 && nms.length() > 0) {
                hsentity.setNickname(nickNames);
                hsentity.setNm(nms);
                hsentity.setAddress_post(address_post);
                hsentity.setAddress_primary(address_primary.trim());
                hsentity.setAddress_secondary(address_secondary.trim());
                service.informationUpd(hsentity);
                return "redirect:/user/mypage";
            }
        }
        model.addAttribute(Const.MSG, "공백,특수문자 없이 입력 하십시오.");
        return "/user/information";
    }

    @GetMapping("/charge")
    public String charge(@RequestParam int pageNum, UserEntity entity,PageVo vo, Model model){ //page num = 2
        System.out.println(pageNum);
        if (utils.getLoginUserPk() != 0){
            int result = service.selMoneyCount(entity);
            model.addAttribute(Const.Count, result);
            vo.setCurrentPage((pageNum -1) * vo.getRecordCount());
            vo.setRecordCount(vo.getRecordCount());
            vo.setIuser(utils.getLoginUserPk());
            model.addAttribute(Const.Money, service.selMoney(vo));
            return "/user/charge";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/charge")
    public void charge(@RequestParam int money, HttpSession hs){
        UserEntity entity = (UserEntity) hs.getAttribute(Const.LOGIN_USER);
        entity.setMoney(entity.getMoney()+money);
        UserEntity userEntity = new UserEntity();
        userEntity.setIuser(entity.getIuser());
        userEntity.setMoney(money);
        service.insMoney(userEntity);
        service.moneyCharge(userEntity);
    }

    @GetMapping("/forgotId")
    public String forgotId(@ModelAttribute("entity") UserEntity entity, Model model) {
        UserEntity user = utils.getLoginUser();

        if (user != null) {
            return "/main";
        }

        model.addAttribute("CONTACT_FIRST", Const.CONTACT_FIRST);
        model.addAttribute("CONTACT_SECOND", Const.CONTACT_SECOND);
        model.addAttribute("CONTACT_THIRD", Const.CONTACT_THIRD);

        return "/user/forgotId";
    }

    @PostMapping("/forgotId")
    public String forgotIdProc(UserEntity entity, Model model) {
        ForgotIdVo vo = service.forgotId(entity);
        if (vo.getForgotIdResult() == ForgotIdResult.FAILURE) {
            return "/user/forgotId.failure";
        }
        model.addAttribute(Const.User, vo);
        System.out.println(vo.getUid());
        return "/user/forgotId.success";
    }

    @GetMapping("/forgotId.success")
    public String forgotIdSuccess() {
        return "/user/forgotId.success";
    }

    @GetMapping("/forgotId.failure")
    public String forgotIdFailure() {
        return "/user/forgotId.failure";
    }

    @GetMapping("/forgotPw")
    public String forgotPw(@ModelAttribute("entity") UserEntity entity, Model model) {
        UserEntity user = utils.getLoginUser();

        if (user != null) {
            return "/main";
        }

        model.addAttribute("UID", Const.UID);
        model.addAttribute("CONTACT_FIRST", Const.CONTACT_FIRST);
        model.addAttribute("CONTACT_SECOND", Const.CONTACT_SECOND);
        model.addAttribute("CONTACT_THIRD", Const.CONTACT_THIRD);

        return "/user/forgotPw";
    }

    @PostMapping("/forgotPw")
    public String forgotPwProc(UserEntity entity) throws MessagingException {
        ForgotPwVo vo = service.forgotPw(entity);
        if (vo.getForgotPwResult() == ForgotPwResult.FAILURE) {
            return "/user/forgotPw.failure";
        }

        return "/user/forgotPw.success";
    }


}
