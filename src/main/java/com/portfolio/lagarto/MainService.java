package com.portfolio.lagarto;

import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    private MainMapper mapper;

    public List<AuctionVo> selMainAuctionList(AuctionVo auctionVo) {
        return mapper.selMainAuctionList(auctionVo);
    }

    public List<SuppliesVo> selMainSuppliesList(SuppliesVo suppliesVo) {
        return mapper.selMainSuppliesList(suppliesVo);
    }

    public List<SuppliesVo> selMainTopSuppliesList(SuppliesVo suppliesVo) {
        return mapper.selMainTopSuppliesList(suppliesVo);
    }
}
