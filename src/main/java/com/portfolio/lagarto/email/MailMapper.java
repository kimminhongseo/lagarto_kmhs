package com.portfolio.lagarto.email;


import com.portfolio.lagarto.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.social.facebook.api.User;

@Mapper
public interface MailMapper {
    int authTrue(UserEntity entity);
    UserEntity selUser(UserEntity entity);
}
