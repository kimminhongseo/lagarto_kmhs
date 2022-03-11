package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuppliesMapper {
    boolean insSupplies(SuppliesEntity entity);
    SuppliesVo insSuppliesList(SuppliesEntity entity);

    List<SuppliesVo>  selSuppliesList(SuppliesVo vo);
    List<SuppliesVo>  selSuppliesListAll(SuppliesVo vo);

    SuppliesVo selSuppliesDetail(SuppliesVo vo);

    int delSupplies(SuppliesVo vo);

    //cartlist

    int cartList(SuppliesEntity entity);


    List<SuppliesVo> myCartList(SuppliesVo vo);

    int plusnum(SuppliesVo vo);
    int minusnum(SuppliesVo vo);


}
