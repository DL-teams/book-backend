package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.service.BmsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图书证信息表 前端控制器
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/bookCard")
public class BmsCardController {

    @Autowired
    private BmsCardService bmsCardService;

    @GetMapping("list")
    public CommonResult list (
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize
    ) {
        return CommonResult.success(CommonPage.restPage(bmsCardService.cardList(pageNum, pageSize)));
    }
}

