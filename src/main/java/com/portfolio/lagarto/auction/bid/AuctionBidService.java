package com.portfolio.lagarto.auction.bid;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.AuctionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionBidService {
    @Autowired
    private AuctionBidMapper mapper;

    @Autowired
    private Utils utils;

    public int updBid(AuctionVo vo){


        if(vo.getPrebuy()>vo.getBuy()){
            return 2; //현재 경매가보다 낮은 금액을 입력할 경우
        }
        if(vo.getImbuy()>vo.getBuy()){
            //현재 buyer=2
            //여기서 돈을 환불하고 + prebuy(이거 컬럼에 추가해야하나) >>해결방법 생각
            //returnmoney


            //이렇게 하면 buyer=1 그리고 돈을 빼낸다. - buy
            //remove

            //빼주는거 ㅇㅋ
            mapper.returnmoney(vo);

            vo.setBuyer(utils.getLoginUserPk()); //입력전의 buyer가 필요해서 service에 넣음

            mapper.removemoney(vo);
            return mapper.updBid(vo); //성공시 1
        }
        else{
            return 0; //즉시구매가보다 높을때.
        }

    }
}
