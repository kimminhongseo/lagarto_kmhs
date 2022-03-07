package com.portfolio.lagarto.auction.comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionCommentMapper {
    int insAuctionCmt(AuctionCommentEntity entity);
    List<AuctionCommentVo> selAuctionCmtList(AuctionCommentEntity entity);



    int updAuctionCmt(AuctionCommentEntity entity);

    int delAuctionCmt(AuctionCommentEntity entity);


}
