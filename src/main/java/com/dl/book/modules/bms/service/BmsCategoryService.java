package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsCategoryParam;
import com.dl.book.modules.bms.model.BmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图书分类信息表 服务类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-21
 */
public interface BmsCategoryService extends IService<BmsCategory> {

    boolean addCategory(BmsCategory bmsCategory);

    boolean deleteCategory(Integer id);

    boolean updateCategory(BmsCategory bmsCategory, Integer id);

    BmsCategory getCategory(Integer id);

    Page<BmsCategory> listCategory(BmsCategoryParam bmsCategoryParam, Integer parentId);
}
