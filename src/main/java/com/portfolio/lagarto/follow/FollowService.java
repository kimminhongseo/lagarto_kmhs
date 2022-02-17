package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.FollowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowMapper mapper;
    @Autowired
    private Utils utils;

    public void follow(FollowEntity entity){
        mapper.follow(entity);
    }

    public void unfollow(FollowEntity entity){
        mapper.unfollow(entity);
    }

    public int isFollow(FollowEntity entity){
        return mapper.isFollow(entity);
    }

    public List<FollowEntity> FollowList(){
        FollowEntity entity = new FollowEntity();
        entity.setIuserMe(utils.getLoginUserPk());
        return mapper.FollowList(entity);
    }

    public List<FollowEntity> FollowingList(){
        FollowEntity entity = new FollowEntity();
        entity.setIuserYou(utils.getLoginUserPk());
        return mapper.FollowingList(entity);
    }
}