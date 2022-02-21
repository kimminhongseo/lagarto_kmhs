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

    public int updBid(AuctionBidVo vo){
        System.out.println(vo.getPrebuy());
        System.out.println(vo.getBuy());
        System.out.println(vo.getImbuy());

        if(vo.getPrebuy()>vo.getBuy()){
            return 2; //현재 경매가보다 낮은 금액을 입력할 경우
        }
        if(vo.getImbuy()>vo.getBuy()){
            return mapper.updBid(vo); //성공시 1
        }
        else{
            return 0; //즉시구매가보다 높을때.
        }

    }
}
