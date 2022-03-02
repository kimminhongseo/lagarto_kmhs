package com.portfolio.lagarto.supplies.files;

import com.portfolio.lagarto.customer.files.AttachDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupAttachMapper {

    int insertAttach(List<SupAttachDTO> attachList);

    List<SupAttachDTO> selectAttachList(int iboard);

    int selectAttachTotalCount(int iboard);


}
