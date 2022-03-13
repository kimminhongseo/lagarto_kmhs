package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.PaginationInfo;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.customer.files.CustomerAttachDTO;
import com.portfolio.lagarto.customer.files.CustomerAttachMapper;
import com.portfolio.lagarto.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    @Autowired private CustomerMapper mapper;
    @Autowired private CustomerAttachMapper attachMapper;
    @Autowired private Utils utils;
    @Autowired private MyFileUtils myFileUtils;

    public boolean insCustomer(CustomerEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        return mapper.insCustomer(entity);
    }

    public boolean insCustomer(CustomerEntity entity, MultipartFile[] files) {
        int queryResult = 1;
        if (!this.insCustomer(entity)) {
            return false;
        } else {
            List<CustomerAttachDTO> fileList = this.myFileUtils.uploadFiles(files, entity.getIboard());
            if (!CollectionUtils.isEmpty(fileList)) {
                queryResult = this.attachMapper.insertAttach(fileList);
                if (queryResult < 1) {
                    queryResult = 0;
                }
            }

            return queryResult > 0;
        }
    }

    public List<CustomerVo> selCustomerList(TestDto dto) {
        List<CustomerVo> list = Collections.emptyList();
        int totalCount = mapper.totalCount(dto);
        PaginationInfo paginationInfo = new PaginationInfo(dto);
        paginationInfo.setTotalRecordCount(totalCount);

        dto.setPaginationInfo(paginationInfo);

        if(totalCount > 0) {
            list = mapper.selCustomerList(dto);
        }
        return list;
    }

    public List<CustomerVo> selNoticeList(TestDto dto) {
        List<CustomerVo> list = null;
        int totalCount = mapper.totalCount(dto);
        PaginationInfo paginationInfo = new PaginationInfo(dto);
        paginationInfo.setTotalRecordCount(totalCount);

        dto.setPaginationInfo(paginationInfo);

        if(totalCount > 0) {
            list = mapper.selCustomerList(dto);
        }
        return list;
    }

    public List<CustomerVo> selMyReviewList(TestDto dto) {
        List<CustomerVo> list;
        int totalCount = mapper.totalCount(dto);
        PaginationInfo paginationInfo = new PaginationInfo(dto);
        paginationInfo.setTotalRecordCount(totalCount);

        dto.setIuser(utils.getLoginUserPk());
        dto.setPaginationInfo(paginationInfo);

            list = mapper.selMyReviewList(dto);
        return list;
    }



    public CustomerVo selCustomerDetail(CustomerDto dto) {
        attachMapper.deleteDbFiles(dto.getIboard());
        return mapper.selCustomerDetail(dto);
    }

    public int updCustomer(CustomerDto dto) {
        dto.setIuser(utils.getLoginUserPk());
        int result = mapper.updCustomer(dto);
//         파일이 추가, 삭제, 변경된 경우
        if ("Y".equals(dto.getChangeYn())) {
            attachMapper.deleteAttach(dto.getIboard());

            // fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 'N'으로 업데이트
            if (CollectionUtils.isEmpty(dto.getFileIdxs()) == false) {
                attachMapper.undeleteAttach(dto.getFileIdxs());
            }
        }
        return result;
    }
    public int updCustomer(CustomerDto dto, MultipartFile[] files) {
        int result = 1;
        mapper.updCustomer(dto);

        List<CustomerAttachDTO> fileList = myFileUtils.uploadFiles(files, dto.getIboard());
        if(CollectionUtils.isEmpty(fileList) == false) {
            result = attachMapper.insertAttach(fileList);
        }
        return result;
    }

    public int delCustomer(CustomerEntity entity){
        entity.setIsdel(1);
        attachMapper.deleteAttach(entity.getIboard());
        return mapper.delCustomer(entity);
    }

    public List<CustomerAttachDTO> getAttachFileList(int iboard) {

        int fileTotalCount = attachMapper.selectAttachTotalCount(iboard);
        if (fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return attachMapper.selectAttachList(iboard);
    }

}
