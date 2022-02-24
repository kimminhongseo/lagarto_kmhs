package com.portfolio.lagarto.auction.comment;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.customer.comment.CustomerCommentEntity;
import com.portfolio.lagarto.customer.comment.CustomerCommentMapper;
import com.portfolio.lagarto.customer.comment.CustomerCommentVo;
import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionCommentService {
    @Autowired
    private AuctionCommentMapper mapper;
    @Autowired private Utils utils;

    public ResultVo insAuctionCmt(AuctionCommentEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        int result = mapper.insAuctionCmt(entity);
        return new ResultVo(result);
    }

    public List<AuctionCommentVo> selAuctionCmtList(AuctionCommentEntity entity) {
        return mapper.selAuctionCmtList(entity);
    }

    public int delAuctionCmt(int icmt) {
        AuctionCommentEntity entity = new AuctionCommentEntity();
        entity.setIcmt(icmt);
        entity.setIuser(utils.getLoginUserPk());
        return mapper.delAuctionCmt(entity);
    }
    public  int modBoardCmt(AuctionCommentEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        entity.getIcmt();
        return mapper.modBoardCmt(entity);
    }


    public int delAuctionCmtAll(int iboard) {
        AuctionCommentEntity entity = new AuctionCommentEntity();
        entity.setIboard(iboard);
        return mapper.delAuctionCmtAll(entity);
    }

}
