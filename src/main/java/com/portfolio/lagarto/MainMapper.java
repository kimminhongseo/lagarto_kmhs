package com.portfolio.lagarto;

import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<AuctionVo> selMainAuctionList(AuctionVo auctionVo);
    List<SuppliesVo> selMainSuppliesList(SuppliesVo suppliesVo);
    List<SuppliesVo> selMainTopSuppliesList(SuppliesVo suppliesVo);
}
