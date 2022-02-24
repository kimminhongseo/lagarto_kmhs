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

            if(vo.getBuy() > mapper.findmoney(vo)){ //잔액부족일때.
                return 3;
            }

            if(vo.getPrebuy()>vo.getBuy()){
                return 2; //현재 경매가보다 낮은 금액을 입력할 경우
            }

            if(vo.getImbuy()>vo.getBuy()){ //즉시구매가 보다 낮은 금액 입력 == 정상상

                if(vo.getPrebuyer() == 0) {
                    //처음올리면 prebuyer는 0으로 세팅 되어있으므로, 0일때는 prebuyer는 놔두고
                    //buyer의 money에서만 차감

                    mapper.removemoney(vo);
                }
                else{ //처음 올린게 아니고 경매 갱신하하는상황이면

                    mapper.returnmoney(vo); //이건 전에 사람에게 돌려주는 것. prebuyer!=0
                    mapper.removemoney(vo);
                }

                return mapper.updBid(vo); //성공시 1
            }
            else{
                return 0; //즉시구매가보다 높을때.
            }





    }
}
