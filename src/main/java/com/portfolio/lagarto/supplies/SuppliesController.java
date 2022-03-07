package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/supplies")
public class SuppliesController {

    @Autowired
    private SuppliesService service;

    @GetMapping("/write")
    public String write(@ModelAttribute("suppliesEntity") SuppliesEntity suppliesEntity){
        System.out.println(suppliesEntity.getIcategory());

        return  "/supplies/write";
    }

    @PostMapping("/write")
    public String writeProc( SuppliesEntity entity, MultipartFile[] files){
       boolean isRegistered= this.service.insSupplies(entity,files);
       //난 왜 안뜨는것임?
        System.out.println(entity.getIboard());
        return "redirect:/supplies/list";
    }


    @GetMapping("/list")
    public String list(SuppliesVo vo, Model model){
        model.addAttribute("List",service.selSuppliesListAll(vo));
        return "supplies/list";
    }

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, SuppliesVo vo, Model model){
        model.addAttribute("icategory");
        model.addAttribute("List",service.selSuppliesList(vo));
        vo.setIcategory(icategory);
        return "supplies/list";
    }


    @GetMapping("/detail")
    public void detail(SuppliesVo vo, Model model, int iboard, HttpSession hs){
        model.addAttribute("Data",service.selSuppliesDetail(vo));
        model.addAttribute("fileList",service.getSupAttachFileList(iboard));
        model.addAttribute("login",hs.getAttribute(Const.LOGIN_USER));
    }


    @GetMapping("/del")
    public String delProc(SuppliesVo vo){
        service.delSupplies(vo);
        return "redirect:/supplies/list/";
    }













}
