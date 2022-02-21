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
    public int bid(@RequestParam("formbid") int formbid, @RequestParam("iboard") int iboard,
                      @RequestParam("formimbuy") int formimbuy, @RequestParam("formbuy") int formbuy) {
        AuctionBidVo vo = new AuctionBidVo();
        vo.setPrebuy(formbuy); //현재
        vo.setBuy(formbid); //적은값
        vo.setImbuy(formimbuy); //즉시구매
        vo.setIboard(iboard);
        vo.setBuyer(utils.getLoginUserPk());

        return service.updBid(vo);


    }


}




