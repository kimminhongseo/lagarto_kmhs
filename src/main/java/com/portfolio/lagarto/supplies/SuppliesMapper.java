package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuppliesMapper {
    int insSupplies(SuppliesEntity entity);
    SuppliesVo insSuppliesList(SuppliesEntity entity);
}
