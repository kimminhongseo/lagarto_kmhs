package com.portfolio.lagarto.auction.comment;


import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.customer.comment.CustomerCommentEntity;
import com.portfolio.lagarto.customer.comment.CustomerCommentService;
import com.portfolio.lagarto.customer.comment.CustomerCommentVo;
import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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


    @GetMapping
    public List<AuctionCommentVo> selAuctionCmtList(AuctionCommentEntity entity, HttpSession hs) {
        Object object = hs.getAttribute(Const.LOGIN_USER);
        return service.selAuctionCmtList(entity);
    }


    @PostMapping("mod")
    public Map<String, Integer> modAuctionCmt(@RequestBody AuctionCommentEntity entity){
        System.out.println(entity);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.updAuctionCmt(entity));
        return  result;
    }



    @PutMapping
    public Map<String, Integer> updAuctionCmt(@RequestBody AuctionCommentEntity entity){
        Map<String, Integer> result = new HashMap<>();
        result.put("result",service.updAuctionCmt(entity));
        return result;
    }

    @DeleteMapping("/{icmt}")
    public Map<String, Integer> delAuctionCmt(@PathVariable int icmt ) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delAuctionCmt(icmt));
        return result;
    }


}
