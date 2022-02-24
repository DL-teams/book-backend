package com.dl.book.modules.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.book.common.api.ResultCode;
import com.dl.book.common.exception.ApiException;
import com.dl.book.modules.bms.dto.BmsInfoParam;
import com.dl.book.modules.bms.mapper.BmsCategoryMapper;
import com.dl.book.modules.bms.mapper.BmsCategoryMappingMapper;
import com.dl.book.modules.bms.mapper.BmsInfoMapper;
import com.dl.book.modules.bms.mapper.BmsPressMapper;
import com.dl.book.modules.bms.mapper.BmsPressMappingMapper;
import com.dl.book.modules.bms.model.BmsCategory;
import com.dl.book.modules.bms.model.BmsCategoryMapping;
import com.dl.book.modules.bms.model.BmsInfo;
import com.dl.book.modules.bms.model.BmsPress;
import com.dl.book.modules.bms.model.BmsPressMapping;
import com.dl.book.modules.bms.request.BmsBookAddRequest;
import com.dl.book.modules.bms.request.BmsBookUpdateRequest;
import com.dl.book.modules.bms.response.BmsBookDetailResponse;
import com.dl.book.modules.bms.service.BmsInfoService;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final BmsInfoMapper bmsInfoMapper;

    private final BmsCategoryMapper bmsCategoryMapper;

    private final BmsPressMapper bmsPressMapper;

    @Autowired
    public BmsInfoServiceImpl(BmsCategoryMappingMapper bmsCategoryMappingMapper,
                              BmsPressMappingMapper bmsPressMappingMapper,
                              BmsInfoMapper bmsInfoMapper,
                              BmsCategoryMapper bmsCategoryMapper,
                              BmsPressMapper bmsPressMapper) {
        this.bmsCategoryMappingMapper = bmsCategoryMappingMapper;
        this.bmsPressMappingMapper = bmsPressMappingMapper;
        this.bmsInfoMapper = bmsInfoMapper;
        this.bmsCategoryMapper = bmsCategoryMapper;
        this.bmsPressMapper = bmsPressMapper;
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

        HashSet<Integer> bookIdSet = new HashSet<>();
        if (!ObjectUtils.isEmpty(bmsInfoParam.getCategoryId())) {
            QueryWrapper<BmsCategoryMapping> bmsCategoryWrapper = new QueryWrapper<>();
            bmsCategoryWrapper.lambda().eq(BmsCategoryMapping::getCategoryId, bmsInfoParam.getCategoryId());
            List<BmsCategoryMapping> bmsCategoryMappingList = bmsCategoryMappingMapper.selectList(bmsCategoryWrapper);
            List<Integer> bookIds =
                bmsCategoryMappingList.stream().map(BmsCategoryMapping::getBookId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(bookIds)) {
                return null;
            }
            bookIdSet.addAll(bookIds);
        }
        if (!ObjectUtils.isEmpty(bmsInfoParam.getPressId())) {
            QueryWrapper<BmsPressMapping> bmsPressMappingWrapper = new QueryWrapper<>();
            bmsPressMappingWrapper.lambda().eq(BmsPressMapping::getPressId, bmsInfoParam.getPressId());
            List<BmsPressMapping> bmsPressMappingList = bmsPressMappingMapper.selectList(bmsPressMappingWrapper);
            List<Integer> bookIds =
                bmsPressMappingList.stream().map(BmsPressMapping::getBookId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(bookIds)) {
                return null;
            }
            bookIdSet.addAll(bookIds);
        }
        if (!CollectionUtils.isEmpty(bookIdSet)) {
            wrapper.lambda().in(BmsInfo::getId, bookIdSet);
        }
        return page(page, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean create(BmsBookAddRequest bmsBookAddRequest) {
        // 查询图书编号是否存在
        QueryWrapper<BmsInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsInfo::getCode, bmsBookAddRequest.getCode()).eq(BmsInfo::getIsDeteled, Boolean.TRUE);
        BmsInfo bmsInfo = bmsInfoMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(bmsInfo)) {
            throw new ApiException(ResultCode.BOOK_CODE_EXIST);
        }
        BmsInfo bmsBookInfo = new BmsInfo();
        BeanUtils.copyProperties(bmsBookAddRequest, bmsBookInfo);
        int insertCount = bmsInfoMapper.insert(bmsBookInfo);
        int insertBmsCategoryMapping = 0;
        int insertBmsPressMapping = 0;
        if (insertCount > 0) {
            // 添加book和category的关系表
            BmsCategoryMapping bmsCategoryMapping = new BmsCategoryMapping();
            bmsCategoryMapping.setCategoryId(bmsBookAddRequest.getCategoryId());
            bmsCategoryMapping.setBookId(bmsBookInfo.getId());
            insertBmsCategoryMapping = bmsCategoryMappingMapper.insert(bmsCategoryMapping);

            // 添加press和book的关系表
            BmsPressMapping bmsPressMapping = new BmsPressMapping();
            bmsPressMapping.setBookId(bmsBookInfo.getId());
            bmsPressMapping.setPressId(bmsBookAddRequest.getPressId());
            insertBmsPressMapping = bmsPressMappingMapper.insert(bmsPressMapping);
        }
        return insertBmsPressMapping > 0 && insertBmsCategoryMapping > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(BmsBookUpdateRequest bmsBookUpdateRequest, Integer bookId) {
        QueryWrapper<BmsInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsInfo::getId, bookId).eq(BmsInfo::getIsDeteled, Boolean.FALSE);
        if (ObjectUtils.isEmpty(bmsInfoMapper.selectOne(wrapper))) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        // 查询图书编号是否存在
        wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsInfo::getCode, bmsBookUpdateRequest.getCode()).
            eq(BmsInfo::getIsDeteled, Boolean.TRUE).
            ne(BmsInfo::getId, bookId);
        BmsInfo bmsInfo = bmsInfoMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(bmsInfo)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        QueryWrapper<BmsPressMapping> wrapperBmsPress = new QueryWrapper<>();
        wrapperBmsPress.lambda().eq(BmsPressMapping::getBookId, bookId);
        BmsPressMapping bmsPressMapping = bmsPressMappingMapper.selectOne(wrapperBmsPress);

        QueryWrapper<BmsCategoryMapping> wrapperBmsCategory = new QueryWrapper<>();
        wrapperBmsCategory.lambda().eq(BmsCategoryMapping::getBookId, bookId);
        BmsCategoryMapping bmsCategoryMapping = bmsCategoryMappingMapper.selectOne(wrapperBmsCategory);

        BmsInfo bmsBookInfo = new BmsInfo();
        BeanUtils.copyProperties(bmsBookUpdateRequest, bmsBookInfo);
        bmsBookInfo.setId(bookId);
        int bookCount = bmsInfoMapper.updateById(bmsBookInfo);

        if (bookCount > 0 &&
            !Objects.equals(bmsPressMapping.getPressId(), bmsBookUpdateRequest.getPressId()) &&
            !Objects.equals(bmsCategoryMapping.getCategoryId(), bmsBookUpdateRequest.getCategoryId())) {

            bmsCategoryMapping.setCategoryId(bmsBookUpdateRequest.getCategoryId());
            bmsCategoryMappingMapper.updateById(bmsCategoryMapping);
            bmsPressMapping.setPressId(bmsBookUpdateRequest.getPressId());
            bmsPressMappingMapper.updateById(bmsPressMapping);
        }
        return bookCount > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer bookId) {
        QueryWrapper<BmsInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsInfo::getId, bookId).eq(BmsInfo::getIsDeteled, Boolean.FALSE);
        BmsInfo bmsInfo = bmsInfoMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(bmsInfo)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        bmsInfo.setIsDeteled(false);
        int deleteCount = bmsInfoMapper.updateById(bmsInfo);
        if (deleteCount > 0) {
            QueryWrapper<BmsCategoryMapping> wrapperBmsCategory = new QueryWrapper<>();
            wrapperBmsCategory.lambda().eq(BmsCategoryMapping::getBookId, bookId);
            bmsCategoryMappingMapper.delete(wrapperBmsCategory);

            QueryWrapper<BmsPressMapping> wrapperBmsPress = new QueryWrapper<>();
            wrapperBmsPress.lambda().eq(BmsPressMapping::getBookId, bmsInfo.getId());
            bmsPressMappingMapper.delete(wrapperBmsPress);
        }
        return deleteCount > 0;
    }

    @Override
    public BmsBookDetailResponse detail(Integer bookId) {
        QueryWrapper<BmsInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsInfo::getId, bookId).eq(BmsInfo::getIsDeteled, Boolean.FALSE);
        BmsInfo bmsInfo = bmsInfoMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(bmsInfo)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        BmsBookDetailResponse bmsBookDetailResponse = new BmsBookDetailResponse();
        BeanUtils.copyProperties(bmsInfo, bmsBookDetailResponse);
        QueryWrapper<BmsCategoryMapping> wrapperBmsCategory = new QueryWrapper<>();
        wrapperBmsCategory.lambda().eq(BmsCategoryMapping::getBookId, bookId);
        BmsCategoryMapping bmsCategoryMapping = bmsCategoryMappingMapper.selectOne(wrapperBmsCategory);
        if (ObjectUtils.isEmpty(bmsCategoryMapping)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        BmsCategory bmsCategory = bmsCategoryMapper.selectById(bmsCategoryMapping.getCategoryId());
        if (ObjectUtils.isEmpty(bmsCategory)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        bmsBookDetailResponse.setCategoryName(bmsCategory.getName());

        QueryWrapper<BmsPressMapping> wrapperBmsPress = new QueryWrapper<>();
        wrapperBmsPress.lambda().eq(BmsPressMapping::getBookId, bookId);
        BmsPressMapping bmsPressMapping = bmsPressMappingMapper.selectOne(wrapperBmsPress);
        if (ObjectUtils.isEmpty(bmsCategoryMapping)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        BmsPress bmsPress = bmsPressMapper.selectById(bmsPressMapping.getPressId());
        if (ObjectUtils.isEmpty(bmsPress)) {
            throw new ApiException(ResultCode.TARGET_NOT_FOUND);
        }
        bmsBookDetailResponse.setPressName(bmsPress.getName());
        return bmsBookDetailResponse;
    }
}
