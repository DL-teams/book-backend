package com.dl.book.modules.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.model.BmsCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图书证信息表 服务类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
public interface BmsCardService extends IService<BmsCard> {

    Page<BmsCard> cardList(Integer pageNum, Integer pageSize);
}
