package com.portfolio.lagarto.customer.files;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachMapper {
    int insertAttach(List<AttachDTO> attachList);

    AttachDTO selectAttachDetail(int idx);

    int deleteAttach(Long boardIdx);

    List<AttachDTO> selectAttachList(Long iboard);

    int selectAttachTotalCount(Long iboard);
}
