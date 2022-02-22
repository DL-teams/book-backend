package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.mapper.BmsCategoryMappingMapper;
import com.dl.book.modules.bms.mapper.BmsPressMappingMapper;
import com.dl.book.modules.bms.model.BmsCategoryMapping;
import com.dl.book.modules.bms.model.BmsInfo;
import com.dl.book.modules.bms.mapper.BmsInfoMapper;
import com.dl.book.modules.bms.model.BmsPressMapping;
import com.dl.book.modules.bms.service.BmsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 图书信息表 服务实现类
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Service
public class BmsInfoServiceImpl extends ServiceImpl<BmsInfoMapper, BmsInfo> implements BmsInfoService {

    private final  BmsCategoryMappingMapper bmsCategoryMappingMapper;

    private final BmsPressMappingMapper bmsPressMappingMapper;

    @Autowired
    public BmsInfoServiceImpl(BmsCategoryMappingMapper bmsCategoryMappingMapper,
                              BmsPressMappingMapper bmsPressMappingMapper) {
        this.bmsCategoryMappingMapper = bmsCategoryMappingMapper;
        this.bmsPressMappingMapper = bmsPressMappingMapper;
    }

    @Override
    public Page<BmsInfo> listBooking(BmsInfoParam bmsInfoParam) {

        Page<BmsInfo> page = new Page<>(bmsInfoParam.getPageNum(), bmsInfoParam.getPageSize());
        QueryWrapper<BmsInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda()
            .eq(BmsInfo::getIsDeteled, Boolean.FALSE);
        if (StringUtils.isNotBlank(bmsInfoParam.getName())) {
            wrapper.lambda()
                .like(BmsInfo::getName, bmsInfoParam.getName());
        }
        if (StringUtils.isNotBlank(bmsInfoParam.getAuthor())) {
            wrapper.lambda()
                .like(BmsInfo::getAuthor, bmsInfoParam.getAuthor());
        }

        List<Integer> bookList = new ArrayList<>();
        if (ObjectUtils.isEmpty(bmsInfoParam.getCategoryId())) {
            QueryWrapper<BmsCategoryMapping> bmsCategoryWrapper = new QueryWrapper<>();
            bmsCategoryWrapper.lambda().eq(BmsCategoryMapping::getCategoryId, bmsInfoParam.getCategoryId());
            List<BmsCategoryMapping> bmsCategoryMappingList = bmsCategoryMappingMapper.selectList(bmsCategoryWrapper);
            List<Integer> bookIds =
                bmsCategoryMappingList.stream().map(BmsCategoryMapping::getBookId).collect(Collectors.toList());
            bookList.addAll(bookIds);
        }
        if (ObjectUtils.isEmpty(bmsInfoParam.getPressId())) {
            QueryWrapper<BmsPressMapping> bmsPressMappingWrapper = new QueryWrapper<>();
            bmsPressMappingWrapper.lambda().eq(BmsPressMapping::getPressId, bmsInfoParam.getPressId());
            List<BmsPressMapping> bmsPressMappingList = bmsPressMappingMapper.selectList(bmsPressMappingWrapper);
            List<Integer> bookIds =
                bmsPressMappingList.stream().map(BmsPressMapping::getBookId).collect(Collectors.toList());
            bookList.addAll(bookIds);
        }
        if (!CollectionUtils.isEmpty(bookList)) {
            wrapper.lambda().in(BmsInfo::getId, bookList);
        }
        return page(page, wrapper);
    }
}
