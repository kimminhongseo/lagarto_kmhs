package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionEntity;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService service;

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @GetMapping("/write")
    public String write(@ModelAttribute AuctionEntity auctionEntity) {
        System.out.println(auctionEntity.getIcategory());
        //여기선 찍힘
        return "/auction/write";
    }

    @PostMapping("/write")
    public String writeProc(@ModelAttribute("auctionEntity") AuctionEntity auctionEntity){
        int result = service.insAuction(auctionEntity);
        System.out.println(auctionEntity);
        return "redirect:/auction/list/" + auctionEntity.getIcategory(); //경매등록 눌렀을때 가는곳
        //

    }

    //todo: 확인해보기 list에 전체적인 내용
    @GetMapping("/list")
    public String list(AuctionDto dto, Model model) {

        model.addAttribute("List", service.selAuctionListAll(dto));
        return "auction/list";
    }


    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, AuctionDto dto, Model model) {
        model.addAttribute("icategory");
        model.addAttribute("List", service.selAuctionList(dto));
        dto.setIcategory(icategory);
        return "auction/list";

    }


    @GetMapping("/detail")
    public String detail(AuctionDto dto, Model model) {
        model.addAttribute("Data", service.selAuctionDetail(dto));
        return "auction/detail";
    }



}





