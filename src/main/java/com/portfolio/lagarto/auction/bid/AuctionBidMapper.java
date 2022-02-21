package com.portfolio.lagarto.auction.bid;


import com.portfolio.lagarto.model.AuctionBidVo;
import com.portfolio.lagarto.model.AuctionVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionBidMapper {

    int insAuction(AuctionBidVo vo);
    int updBid(AuctionBidVo vo);
}
