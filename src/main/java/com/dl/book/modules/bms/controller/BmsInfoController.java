package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.request.BmsBookAddRequest;
import com.dl.book.modules.bms.request.BmsBookUpdateRequest;
import com.dl.book.modules.bms.service.BmsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/list")
    public CommonResult listBooks(BmsInfoParam bmsInfoParam) {
        return CommonResult.success(bmsInfoService.listBooking(bmsInfoParam));
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody @Validated BmsBookAddRequest bmsBookAddRequest) {
        return CommonResult.success(bmsInfoService.create(bmsBookAddRequest));
    }

    @PutMapping("/update/{bookId}")
    public CommonResult create(@RequestBody @Validated BmsBookUpdateRequest bmsBookUpdateRequest,
                               @PathVariable Integer bookId) {
        return CommonResult.success(bmsInfoService.update(bmsBookUpdateRequest, bookId));
    }

    @DeleteMapping("/delete/{bookId}")
    public CommonResult delete(@PathVariable Integer bookId) {
        return CommonResult.success(bmsInfoService.delete(bookId));
    }

    @GetMapping("/detail/{bookId}")
    public CommonResult detail(@PathVariable Integer bookId) {
        return CommonResult.success(bmsInfoService.detail(bookId));
    }

}

