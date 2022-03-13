package com.portfolio.lagarto.auction.bid;



import com.portfolio.lagarto.model.AuctionVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionBidMapper {

    void returnmoney(AuctionVo vo);
    void removemoney(AuctionVo vo);
    void firstremovemoney(AuctionVo vo);
    void updBid(AuctionVo vo);
    int findmoney(AuctionVo vo);
    int checktimer(AuctionVo vo);
    void imbuy(AuctionVo vo);
    int bidcheck(AuctionVo vo);
}
