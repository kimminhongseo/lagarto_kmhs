package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AuctionService {
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss");
    long nowTime = System.currentTimeMillis(); //현재시간
    String timestr = timeFormat.format(nowTime);



    @Autowired
    private AuctionMapper mapper;


    @Autowired
    private Utils utils;


    public int insAuction(AuctionEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        entity.setBuyer(utils.getLoginUserPk());
        return mapper.insAuction(entity);
    }

    public AuctionVo insAuctionList(AuctionEntity entity){
      return mapper.insAuctionList(entity);
    }


    public List<AuctionVo> selAuctionList(AuctionVo vo){

        return mapper.selAuctionList(vo);
    }
    public List<AuctionVo> selAuctionListAll(AuctionVo vo){
        return  mapper.selAuctionListAll(vo);}



    public AuctionVo selAuctionDetail (AuctionVo vo){
        AuctionVo detail = mapper.selAuctionDetail(vo);
        int hitsResult = mapper.addHits(vo);
        if(hitsResult == 1){
            detail.setHits(detail.getHits()+1);
        }

        System.out.println("detail에서 마감시간? : "+detail.getMdt());
        System.out.println("지금 시간은? (문자) :" + timestr);

        return detail;
    }

    public int updAuction(AuctionVo vo){
        try{
            vo.setIuser(utils.getLoginUserPk());
            return mapper.updAuction(vo);
        }catch (Exception e){
            e.printStackTrace();
            return 2; // update안되면 0
        }
    }

    public int delAuction(AuctionVo vo){
        vo.setIsdel(1);
        return mapper.delAuction(vo);
    }

    public List<AuctionCategoryEntity> auctionMenuList(){return  mapper.selAuctionCategoryList();}






}