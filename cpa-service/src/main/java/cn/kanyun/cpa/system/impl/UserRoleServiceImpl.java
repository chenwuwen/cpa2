package cn.kanyun.cpa.system.impl;

import cn.kanyun.cpa.model.system.RolePermission;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.system.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(IUserRoleService.SERVICE_NAME)
public class UserRoleServiceImpl extends CommonServiceImpl<Integer,UserRole> implements IUserRoleService {
}
