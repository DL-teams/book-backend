package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.model.BmsCard;
import com.dl.book.modules.bms.mapper.BmsCardMapper;
import com.dl.book.modules.bms.service.BmsCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书证信息表 服务实现类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Service
public class BmsCardServiceImpl extends ServiceImpl<BmsCardMapper, BmsCard> implements BmsCardService {

    @Override
    public Page<BmsCard> cardList(Integer pageNum, Integer pageSize) {
        Page<BmsCard> page = new Page<>(pageNum,pageSize);
        QueryWrapper<BmsCard> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsCard::getIsDeteled,Boolean.FALSE);
        return page(page,wrapper);
    }
}
