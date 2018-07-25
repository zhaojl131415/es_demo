package com.zhao.demo.controller;

import com.zhao.demo.domain.HrCompany;
import com.zhao.demo.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司 Controller 实现 Restful HTTP 服务
 *
 */
@RestController
@RequestMapping("/api/hr_company")
public class HrCompanyController {

    @Autowired
    private HrCompanyService hrCompanyService;

    /**
     * 插入 ES 新公司
     *
     * @param
     * @return
     */
    @PostMapping(value = "/add")
    public Long createHrCompany(@RequestParam(value = "companyId") Long companyId,
                                @RequestParam(value = "companyName") String companyName,
                                @RequestParam(value = "score") Integer score,
                                @RequestParam(value = "description") String description) {
        HrCompany hrCompany = new HrCompany();
        hrCompany.setId(companyId);
        hrCompany.setCompanyId(companyId);
        hrCompany.setCompanyName(companyName);
        hrCompany.setScore(score);
        hrCompany.setDescription(description);

        return hrCompanyService.saveHrCompany(hrCompany);
    }

    /**
     * 全库查询
     *
     * @return
     */
    @GetMapping("/find")
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
    @GetMapping("/and/find")
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
    @GetMapping(value = "/or/find")
    public List<HrCompany> findByDescriptionOrScore(@RequestParam(value = "description") String description,
                                               @RequestParam(value = "score") Integer score) {
        return hrCompanyService.findByDescriptionOrScore(description, score);
    }

    /**
     * 查询公司描述
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/description/find")
    public List<HrCompany> findByDescription(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescription(description);
    }

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/description/not/find")
    public List<HrCompany> findByDescriptionNot(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescriptionNot(description);
    }

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    @GetMapping(value = "/like/find")
    public List<HrCompany> findByDescriptionLike(@RequestParam(value = "description") String description) {
        return hrCompanyService.findByDescriptionLike(description);
    }
}
