package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsCardParam;
import com.dl.book.modules.bms.model.BmsCard;
import com.dl.book.modules.bms.service.BmsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("create")
    public CommonResult addCard(@RequestBody BmsCard bmsCard) {
        return CommonResult.success(bmsCardService.addCard(bmsCard));
    }

    @PutMapping("update/{id}")
    public CommonResult updateCard(@RequestBody BmsCard bmsCard, @PathVariable Integer id) {
        return CommonResult.success(bmsCardService.updateCard(bmsCard,id));
    }

    @DeleteMapping("delete/{id}")
    public CommonResult deleteCard(@PathVariable Integer id) {
        return CommonResult.success(bmsCardService.deleteCard(id));
    }

    @GetMapping("get/{id}")
    public CommonResult getCard(@PathVariable Integer id) {
        return CommonResult.success(bmsCardService.getCard(id));
    }

    @GetMapping("list")
    public CommonResult list (BmsCardParam bmsCardParam) {
        return CommonResult.success(CommonPage.restPage(bmsCardService.cardList(bmsCardParam)));
    }
}

