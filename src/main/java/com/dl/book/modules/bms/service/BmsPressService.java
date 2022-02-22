package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsPress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 出版社 服务类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
public interface BmsPressService extends IService<BmsPress> {

    boolean addPress(BmsPress bmsPress);

    boolean deletePress(Integer id);

    boolean updatePress(BmsPress bmsPress, Integer id);

    BmsPress getPress(Integer id);

    Page<BmsPress> listPress(BmsPressParam bmsPressParam);
}
