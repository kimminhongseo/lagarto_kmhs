package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import com.portfolio.lagarto.supplies.files.SupAttachDTO;
import com.portfolio.lagarto.supplies.files.SupAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class SuppliesService {
    @Autowired private SuppliesMapper mapper;
    @Autowired private SupAttachMapper attachMapper;
    @Autowired private MyFileUtils myFileUtils;
    @Autowired private Utils utils;

    public boolean insSupplies(SuppliesEntity entity){
        entity.setIuser(utils.getLoginUserPk());
         return mapper.insSupplies(entity);
    }

    public boolean insSupplies(SuppliesEntity entity, MultipartFile[] files){
        int queryResult = 1;
        if(!this.insSupplies(entity)){
            return false;
        }
        else{
            List<SupAttachDTO> fileList = this.myFileUtils.uploadFiles2(files, entity.getIboard());
            if(!CollectionUtils.isEmpty(fileList)){
                queryResult= this.attachMapper.insertAttach(fileList);
                if(queryResult <1){
                    queryResult = 0;
                }
            }

            return  queryResult>0;



        }



    }

    public SuppliesVo insSuppliesList(SuppliesEntity entity){
        return mapper.insSuppliesList(entity);
    }
    public List<SuppliesVo> selSuppliesList(SuppliesVo vo){return  mapper.selSuppliesList(vo);}
    public List<SuppliesVo> selSuppliesListAll(SuppliesVo vo){return  mapper.selSuppliesListAll(vo);}
    public List<SupAttachDTO> getSupAttachFileList(int iboard){

        int fileTotalCount = attachMapper.selectAttachTotalCount(iboard);
        if (fileTotalCount < 1) {
            return Collections.emptyList();
        }else{
            return attachMapper.selectAttachList(iboard);
        }
       }


    public SuppliesVo selSuppliesDetail(SuppliesVo vo){ return mapper.selSuppliesDetail(vo);}

    public int delSupplies(SuppliesVo vo){
        vo.setIsdel(1);
        return mapper.delSupplies(vo);
    }

    public int cartList(SuppliesEntity entity){
        int result = mapper.cartList(entity);
        return result;
    }

    public List<SuppliesVo> myCartList(SuppliesVo vo){
        vo.setIuser(utils.getLoginUserPk());
        return mapper.myCartList(vo);
    }

    public int plusnum(SuppliesVo vo){
        vo.setIuser(utils.getLoginUserPk());
        vo.getIboard();

        return mapper.plusnum(vo);
    }

    public int minusnum(SuppliesVo vo){
        vo.setIuser(utils.getLoginUserPk());
        return mapper.minusnum(vo);
    }





}
