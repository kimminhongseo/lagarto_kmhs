package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
