package com.zhao.demo.repository;

import com.zhao.demo.domain.HrCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ES 操作类
 * <p>
 * Created by bysocket on 17/05/2017.
 */

public interface HrCompanyRepository extends ElasticsearchRepository<HrCompany, Long> {
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
     * 等同于下面代码
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"description\" : \"?0\"}}}}")
     * Page<HrCompany> findByDescription(String description, Pageable pageable);
     *
     * @param description
     * @param page
     * @return
     */
    Page<HrCompany> findByDescription(String description, Pageable page);

    /**
     * NOT 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<HrCompany> findByDescriptionNot(String description, Pageable page);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @param page
     * @return
     */
    Page<HrCompany> findByDescriptionLike(String description, Pageable page);

}
