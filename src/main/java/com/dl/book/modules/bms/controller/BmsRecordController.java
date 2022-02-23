package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsRecordParam;
import com.dl.book.modules.bms.service.BmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 借阅还书记录表 前端控制器
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-23
 */
@RestController
@RequestMapping("/bmsRecord")
public class BmsRecordController {

    @Autowired
    private BmsRecordService bmsRecordService;

    @GetMapping("list")
    public CommonResult list(BmsRecordParam bmsRecordParam) {
        return CommonResult.success(CommonPage.restPage(bmsRecordService.recordsList(bmsRecordParam)));
    }
}

