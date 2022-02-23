package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.service.BmsInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BmsInfoService bmsInfoService;

    @GetMapping("list")
    public CommonResult listBooks(BmsInfoParam bmsInfoParam) {
        return CommonResult.success(bmsInfoService.listBooking(bmsInfoParam));
    }

}

