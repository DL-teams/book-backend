package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsCategoryParam;
import com.dl.book.modules.bms.model.BmsCategory;
import com.dl.book.modules.bms.mapper.BmsCategoryMapper;
import com.dl.book.modules.bms.service.BmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书分类信息表 服务实现类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-21
 */
@Service
public class BmsCategoryServiceImpl extends ServiceImpl<BmsCategoryMapper, BmsCategory> implements BmsCategoryService {

    @Override
    public boolean addCategory(BmsCategory bmsCategory) {
        return this.save(bmsCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        BmsCategory bmsCategory = new BmsCategory();
        bmsCategory.setId(id);
        bmsCategory.setIsDeleted(Boolean.TRUE);
        return this.updateById(bmsCategory);
    }

    @Override
    public boolean updateCategory(BmsCategory bmsCategory, Integer id) {
        QueryWrapper<BmsCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsCategory::getId, id);
        return this.update(bmsCategory, wrapper);
    }

    @Override
    public BmsCategory getCategory(Integer id) {
        return this.getById(id);
    }

    @Override
    public Page<BmsCategory> listCategory(BmsCategoryParam bmsCategoryParam, Integer parentId) {
        Page<BmsCategory> page = new Page<>(bmsCategoryParam.getPageNum(), bmsCategoryParam.getPageSize());
        QueryWrapper<BmsCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsCategory::getParentId, parentId)
            .eq(BmsCategory::getIsDeleted, Boolean.FALSE);
        if (StringUtils.isNotBlank(bmsCategoryParam.getCode())) {
            wrapper.lambda().like(BmsCategory::getCode, bmsCategoryParam.getCode());
        }
        if (StringUtils.isNotBlank(bmsCategoryParam.getName())) {
            wrapper.lambda().like(BmsCategory::getName, bmsCategoryParam.getName());
        }
        return page(page, wrapper);
    }

}
