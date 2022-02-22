package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dl.book.modules.bms.model.BmsPress;

/**
 * <p>
 * 图书信息表 服务类
 * </p>
 *
 * @author jiangfendou
 * @since 2022-02-22
 */
public interface BmsInfoService extends IService<BmsInfo> {

    Page<BmsInfo> listBooking(BmsInfoParam bmsInfoParam);

}
