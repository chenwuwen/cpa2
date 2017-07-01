package cn.kanyun.cpa.service.system;

import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.RolePermission;
import cn.kanyun.cpa.service.ICommonService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface IRolePermissionService extends ICommonService<Integer,RolePermission> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.system.impl.RolePermissionServiceImpl";

    Set<CpaPermission> findPermissionByRoleId(Set roleIds);

}
