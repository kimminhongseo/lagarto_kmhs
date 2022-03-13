package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.Const;
import com.portfolio.lagarto.Criteria;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.auction.AuctionService;
import com.portfolio.lagarto.customer.comment.CustomerCommentService;
import com.portfolio.lagarto.customer.files.AttachFileException;
import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.CustomerDto;
import com.portfolio.lagarto.model.CustomerEntity;
import com.portfolio.lagarto.model.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired public CustomerService service;
    @Autowired public CustomerCommentService cmtService;
    @Autowired public AuctionService auctionService;
    @Autowired public Utils utils;

    @GetMapping("/list/{board_cd}")
    public String list(@PathVariable int board_cd, @ModelAttribute("params") TestDto dto, Model model) {
        model.addAttribute("board_cd", board_cd);
        model.addAttribute("list", service.selCustomerList(dto));
        model.addAttribute("loginUser",utils.getLoginUserPk());
        model.addAttribute("notice", service.selNoticeList(dto));
        dto.setBoard_cd(board_cd);
        return "customer/list";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("entity") CustomerEntity entity, Model model) {
        model.addAttribute("loginUser",utils.getLoginUserPk());
        return "customer/write";
    }

    @PostMapping("/write")
    public String writeProc(CustomerEntity entity, MultipartFile[] files) {
        boolean isRegistered = this.service.insCustomer(entity, files);
        //TODO
        return "redirect:/customer/detail?iboard=" + entity.getIboard();
    }

    @GetMapping("/detail")
    public void detail(Model model, CustomerDto dto, int iboard) {
        model.addAttribute("fileList", service.getAttachFileList(iboard));
        model.addAttribute("data", service.selCustomerDetail(dto));
    }

    @GetMapping("/upd")
    public String upd(CustomerDto dto, @RequestParam(value = "iboard", required = false) int iboard ,Model model) {
        model.addAttribute("data", service.selCustomerDetail(dto));
        model.addAttribute("fileList", service.getAttachFileList(iboard));
        return "customer/upd";
    }

    @PostMapping("/upd")
    public String updProc(CustomerDto dto, MultipartFile[] files) {
        service.updCustomer(dto);
        service.updCustomer(dto, files);
        return "redirect:/customer/detail?iboard=" + dto.getIboard();
    }

    @GetMapping("/del")
    public String delProc(CustomerEntity entity){
        cmtService.delCustomerCmtAll(entity.getIboard());
        int result = service.delCustomer(entity);
        if(result == 1) {
            return "redirect:/customer/list/1";
        }
        return "customer/list";
    }
}
