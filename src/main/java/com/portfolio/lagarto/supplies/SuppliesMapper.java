package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.CartEntity;
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

    //cartlist - 중복방지
    List<CartEntity> overlap(CartEntity entity);


    //cartlist - 장바구니로 삽입
    int cartList(SuppliesEntity entity);

    List<SuppliesVo> myCartList(SuppliesVo vo);
    List<SuppliesVo> cartmoney(SuppliesVo vo);

    int plusnum(SuppliesVo vo);
    int minusnum(SuppliesVo vo);
    int balancenum(SuppliesVo vo);

    int delcart(SuppliesVo vo);


}
