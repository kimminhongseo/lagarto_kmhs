package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService service;

    @GetMapping("/write/{icategory}")
    public String write(@ModelAttribute AuctionEntity auctionEntity){
        System.out.println(auctionEntity.getIcategory());
        //여기선 찍힘
        return "auction/write";
    }

    @PostMapping("/write")
    public String writeProc(AuctionEntity auctionEntity){
        int result = service.insAuction(auctionEntity);
        System.out.println(result); //성공하면 1
        System.out.println(auctionEntity.getIcategory()); //여기서 안넘어옴.
        return "redirect:/auction/list/" + auctionEntity.getIcategory(); //경매등록 눌렀을때 가는곳
        //
    }
    //todo: 확인해보기 list에 전체적인 내용
    @GetMapping("/list")
    public String list(AuctionDto dto, Model model){

        model.addAttribute("List",service.selAuctionListAll(dto));
        return "auction/list";
    }


    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, AuctionDto dto, Model model){
        model.addAttribute("icategory");
        model.addAttribute("List",service.selAuctionList(dto));
        dto.setIcategory(icategory);
        return "auction/list";

    }



    @GetMapping("/detail")
    public String detail(AuctionDto dto,Model model){
        model.addAttribute("Data",service.selAuctionDetail(dto));
        return "auction/detail";
    }



}
