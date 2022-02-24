package com.portfolio.lagarto.auction.comment;

import com.portfolio.lagarto.customer.comment.CustomerCommentEntity;
import com.portfolio.lagarto.customer.comment.CustomerCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionCommentMapper {
    int insAuctionCmt(AuctionCommentEntity entity);
    List<AuctionCommentVo> selAuctionCmtList(AuctionCommentEntity entity);

    int modBoardCmt(AuctionCommentEntity entity);
    int delAuctionCmt(AuctionCommentEntity entity);
    int delAuctionCmtAll(AuctionCommentEntity entity);

}
