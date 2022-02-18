package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    private AuctionMapper mapper;


    @Autowired
    private Utils utils;


    public int insAuction(AuctionEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.insAuction(entity);
    }

    public AuctionVo insAuctionList(AuctionEntity entity){
      return mapper.insAuctionList(entity);
    }

    public List<AuctionVo> selAuctionList(AuctionVo dto){return  mapper.selAuctionList(dto);}
    public List<AuctionVo> selAuctionListAll(AuctionVo dto){return  mapper.selAuctionListAll(dto);}

    public AuctionVo selAuctionDetail (AuctionVo dto){return mapper.selAuctionDetail(dto);}

    public int updAuction(AuctionEntity entity){
        try{
            entity.setIuser(utils.getLoginUserPk());
            return mapper.updAuction(entity);
        }catch (Exception e){
            e.printStackTrace();
            return 2; // update안되면 0
        }
    }

    public int delAuction(AuctionEntity entity){
        entity.setIsdel(1);
        return mapper.delAuction(entity);
    }

    public List<AuctionCategoryEntity> auctionMenuList(){return  mapper.selAuctionCategoryList();}


    public AuctionBidVo insBid(AuctionBidEntity entity){
        return mapper.insBid(entity);
    }

    public AuctionBidVo bidList(AuctionBidEntity entity){
        return mapper.BidList(entity);
    }





}