package com.zhao.demo.service.impl;

import com.google.common.collect.Lists;
import com.zhao.demo.common.CommonUtils;
import com.zhao.demo.domain.HrCompany;
import com.zhao.demo.repository.HrCompanyRepository;
import com.zhao.demo.service.HrCompanyService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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

    public Page<HrCompany> getHrCompanys() {
        //创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置模糊搜索,真实姓名中包含金的用户
        builder.must(QueryBuilders.fuzzyQuery("companyName", "金"));
        //设置用户名为king
        builder.must(new QueryStringQueryBuilder("12").field("companyId"));

        //排序
        FieldSortBuilder sort = SortBuilders.fieldSort("score").order(SortOrder.DESC);

        //设置分页
        //====注意!es的分页和Hibernate一样api是从第0页开始的=========
        PageRequest page = new PageRequest(0, 2);

        //构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //执行,返回包装结果的分页
        Page<HrCompany> resutlList = hrCompanyRepository.search(query);

        return resutlList;
    }

}
