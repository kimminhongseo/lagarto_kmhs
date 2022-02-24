package com.portfolio.lagarto.auction.comment;


import com.portfolio.lagarto.customer.comment.CustomerCommentEntity;
import com.portfolio.lagarto.customer.comment.CustomerCommentService;
import com.portfolio.lagarto.customer.comment.CustomerCommentVo;
import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ajax/auctionCmt")
public class AuctionCommentRestController {

    @Autowired private AuctionCommentService service;

    @PostMapping
    public ResultVo insAuctionCmt(@RequestBody AuctionCommentEntity entity) {
        return service.insAuctionCmt(entity);
    }

    @PostMapping("mod")
    public Map<String, Integer> modBoardCmt(@RequestBody AuctionCommentEntity entity){
        System.out.println(entity);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.modBoardCmt(entity));
        return  result;
    }

    @GetMapping
    public List<AuctionCommentVo> selCustomerCmtList(AuctionCommentEntity entity) {
        return service.selAuctionCmtList(entity);
    }

    @DeleteMapping("/{icmt}")
    public Map<String, Integer> delAuctionCmt(@PathVariable int icmt) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delAuctionCmt(icmt));
        return result;
    }


}
