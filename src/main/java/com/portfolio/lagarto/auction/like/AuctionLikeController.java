package com.portfolio.lagarto.auction.like;


import com.portfolio.lagarto.customer.like.CustomerLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auction/like")
public class AuctionLikeController {

    @Autowired private AuctionLikeService service;

    @PostMapping
    public Map<String, Integer> insAuctionLike(@RequestBody AuctionLikeEntity entity){
        Map<String,Integer> result = new HashMap<>();
        result.put("result",service.insAuctionLike(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public Map<String, Integer> isLike(@PathVariable int iboard) {
        AuctionLikeEntity dbEntity = service.selAuctionLike(iboard);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", dbEntity == null ? 0 : 1);
        return result;
    }

    @DeleteMapping("/{iboard}")
    public Map<String, Integer> delCustomerLike(@PathVariable int iboard) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delAuctionLike(iboard));
        return result;
    }







}
