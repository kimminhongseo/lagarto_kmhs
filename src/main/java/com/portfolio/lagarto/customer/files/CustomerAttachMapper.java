package com.portfolio.lagarto.customer.files;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerAttachMapper {
    int insertAttach(List<CustomerAttachDTO> attachList);

    CustomerAttachDTO selectAttachDetail(int iboard);

    int deleteAttach(int iboard);

    void deleteDbFiles(int iboard);

    int undeleteAttach(List<Integer> idxs);

    List<CustomerAttachDTO> selectAttachList(int iboard);

    int selectAttachTotalCount(int iboard);
}
