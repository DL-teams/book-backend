package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsCategoryParam;
import com.dl.book.modules.bms.model.BmsCategory;
import com.dl.book.modules.bms.service.BmsCategoryService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图书分类信息表 前端控制器
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/bookCategory")
public class BmsCategoryController {

    @Autowired
    private BmsCategoryService bmsCategoryService;

    @PostMapping("create")
    public CommonResult addCategory(@RequestBody BmsCategory bmsCategory) {
        return CommonResult.success(bmsCategoryService.addCategory(bmsCategory));
    }

    @DeleteMapping("delete/{id}")
    public CommonResult deleteCategory(@PathVariable Integer id) {
        return CommonResult.success(bmsCategoryService.deleteCategory(id));
    }

    @PutMapping("update/{id}")
    public CommonResult updateCategory(@RequestBody BmsCategory bmsCategory, @PathVariable Integer id) {
        return CommonResult.success(bmsCategoryService.updateCategory(bmsCategory, id));
    }

    @GetMapping("{id}")
    public CommonResult getCategory(@PathVariable Integer id) {
        return CommonResult.success(bmsCategoryService.getCategory(id));
    }

    @GetMapping("list/{parentId}")
    public CommonResult listCategory(
        BmsCategoryParam bmsCategoryParam,
        @PathVariable Integer parentId) {
        return CommonResult.success(CommonPage.restPage(bmsCategoryService.listCategory(bmsCategoryParam, parentId)));
    }
}

