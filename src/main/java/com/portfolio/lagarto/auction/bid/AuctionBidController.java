package com.portfolio.lagarto.auction.bid;


import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.auction.AuctionService;


import com.portfolio.lagarto.model.AuctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ajax/auctionBid")
public class AuctionBidController {


    @Autowired
    private AuctionBidService service;



    @Autowired
    private Utils utils;



    @PostMapping("/buy")
    public int bid(@RequestParam("formbid") int formbid, @RequestParam("iboard") int iboard,
                      @RequestParam("formimbuy") int formimbuy, @RequestParam("formbuy") int formbuy,
                    @RequestParam("prebuyer") int prebuyer, @RequestParam("iuser")int iuser) {
        AuctionVo vo = new AuctionVo();

        vo.setBuy(formbid); //경매희망값
        vo.setImbuy(formimbuy); //즉시구매
        vo.setIboard(iboard);
        vo.setIuser(iuser); //글쓴이!
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

    @GetMapping("/timer") //시간재기
    public int time (@RequestParam("iboard") int iboard,@RequestParam("mdt") int mdt)
    {
        AuctionVo vo = new AuctionVo();
        vo.setIboard(iboard);


      return service.checktimer(vo);
    }

    @PutMapping() //낙찰 유무
    public Map<String, Integer> bidcheck (@RequestBody AuctionVo vo){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.bidcheck(vo));
        return result;
    }





}




