package com.portfolio.lagarto.auction;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.AuctionCategoryEntity;
import com.portfolio.lagarto.model.AuctionDto;
import com.portfolio.lagarto.model.AuctionEntity;
import com.portfolio.lagarto.model.AuctionVo;
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
        return mapper.insAuction(entity);
    }
    public AuctionVo insAuctionList(AuctionEntity entity){
       if(mapper.insAuctionList(entity) == null){
           return null;
       }return mapper.insAuctionList(entity);
    }

    public List<AuctionVo> selAuctionList(AuctionDto dto){return  mapper.selAuctionList(dto);}
    public List<AuctionVo> selAuctionListAll(AuctionDto dto){return  mapper.selAuctionListAll(dto);}

    public AuctionVo selAuctionDetail (AuctionDto dto){return mapper.selAuctionDetail(dto);}

    public List<AuctionCategoryEntity> auctionMenuList(){return  mapper.selAuctionCategoryList();}



}