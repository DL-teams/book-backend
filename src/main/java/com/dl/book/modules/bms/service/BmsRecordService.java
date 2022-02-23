package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsRecordParam;
import com.dl.book.modules.bms.model.BmsRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dl.book.modules.bms.vo.BmsRecordInfo;
import lombok.Data;

/**
 * <p>
 * 借阅还书记录表 服务类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-23
 */
public interface BmsRecordService extends IService<BmsRecord> {

    Boolean addRecord(BmsRecord bmsRecord);

    Boolean updateRecord(BmsRecord bmsRecord, Integer id);

    Boolean deleteRecord(Integer id);

    BmsRecord detailRecord(Integer id);

    Page<BmsRecordInfo> recordsList(BmsRecordParam bmsRecordParam);
}
