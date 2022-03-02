package com.portfolio.lagarto.user;


import com.portfolio.lagarto.model.ForgotIdVo;
import com.portfolio.lagarto.model.PageVo;
import com.portfolio.lagarto.model.UserDto;
import com.portfolio.lagarto.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

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
    UserEntity facebookPk(UserEntity entity);
    UserEntity loginSel(UserEntity entity);
    UserEntity passwordSel(UserDto dto);
    int passwordUpd(UserDto dto);
    UserEntity authKey(UserEntity entity);
    int nicknameCheck(String nickname);
    void informationUpd(UserEntity entity);
    ForgotIdVo selUserId(UserEntity entity);
    void moneyCharge(UserEntity entity);
    void insMoney(UserEntity entity);
    List<UserEntity> selMoney(PageVo vo);
    int selMoneyCount(UserEntity entity);
    int reportUser(UserDto dto);

    int updUser(UserEntity entity);
}