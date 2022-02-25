package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsRecordParam;
import com.dl.book.modules.bms.model.BmsRecord;
import com.dl.book.modules.bms.mapper.BmsRecordMapper;
import com.dl.book.modules.bms.service.BmsRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.book.modules.bms.vo.BmsRecordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 借阅还书记录表 服务实现类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-23
 */
@Service
public class BmsRecordServiceImpl extends ServiceImpl<BmsRecordMapper, BmsRecord> implements BmsRecordService {

//    private BmsRecordMapper bmsRecordMapper;

    @Override
    public Boolean addRecord(BmsRecord bmsRecord) {
        return this.save(bmsRecord);
    }

    @Override
    public Boolean updateRecord(BmsRecord bmsRecord, Integer id) {
        QueryWrapper<BmsRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsRecord::getId,id);
        return this.update(bmsRecord,wrapper);
    }

    @Override
    public Boolean deleteRecord(Integer id) {
        BmsRecord bmsRecord = new BmsRecord();
        bmsRecord.setId(id);
        bmsRecord.setIsDeteled(Boolean.TRUE);
        return this.updateById(bmsRecord);
    }

    @Override
    public BmsRecord getRecord(Integer id) {
        return this.getById(id);
    }

    @Override
    public Page<BmsRecordInfo> recordsList(BmsRecordParam bmsRecordParam) {
        Page<BmsRecordInfo> page = new Page<>(bmsRecordParam.getPageNum(),bmsRecordParam.getPageSize());
        return page.setRecords(this.baseMapper.recordsList());
    }
}
