package com.portfolio.lagarto.auction.bid;


import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.auction.AuctionService;

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
                      @RequestParam("formimbuy") int formimbuy, @RequestParam("formbuy") int formbuy,
                    @RequestParam("prebuyer") int prebuyer) {
        AuctionVo vo = new AuctionVo();

        vo.setBuy(formbid); //경매희망값
        vo.setImbuy(formimbuy); //즉시구매
        vo.setIboard(iboard);

        vo.setPrebuy(formbuy); //현재 등록된값
        vo.setPrebuyer(prebuyer); //현재가 올린 그 사람. = 예전사람

        vo.setBuyer(utils.getLoginUserPk()); // 현재 로그인한 사람

        return service.updBid(vo);

    }

    //prebuyer는 현재 등록된 buyer의 iuser값
    @PostMapping("/return") //돈 돌려주는거 ajax쓸때.
    public int returnmoney(@RequestParam("formbid") int formbid, @RequestParam("formbuy") int formbuy,
                           @RequestParam("iboard") int iboard, @RequestParam("prebuyer") int prebuyer)
    {

        return 1;
    }




}




