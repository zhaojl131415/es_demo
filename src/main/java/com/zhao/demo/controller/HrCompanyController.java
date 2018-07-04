package com.zhao.demo.controller;

import com.zhao.demo.domain.HrCompany;
import com.zhao.demo.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 03/05/2017.
 */
@RestController
public class HrCompanyController {

    @Autowired
    private HrCompanyService hrCompanyService;

    /**
     * 插入 ES 新城市
     *
     * @param hrCompany
     * @return
     */
    @PostMapping("/api/hr_company")
    public Long createHrCompany(@RequestBody HrCompany hrCompany) {
        return hrCompanyService.saveHrCompany(hrCompany);
    }

    /**
     * 全库查询
     *
     * @return
     */
    @GetMapping("/api/hr_company/find")
    public List<HrCompany> findByDescriptionAndScore() {
        return hrCompanyService.findAll();
    }
    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @GetMapping("/api/hr_company/and/find")
    public List<HrCompany> findByDescriptionAndScore(@RequestParam(value = "description") String description,
                                                @RequestParam(value = "score") Integer score) {
        return hrCompanyService.findByDescriptionAndScore(description, score);
    }

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @GetMapping(value = "/api/hr_company/or/find")
    public List<HrCompany> findByDescriptionOrScore(@RequestParam(value = "description") String description,
                                               @RequestParam(value = "score") Integer score) {
        return hrCompanyService.findByDescriptionOrScore(description, score);
    }

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/api/hr_company/description/find")
    public List<HrCompany> findByDescription(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescription(description);
    }

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/api/hr_company/description/not/find")
    public List<HrCompany> findByDescriptionNot(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescriptionNot(description);
    }

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/api/hr_company/like/find")
    public List<HrCompany> findByDescriptionLike(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescriptionLike(description);
    }
}
