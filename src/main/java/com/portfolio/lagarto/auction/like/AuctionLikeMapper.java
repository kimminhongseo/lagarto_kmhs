package com.portfolio.lagarto.auction.like;

import com.portfolio.lagarto.customer.like.CustomerLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionLikeMapper {

    int insAuctionLike(AuctionLikeEntity entity);
    AuctionLikeEntity selAuctionLike(AuctionLikeEntity entity);
    int delAuctionLike(AuctionLikeEntity entity);
}
