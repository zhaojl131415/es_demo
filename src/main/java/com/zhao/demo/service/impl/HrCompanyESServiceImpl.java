package com.zhao.demo.service.impl;

import com.google.common.collect.Lists;
import com.zhao.demo.common.CommonUtils;
import com.zhao.demo.domain.HrCompany;
import com.zhao.demo.repository.HrCompanyRepository;
import com.zhao.demo.service.HrCompanyService;
import com.zhao.demo.service.HrCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class HrCompanyESServiceImpl implements HrCompanyService {

    private static final Logger logger = LoggerFactory.getLogger(HrCompanyESServiceImpl.class);

    // ES 操作类
    @Autowired
    HrCompanyRepository hrCompanyRepository;

    public Long saveHrCompany(HrCompany hrCompany) {
        HrCompany hrCompanyResult = hrCompanyRepository.save(hrCompany);
        return hrCompanyResult.getCompanyId();
    }

    public List<HrCompany> findAll() {
        return Lists.newArrayList(hrCompanyRepository.findAll());
    }

    public List<HrCompany> findByDescriptionAndScore(String description, Integer score) {
        return hrCompanyRepository.findByDescriptionAndScore(description, score);
    }

    public List<HrCompany> findByDescriptionOrScore(String description, Integer score) {
        return hrCompanyRepository.findByDescriptionOrScore(description, score);
    }

    public List<HrCompany> findByDescription(String description) {
        return hrCompanyRepository.findByDescription(description, CommonUtils.pageable).getContent();
    }

    public List<HrCompany> findByDescriptionNot(String description) {
        return hrCompanyRepository.findByDescriptionNot(description, CommonUtils.pageable).getContent();
    }

    public List<HrCompany> findByDescriptionLike(String description) {
        return hrCompanyRepository.findByDescriptionLike(description, CommonUtils.pageable).getContent();
    }

}
