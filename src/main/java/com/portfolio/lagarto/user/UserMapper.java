package com.portfolio.lagarto.user;


import com.portfolio.lagarto.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int apiInsUser(UserEntity entity);
    int insUser(UserEntity entity);
    int selContactCount(UserEntity entity);
    int selUidCount(UserEntity entity);
    int isDesc(UserEntity entity);
    UserEntity selUser(UserEntity entity);
    UserEntity selApiUser(UserEntity entity);
    LoginVo loginSel(LoginVo loginVo);
    void updLastLogin(UserEntity entity);
    UserEntity passwordSel(UserDto dto);
    int passwordUpd(UserDto dto);
    UserEntity authKey(UserEntity entity);
    int nicknameCheck(String nickname);
    void informationUpd(UserEntity entity);
    ForgotIdVo selUserId(UserEntity entity);
    ForgotPwVo selUserPw(UserEntity entity);
    void updUserPw(UserEntity entity);
    void moneyCharge(UserEntity entity);
    void insMoney(UserEntity entity);
    List<UserEntity> selMoney(PageVo vo);
    int selMoneyCount(UserEntity entity);
    int reportUser(UserDto dto);

    int selUserLevel(UserEntity entity);
    void updUserLevel(UserEntity entity);

    void updLevelBar(@Param("point") int point, @Param("iuser") int iuser);
    int selFirstLogin(UserEntity entity);

    int updUser(UserEntity entity);
}