package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.model.FollowEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    void follow(FollowEntity entity);
    void unfollow(FollowEntity entity);
    //팔로우 유무
    int isFollow(FollowEntity entity);

    //팔로우 리스트 조회
    List<FollowEntity> FollowList(FollowEntity entity);

    //팔로워 리스트 조회
    List<FollowEntity> FollowingList(FollowEntity entity);
}