package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.customer.comment.CustomerCommentService;
import com.portfolio.lagarto.customer.files.AttachDTO;
import com.portfolio.lagarto.customer.files.AttachFileException;
import com.portfolio.lagarto.model.AuctionEntity;
import com.portfolio.lagarto.model.CustomerDto;
import com.portfolio.lagarto.model.CustomerEntity;
import com.portfolio.lagarto.model.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired public CustomerService service;
    @Autowired public CustomerCommentService cmtService;

    @GetMapping("/list/{board_cd}")
    public String list(@PathVariable int board_cd, CustomerDto dto, Model model) {
        model.addAttribute("board_cd", board_cd);
        model.addAttribute("list", service.selCustomerList(dto));
        dto.setBoard_cd(board_cd);
        return "customer/list";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("entity") CustomerEntity entity) {
        return "customer/write";
    }

    @PostMapping("/write")
    public String writeProc(CustomerEntity entity, MultipartFile[] files, Model model) {
        boolean isRegistered = this.service.insCustomer(entity, files);
        return "redirect:/customer/list/" + entity.getBoard_cd();
    }

    @GetMapping("/detail")
    public void detail(Model model, CustomerDto dto) {
        model.addAttribute("data", service.selCustomerDetail(dto));

    }

    @GetMapping("/detail_item")
    public void selCustomerDetail(Model model, CustomerDto dto, int iboard) {
        model.addAttribute("fileList", service.getAttachFileList(iboard));
        model.addAttribute("data", service.selCustomerDetail(dto));
    }



    @GetMapping("/del")
    public String delProc(CustomerEntity entity){
        int result = service.delCustomer(entity);
        if(result == 1) {
            return "redirect:/customer/list/" + entity.getBoard_cd();
        }
        return "customer/list";
    }
}
