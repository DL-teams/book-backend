package com.dl.book.modules.bms.mapper;

import com.dl.book.modules.bms.model.BmsRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.book.modules.bms.vo.BmsRecordInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 借阅还书记录表 Mapper 接口
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-23
 */
public interface BmsRecordMapper extends BaseMapper<BmsRecord> {

    @Select("SELECT br.*,bi.name as bookname, ua.username, bc.number FROM bms_record br " +
            "JOIN bms_info bi ON br.book_id = bi.id JOIN bms_card bc ON br.card_id = bc.id " +
            "JOIN ums_admin ua ON bc.user_id = ua.id")
    List<BmsRecordInfo> recordsList();
}
