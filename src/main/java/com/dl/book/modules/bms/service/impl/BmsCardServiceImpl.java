package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsCardParam;
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
    public Boolean addCard(BmsCard bmsCard) {
        return this.save(bmsCard);
    }

    @Override
    public Boolean updateCard(BmsCard bmsCard, Integer id) {
        QueryWrapper<BmsCard> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsCard::getId,id);
        return this.update(bmsCard,wrapper);
    }

    @Override
    public Boolean deleteCard(Integer id) {
        BmsCard bmsCard = new BmsCard();
        bmsCard.setId(id);
        bmsCard.setIsDeteled(Boolean.TRUE);
        return this.updateById(bmsCard);
    }

    @Override
    public BmsCard getCard(Integer id) {
        return this.getById(id);
    }

    @Override
    public Page<BmsCard> cardList(BmsCardParam bmsCardParam) {
        Page<BmsCard> page = new Page<>(bmsCardParam.getPageNum(),bmsCardParam.getPageSize());
        QueryWrapper<BmsCard> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsCard::getIsDeteled,Boolean.FALSE);
        return page(page,wrapper);
    }
}
