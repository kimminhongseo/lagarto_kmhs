package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    int insAuction(AuctionEntity entity);
    AuctionVo insAuctionList(AuctionEntity entity);

    AuctionVo selAuctionDetail(AuctionDto dto); //writernm때문에
    List<AuctionVo> selAuctionList(AuctionDto dto);
    List<AuctionVo> selAuctionListAll(AuctionDto dto);
    List<AuctionCategoryEntity> selAuctionCategoryList();

    int updAuction(AuctionEntity entity);
    int delAuction(AuctionEntity entity);

    AuctionBidVo insBid(AuctionBidEntity entity);
    AuctionBidVo BidList(AuctionBidEntity entity);

}
