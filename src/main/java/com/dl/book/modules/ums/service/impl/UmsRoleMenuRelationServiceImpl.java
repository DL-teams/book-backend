package com.dl.book.modules.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.book.modules.ums.mapper.UmsRoleMenuRelationMapper;
import com.dl.book.modules.ums.model.UmsRoleMenuRelation;
import com.dl.book.modules.ums.service.UmsRoleMenuRelationService;
import org.springframework.stereotype.Service;

/**
 * 角色菜单关系管理Service实现类
 */
@Service
public class UmsRoleMenuRelationServiceImpl extends ServiceImpl<UmsRoleMenuRelationMapper, UmsRoleMenuRelation> implements
    UmsRoleMenuRelationService {
}
