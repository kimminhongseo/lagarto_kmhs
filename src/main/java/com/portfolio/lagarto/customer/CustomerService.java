package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.MyFileUtils;
import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.customer.files.AttachDTO;
import com.portfolio.lagarto.customer.files.AttachMapper;
import com.portfolio.lagarto.model.CustomerDto;
import com.portfolio.lagarto.model.CustomerEntity;
import com.portfolio.lagarto.model.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    @Autowired private CustomerMapper mapper;
    @Autowired private AttachMapper attachMapper;
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
            List<AttachDTO> fileList = this.myFileUtils.uploadFiles(files, (long) entity.getIboard());
            if (!CollectionUtils.isEmpty(fileList)) {
                queryResult = this.attachMapper.insertAttach(fileList);
                if (queryResult < 1) {
                    queryResult = 0;
                }
            }

            return queryResult > 0;
        }
    }

    public List<CustomerVo> selCustomerList(CustomerDto dto) {
        return mapper.selCustomerList(dto);
    }

    public List<AttachDTO> getAttachFileList(Long iboard) {

        int fileTotalCount = attachMapper.selectAttachTotalCount(iboard);
        if (fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return attachMapper.selectAttachList(iboard);}

    public CustomerVo selCustomerDetail(CustomerDto dto) {
        return mapper.selCustomerDetail(dto);
    }

    public int delCustomer(CustomerEntity entity){
        entity.setIsdel(1);
        return mapper.delCustomer(entity);
    }
}
