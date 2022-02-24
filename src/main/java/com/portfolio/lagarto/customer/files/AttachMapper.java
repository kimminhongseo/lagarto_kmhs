package com.portfolio.lagarto.customer.files;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachMapper {
    int insertAttach(List<AttachDTO> attachList);

    AttachDTO selectAttachDetail(int idx);

    int deleteAttach(int boardIdx);

    List<AttachDTO> selectAttachList(int iboard);

    int selectAttachTotalCount(int iboard);
}
