package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsPressParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图书信息表 前端控制器
 * </p>
 *
 * @author jiangfendu
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/bms/bmsInfo")
public class BmsInfoController {



    @GetMapping("list")
    public CommonResult listCategory(BmsPressParam bmsPressParam) {
        return CommonResult.success(null);
    }

}

