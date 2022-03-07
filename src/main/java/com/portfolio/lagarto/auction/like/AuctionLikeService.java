package com.portfolio.lagarto.auction.like;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.customer.like.CustomerLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionLikeService {
    @Autowired private AuctionLikeMapper mapper;
    @Autowired private Utils utils;

    public int insAuctionLike(AuctionLikeEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        return mapper.insAuctionLike(entity);
    }

    public AuctionLikeEntity selAuctionLike(int iboard) {
        return mapper.selAuctionLike(createCustomerLikeEntity(iboard));
    }

    public int delAuctionLike(int iboard) {
        return mapper.delAuctionLike(createCustomerLikeEntity(iboard));
    }

    private AuctionLikeEntity createCustomerLikeEntity(int iboard) {
        AuctionLikeEntity entity = new AuctionLikeEntity();
        entity.setIboard(iboard);
        entity.setIuser(utils.getLoginUserPk());
        return entity;
    }

}
