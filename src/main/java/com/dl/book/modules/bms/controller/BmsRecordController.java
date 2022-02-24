package com.dl.book.modules.bms.controller;


import com.dl.book.common.api.CommonPage;
import com.dl.book.common.api.CommonResult;
import com.dl.book.modules.bms.dto.BmsRecordParam;
import com.dl.book.modules.bms.model.BmsRecord;
import com.dl.book.modules.bms.service.BmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("create")
    public CommonResult addRecord(@RequestBody BmsRecord bmsRecord) {
        return CommonResult.success(bmsRecordService.addRecord(bmsRecord));
    }

    @PutMapping("update/{id}")
    public CommonResult updateRecord(@RequestBody BmsRecord bmsRecord, @PathVariable Integer id) {
        return CommonResult.success(bmsRecordService.updateRecord(bmsRecord,id));
    }

    @DeleteMapping("delete/{id}")
    public CommonResult deleteRecord(@PathVariable Integer id) {
        return CommonResult.success(bmsRecordService.deleteRecord(id));
    }

    @GetMapping("get/{id}")
    public CommonResult getRecord(@PathVariable Integer id) {
        return CommonResult.success(bmsRecordService.getRecord(id));
    }

    @GetMapping("list")
    public CommonResult list(BmsRecordParam bmsRecordParam) {
        return CommonResult.success(CommonPage.restPage(bmsRecordService.recordsList(bmsRecordParam)));
    }
}

