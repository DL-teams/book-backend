package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsPress;
import com.dl.book.modules.bms.service.BmsPressService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 出版社 前端控制器
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/bookPress")
public class BmsPressController {

    @Autowired
    private BmsPressService bmsPressService;

    @PostMapping("create")
    public CommonResult addCategory(@RequestBody BmsPress bmsPress) {
        return CommonResult.success(bmsPressService.addPress(bmsPress));
    }

    @DeleteMapping("delete/{id}")
    public CommonResult deleteCategory(@PathVariable Integer id) {
        return CommonResult.success(bmsPressService.deletePress(id));
    }

    @PutMapping("update/{id}")
    public CommonResult updateCategory(@RequestBody BmsPress bmsPress, @PathVariable Integer id) {
        return CommonResult.success(bmsPressService.updatePress(bmsPress, id));
    }

    @GetMapping("{id}")
    public CommonResult getCategory(@PathVariable Integer id) {
        return CommonResult.success(bmsPressService.getPress(id));
    }

    @GetMapping("list")
    public CommonResult listCategory(
        BmsPressParam bmsPressParam) {
        return CommonResult.success(CommonPage.restPage(bmsPressService.listPress(bmsPressParam)));
    }
}

