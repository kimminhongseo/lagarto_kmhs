package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliesService {
    @Autowired
    private SuppliesMapper mapper;

    @Autowired
    private Utils utils;

    public int insSupplies(SuppliesEntity entity){
        entity.setIuser(utils.getLoginUserPk());
        return mapper.insSupplies(entity);
    }

    public SuppliesVo insSuppliesList(SuppliesEntity entity){
        return mapper.insSuppliesList(entity);
    }

    public List<SuppliesVo> selSuppliesList(SuppliesVo vo){return  mapper.selSuppliesList(vo);}

    public List<SuppliesVo> selSuppliesListAll(SuppliesVo vo){return  mapper.selSuppliesListAll(vo);}

    public SuppliesVo selSuppliesDetail(SuppliesVo vo){ return mapper.selSuppliesDetail(vo);}

    public int delSupplies(SuppliesVo vo){
        vo.setIsdel(1);
        return mapper.delSupplies(vo);
    }





}
