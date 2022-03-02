package com.portfolio.lagarto.user;



import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.enums.ForgotIdResult;
import com.portfolio.lagarto.enums.ForgotPwResult;
import com.portfolio.lagarto.enums.JoinResult;
import com.portfolio.lagarto.model.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private Utils utils;
    @Autowired
    private HttpSession hs;

    @Autowired
    private JavaMailSender javaMailSender;


    public int apiInsUser(UserEntity entity){
        UserEntity result = null;
        try {
            result = mapper.selUser(entity);
            if (result == null){
                mapper.apiInsUser(entity);
                result = mapper.selUser(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        hs.setAttribute(Const.LOGIN_USER, result);
        return 0;
    }

    public int insUser(UserEntity entity) {
        UserEntity copyEntity = new UserEntity();
        BeanUtils.copyProperties(entity, copyEntity);


        // 필수 약관동의 체크
        if (!(copyEntity.getDisc_agree_a() == 1 && copyEntity.getDisc_agree_b() == 1)) {
            System.out.println("약관 error");
            copyEntity.setResult(JoinResult.FAILURE);
            return 0;
        }

        // 아이디 정규식 체크
        if (!Const.checkUid(copyEntity.getUid())) {
            System.out.println("아이디 정규식 error");
            copyEntity.setResult(JoinResult.FAILURE);
            return 0;
        }

        // 아이디 중복 체크
        if (mapper.selUidCount(copyEntity) > 0) {
            System.out.println("중복 error");
            copyEntity.setResult(JoinResult.DUPLICATE_EMAIL);
            return 0;
        }

        // 전화번호 중복 체크
        if (mapper.selContactCount(copyEntity) > 0) {
            System.out.println("번호 error");
            copyEntity.setResult(JoinResult.DUPLICATE_CONTACT);
            return 0;
        }

        String hashUpw = BCrypt.hashpw(copyEntity.getUpw(), BCrypt.gensalt());
        copyEntity.setUpw(hashUpw);
        copyEntity.setPlatform_cd(Const.Platform.GENERAL);
        System.out.println(hashUpw);

        copyEntity.setResult(JoinResult.SUCCESS);
        return mapper.insUser(copyEntity);
    }

    public int apiJoin(UserEntity entity) {
        entity.setDisc_agree_b(1);
        entity.setDisc_agree_b(1);
        entity.setDisc_agree_c(0);
        entity.setResult(JoinResult.SUCCESS);
        return mapper.insUser(entity);
    }

    public int contactCheck(UserEntity entity) {
        int result = mapper.selContactCount(entity);

        if (result > 0) {
            entity.setResult(JoinResult.DUPLICATE_CONTACT);
            return result;
        }

        entity.setResult(JoinResult.AVAILABLE_CONTACT);
        return result;
    }

    public int uidCheck(UserEntity entity) {
        int result = mapper.selUidCount(entity);

        if (result > 0) {
            entity.setResult(JoinResult.DUPLICATE_EMAIL);
        }

        return result;
    }

    public LoginVo loginSel(LoginVo vo){
        LoginVo dbUser = null;
        try {
            dbUser = mapper.loginSel(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;   // 알 수 없는 에러
        }
        if(dbUser != null) {
            if(BCrypt.checkpw(vo.getUpw(), dbUser.getUpw())) {
                dbUser.setUpw(null);
                utils.setLoginUser(dbUser);
                return dbUser;   // 로그인 성공
            }
        }
        return null;   //로그인 실패
    }

    public void putAutoSaveKey(LoginVo loginVo, HttpServletResponse httpServletResponse) {
        Cookie rememberCookie = new Cookie("auto_id_check", loginVo.getUid());
        rememberCookie.setPath("/");
        if(loginVo.isAuto_id_check()) {
            rememberCookie.setMaxAge(60*60*24*7);
        } else {
            rememberCookie.setMaxAge(0);
        }
        httpServletResponse.addCookie(rememberCookie);
    }

    public void updLastLogin(UserEntity entity) {
        mapper.updLastLogin(entity);
    }

    public int selUserResult(UserEntity entity){
        UserEntity result = mapper.selUser(entity);
        return result == null ? 0 : 1;
    }

    public UserEntity selUser(UserEntity entity){
        return mapper.selUser(entity);
    }

    public UserEntity selApiUser(UserEntity entity) {
        return mapper.selApiUser(entity);
    }

    public UserEntity facebookIns(UserEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.facebookPk(entity);
    }


    public int passwordSel(UserDto dto){
        UserEntity dbUpw = mapper.passwordSel(dto);
        if(BCrypt.checkpw(dto.getUpw(), dbUpw.getUpw())){
            dto.setNewUpw(BCrypt.hashpw(dto.getNewUpw(), BCrypt.gensalt()));
            return mapper.passwordUpd(dto);
        }
        return 0;
    }

    public UserEntity authKey(UserEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.authKey(entity);
    }

    public int nicknameCheck(String nickname){
        int result = mapper.nicknameCheck(nickname);
        return result;
    }

    public void informationUpd(UserEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        mapper.informationUpd(entity);
    }

    public void moneyCharge(UserEntity entity) {
        mapper.moneyCharge(entity);
    }

    public ForgotIdVo forgotId(UserEntity entity) {
        ForgotIdVo forgotIdVo = mapper.selUserId(entity);

        if (forgotIdVo == null) {
            forgotIdVo = new ForgotIdVo();
            forgotIdVo.setForgotIdResult(ForgotIdResult.FAILURE);
            return forgotIdVo;
        }

        forgotIdVo.setForgotIdResult(ForgotIdResult.SUCCESS);

        String hid = Utils.hideEmail(forgotIdVo.getUid(), forgotIdVo.getPlatform_cd());
        forgotIdVo.setUid(hid);

        return forgotIdVo;
    }

    public ForgotPwVo forgotPw(UserEntity entity) throws MessagingException {
        ForgotPwVo forgotPwVo = mapper.selUserPw(entity);

        if (forgotPwVo == null || forgotPwVo.getPlatform_cd() != 1) {
            forgotPwVo = new ForgotPwVo();
            forgotPwVo.setForgotPwResult(ForgotPwResult.FAILURE);
            return forgotPwVo;
        }

        forgotPwVo.setForgotPwResult(ForgotPwResult.SUCCESS);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessageHelper.setTo(forgotPwVo.getUid());

        int size = (int) ((Math.random() * (16 - 10)) + 10);
        String tempPw = Utils.tempPw(size);

        mimeMessageHelper.setSubject("[LAGARTO] 임시 비밀번호 발급");
        mimeMessageHelper.setText(String.format("%s<br>%s<br>%s<br>%s%s%s",
                "<h1>[LAGARTO]  임시 비밀번호 발급</h1>",
                "<p>임시 비밀번호를 발급해 드립니다.</p>",
                "<p>로그인 시 아래의 비밀번호를 이용해 주시고, 로그인 후 비밀번호 변경을 권장드립니다.</p>",
                "<h3>", tempPw, "</h3>"
        ), true);

        javaMailSender.send(mimeMessage);

        UserEntity copyEntity = new UserEntity();
        String hashTempPw = BCrypt.hashpw(tempPw, BCrypt.gensalt());
        copyEntity.setUid(forgotPwVo.getUid());
        copyEntity.setUpw(hashTempPw);
        System.out.println(hashTempPw);

        mapper.updUserPw(copyEntity);

        return forgotPwVo;
    }

    public void insMoney(UserEntity entity){
        mapper.insMoney(entity);
    }

    public List<UserEntity> selMoney(PageVo vo){
        return mapper.selMoney(vo);
    }

    public int selMoneyCount(UserEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.selMoneyCount(entity);
    }

    public UserEntity selUserLevel(UserEntity entity) {
        return mapper.selUserLevel(entity);
    }

    public void updUserLevel(UserEntity entity) {
        mapper.updUserLevel(entity);
    }

    public void updLevelBar(int point, UserEntity entity) {
        mapper.updLevelBar(point, entity);
    }

    public int selFirstLogin(UserEntity entity) {
        return mapper.selFirstLogin(entity);
    }
}