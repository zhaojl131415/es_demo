package com.zhao.demo.repository;

import com.zhao.demo.domain.HrCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ES 操作类
 *
 * Created by zhao.
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
     * 查询公司描述
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


    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"companyName\" : \"?0\"}}}}")
    Page<HrCompany> findByCompanyName(String companyName,Pageable pageable);

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

    /**
    关键字	            使用示例	                            等同于的ES查询
    And	                findByNameAndPrice	                {“bool” : {“must” : [ {“field” : {“name” : “?”}}, {“field” : {“price” : “?”}} ]}}
    Or	                findByNameOrPrice	                {“bool” : {“should” : [ {“field” : {“name” : “?”}}, {“field” : {“price” : “?”}} ]}}
    Is	                findByName	                        {“bool” : {“must” : {“field” : {“name” : “?”}}}}
    Not	                findByNameNot	                    {“bool” : {“must_not” : {“field” : {“name” : “?”}}}}
    Between	            findByPriceBetween	                {“bool” : {“must” : {“range” : {“price” : {“from” : ?,”to” : ?,”include_lower” : true,”include_upper” : true}}}}}
    LessThanEqual	    findByPriceLessThan	                {“bool” : {“must” : {“range” : {“price” : {“from” : null,”to” : ?,”include_lower” : true,”include_upper” : true}}}}}
    GreaterThanEqual	findByPriceGreaterThan	            {“bool” : {“must” : {“range” : {“price” : {“from” : ?,”to” : null,”include_lower” : true,”include_upper” : true}}}}}
    Before	            findByPriceBefore	                {“bool” : {“must” : {“range” : {“price” : {“from” : null,”to” : ?,”include_lower” : true,”include_upper” : true}}}}}
    After	            findByPriceAfter	                {“bool” : {“must” : {“range” : {“price” : {“from” : ?,”to” : null,”include_lower” : true,”include_upper” : true}}}}}
    Like	            findByNameLike	                    {“bool” : {“must” : {“field” : {“name” : {“query” : “? *”,”analyze_wildcard” : true}}}}}
    StartingWith	    findByNameStartingWith	            {“bool” : {“must” : {“field” : {“name” : {“query” : “? *”,”analyze_wildcard” : true}}}}}
    EndingWith	        findByNameEndingWith	            {“bool” : {“must” : {“field” : {“name” : {“query” : “*?”,”analyze_wildcard” : true}}}}}
    Contains/Containing	findByNameContaining	            {“bool” : {“must” : {“field” : {“name” : {“query” : “?”,”analyze_wildcard” : true}}}}}
    In	                findByNameIn(Collectionnames)	    {“bool” : {“must” : {“bool” : {“should” : [ {“field” : {“name” : “?”}}, {“field” : {“name” : “?”}} ]}}}}
    NotIn	            findByNameNotIn(Collectionnames)	{“bool” : {“must_not” : {“bool” : {“should” : {“field” : {“name” : “?”}}}}}}
    True	            findByAvailableTrue	                {“bool” : {“must” : {“field” : {“available” : true}}}}
    False	            findByAvailableFalse	            {“bool” : {“must” : {“field” : {“available” : false}}}}
    OrderBy	            findByAvailableTrueOrderByNameDesc	{“sort” : [{ “name” : {“order” : “desc”} }],”bool” : {“must” : {“field” : {“available” : true}}}}
    */
}
