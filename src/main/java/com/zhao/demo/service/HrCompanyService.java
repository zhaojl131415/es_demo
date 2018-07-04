package com.zhao.demo.service;

import com.zhao.demo.domain.HrCompany;

import java.util.List;

/**
 * 公司 ES 业务接口类
 *
 */
public interface HrCompanyService {

    /**
     * 新增 ES 城市信息
     *
     * @param hrCompany
     * @return
     */
    Long saveHrCompany(HrCompany hrCompany);

    /**
     * 全库查询
     *
     * @return
     */
    List<HrCompany> findAll();

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<HrCompany> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<HrCompany> findByDescriptionOrScore(String description, Integer score);

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    List<HrCompany> findByDescription(String description);

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    List<HrCompany> findByDescriptionNot(String description);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    List<HrCompany> findByDescriptionLike(String description);
}