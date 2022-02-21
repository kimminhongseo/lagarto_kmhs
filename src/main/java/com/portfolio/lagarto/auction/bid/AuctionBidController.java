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
    public String bid(@RequestParam("formbid") int formbid, @RequestParam("iboard") int iboard) {
        System.out.println(formbid);
        System.out.println(iboard);
        AuctionBidVo vo = new AuctionBidVo();
        vo.setBuy(formbid);
        vo.setIboard(iboard);
        vo.setBuyer(utils.getLoginUserPk());
        service.updBid(vo);

        return "true";
    }


}




