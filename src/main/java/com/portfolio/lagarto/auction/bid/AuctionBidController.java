package com.portfolio.lagarto.auction.bid;


import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.auction.AuctionService;
import com.portfolio.lagarto.model.AuctionBidEntity;
import com.portfolio.lagarto.model.AuctionBidVo;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/ajax/auctionBid")
public class AuctionBidController {


    @Autowired
    private AuctionBidService service;

    @Autowired
    private AuctionService service1;

    @Autowired
    private Utils utils;



    @PostMapping("/buy")
    public int bid(@RequestParam int formbid ) {
        AuctionBidVo vo = new AuctionBidVo();
        vo.setBuyer(utils.getLoginUserPk());
        vo.setBuy(formbid);
        try {
            return service.insAuction(vo);
        } catch (Exception e) {
            return 0;
        }
    }


}




