package com.portfolio.lagarto.auction.bid;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.AuctionBidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionBidService {
    @Autowired
    private AuctionBidMapper mapper;

    @Autowired
    private Utils utils;


    public int insAuction(AuctionBidVo vo){
        vo.setBuyer(utils.getLoginUserPk());
        return mapper.insAuction(vo);
    }

    public void updBid(AuctionBidVo vo){
        mapper.updBid(vo);
    }
}
