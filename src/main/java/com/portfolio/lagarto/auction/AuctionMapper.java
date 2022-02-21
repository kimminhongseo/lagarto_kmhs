package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    int insAuction(AuctionEntity entity);
    AuctionVo insAuctionList(AuctionEntity entity);

    AuctionVo selAuctionDetail(AuctionVo vo); //writernm때문에
    List<AuctionVo> selAuctionList(AuctionVo vo);
    List<AuctionVo> selAuctionListAll(AuctionVo vo);
    List<AuctionCategoryEntity> selAuctionCategoryList();

    int updAuction(AuctionVo vo);
    int delAuction(AuctionVo vo);

    AuctionBidVo insBid(AuctionBidEntity entity);
    AuctionBidVo BidList(AuctionBidEntity entity);

}
