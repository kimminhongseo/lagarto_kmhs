package com.portfolio.lagarto.follow;

import com.portfolio.lagarto.model.FollowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowMapper mapper;

    public void follow(FollowEntity entity){
        mapper.follow(entity);
    }

    public void unfollow(FollowEntity entity){
        mapper.unfollow(entity);
    }
}