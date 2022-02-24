package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.dto.BmsPressParam;
import com.dl.book.modules.bms.model.BmsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dl.book.modules.bms.model.BmsPress;
import com.dl.book.modules.bms.request.BmsBookAddRequest;
import com.dl.book.modules.bms.request.BmsBookUpdateRequest;
import com.dl.book.modules.bms.response.BmsBookDetailResponse;

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
     * 添加图书信息
     * @param bmsBookAddRequest bmsBookAddRequest
     * @return boolean boolean
     * @author jiangfendou
     * @since 2022-02-22
     */
    boolean create(BmsBookAddRequest bmsBookAddRequest);

    /**
     * 修改图书信息
     * @param bmsBookUpdateRequest bmsBookUpdateRequest
     * @param bookId bookId
     * @return boolean boolean
     * @author jiangfendou
     * @since 2022-02-22
     */
    boolean update(BmsBookUpdateRequest bmsBookUpdateRequest, Integer bookId);

    /**
     * 删除图书信息
     * @param bookId bookId
     * @return boolean boolean
     * @author jiangfendou
     * @since 2022-02-22
     */
    boolean delete(Integer bookId);

    /**
     * 查看图书信息
     * @param bookId bookId
     * @return BmsBookDetailResponse BmsBookDetailResponse
     * @author jiangfendou
     * @since 2022-02-22
     */
    BmsBookDetailResponse detail(Integer bookId);

}
