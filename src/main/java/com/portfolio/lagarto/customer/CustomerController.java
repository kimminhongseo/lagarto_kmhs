package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.customer.comment.CustomerCommentService;
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

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public CustomerService service;

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

//        try {
            boolean isRegistered = this.service.insCustomer(entity, files);
//            if (!isRegistered) {
//                return this.showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
//            }
//        } catch (DataAccessException var6) {
//            return this.showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
//        } catch (Exception var7) {
//            return this.showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
//        }

        return "redirect:/customer/list" + entity.getBoard_cd();
    }

    @GetMapping("/detail")
    public void detail(Model model, CustomerDto dto) {
        model.addAttribute("data", service.selCustomerDetail(dto));
    }

    @GetMapping("/detail_item")
    public void selCustomerDetail(Model model, CustomerDto dto) {
        model.addAttribute("data", service.selCustomerDetail(dto));
    }



    @GetMapping("/del")
    public String delProc(CustomerEntity entity){
        int result = service.delCustomer(entity);
        if(result == 1) {
            return "redirect:/customer/list/" + entity.getBoard_cd();
        }
        return "customer/list/1";
    }
}
