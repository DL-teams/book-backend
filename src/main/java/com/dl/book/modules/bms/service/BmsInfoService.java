package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dl.book.modules.bms.model.BmsPress;
import com.dl.book.modules.bms.request.BmsBookAddRequest;

/**
 * <p>
 * 图书信息表 服务类
 * </p>
 *
 * @author jiangfendou
 * @since 2022-02-22
 */
public interface BmsInfoService extends IService<BmsInfo> {

    /**
     * 查询图书列表
     * @param bmsInfoParam bmsInfoParam
     * @return BmsInfoList BmsInfoList
     * @author jiangfendou
     * @since 2022-02-22
     */
    Page<BmsInfo> listBooking(BmsInfoParam bmsInfoParam);

    /**
     * 查询图书列表
     * @param bmsBookAddRequest bmsBookAddRequest
     * @return boolean boolean
     * @author jiangfendou
     * @since 2022-02-22
     */
    boolean create(BmsBookAddRequest bmsBookAddRequest);

}
