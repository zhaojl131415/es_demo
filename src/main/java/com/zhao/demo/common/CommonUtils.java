package com.zhao.demo.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CommonUtils {
    // 分页参数
    public static final Integer pageNumber = 0;
    public static final Integer pageSize = 10;

    public static final Pageable pageable = new PageRequest(pageNumber, pageSize);
}
