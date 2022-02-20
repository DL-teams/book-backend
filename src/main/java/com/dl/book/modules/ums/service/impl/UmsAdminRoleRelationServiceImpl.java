package com.dl.book.modules.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.book.modules.ums.mapper.UmsAdminRoleRelationMapper;
import com.dl.book.modules.ums.model.UmsAdminRoleRelation;
import com.dl.book.modules.ums.service.UmsAdminRoleRelationService;
import org.springframework.stereotype.Service;

/**
 * 管理员角色关系管理Service实现类
 */
@Service
public class UmsAdminRoleRelationServiceImpl extends ServiceImpl<UmsAdminRoleRelationMapper, UmsAdminRoleRelation> implements
    UmsAdminRoleRelationService {
}
