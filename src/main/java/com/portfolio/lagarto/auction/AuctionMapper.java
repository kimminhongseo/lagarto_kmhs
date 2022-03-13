package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    int insAuction(AuctionEntity entity);
    AuctionVo insAuctionList(AuctionEntity entity);

    AuctionVo selAuctionDetail(AuctionVo vo); //writernm때문에
    int addHits(AuctionVo vo);
    List<AuctionVo> selAuctionList(AuctionVo vo);
    List<AuctionVo> selAuctionListAll(AuctionVo vo);
    List<AuctionVo> selAuctionListAll4(AuctionVo vo);

    List<AuctionCategoryEntity> selAuctionCategoryList();

    List<AuctionVo> buyMyPage(AuctionVo vo); //mypage 보낼거
    List<AuctionVo> sellMyPage(AuctionVo vo);
    List<AuctionVo> sellingMyPage(AuctionVo vo);

    int imbuyclick(AuctionVo vo);

    int updAuction(AuctionVo vo);
    int delAuction(AuctionVo vo);

}
