package com.zhao.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Data
@Document(indexName = "zhanjob-hr-company1", type = "company", createIndex = false)
public class HrCompany implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long id;

    /**
     * 公司ID
     */
    @Field
    private Long companyId;

    /**
     * 公司名称
     */
    @Field
    private String CompanyName;
    /**
     * 公司名称
     */
    @Field
    private String description;
    @Field
    private Integer score;
}
