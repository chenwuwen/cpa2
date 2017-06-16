package cn.kanyun.cpa.system.impl;

import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.RolePermission;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.system.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(IRolePermissionService.SERVICE_NAME)
public class RolePermissionServiceImpl extends CommonServiceImpl<Integer,RolePermission> implements IRolePermissionService {
}
