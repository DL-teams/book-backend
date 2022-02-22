package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsPress;
import com.dl.book.modules.bms.mapper.BmsPressMapper;
import com.dl.book.modules.bms.service.BmsPressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出版社 服务实现类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Service
public class BmsPressServiceImpl extends ServiceImpl<BmsPressMapper, BmsPress> implements BmsPressService {

    @Override
    public boolean addPress(BmsPress bmsPress) {
        return this.save(bmsPress);
    }

    @Override
    public boolean deletePress(Integer id) {
        BmsPress bmsPress = new BmsPress();
        bmsPress.setId(id);
        bmsPress.setIsDeleted(Boolean.TRUE);
        return this.updateById(bmsPress);
    }

    @Override
    public boolean updatePress(BmsPress bmsPress, Integer id) {
        QueryWrapper<BmsPress> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(BmsPress::getId, id);
        return this.update(bmsPress, wrapper);
    }

    @Override
    public BmsPress getPress(Integer id) {
        return this.getById(id);
    }

    @Override
    public Page<BmsPress> listPress(BmsPressParam bmsPressParam) {
        Page<BmsPress> page = new Page<>(bmsPressParam.getPageNum(), bmsPressParam.getPageSize());
        QueryWrapper<BmsPress> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(BmsPress::getIsDeleted, Boolean.FALSE);
        if (StringUtils.isNotBlank(bmsPressParam.getCode())) {
            wrapper.lambda()
                .like(BmsPress::getCode, bmsPressParam.getCode());
        }
        if (StringUtils.isNotBlank(bmsPressParam.getName())) {
            wrapper.lambda()
                .like(BmsPress::getCode, bmsPressParam.getName());
        }
        return page(page, wrapper);
    }
}
