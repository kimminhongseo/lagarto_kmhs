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

                if(vo.getPrebuy() >vo.getBuy()){
                    return 2; //현재 경매가보다 낮은 금액을 입력할 경우
                }


                if(vo.getImbuy()>vo.getBuy()){ //즉시구매가 보다 낮은 금액 입력 == 정상상
                mapper.updBid(vo);
                mapper.removemoney(vo);
                mapper.returnmoney(vo);

                if(vo.getPrebuyer() == vo.getIuser()){//처음 경매라면 buyer에서 빼기만
                    mapper.firstremovemoney(vo);
                }
                return 1;
            }

            else{
                return 0; //즉시구매가보다 높을때.
            }


    }

    public int checktimer(AuctionVo vo){
       return mapper.checktimer(vo);
    }

    public int bidcheck(AuctionVo vo){
        if(vo.getBuyer() == vo.getIuser()){
            return 2;
        }
        return mapper.bidcheck(vo); // bid 1로 바꿈
    }





}
