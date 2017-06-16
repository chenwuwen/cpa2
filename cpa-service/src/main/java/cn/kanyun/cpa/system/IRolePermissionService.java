package cn.kanyun.cpa.system;

import cn.kanyun.cpa.model.exam.CpaRepertory;
import cn.kanyun.cpa.model.system.RolePermission;
import cn.kanyun.cpa.service.ICommonService;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface IRolePermissionService extends ICommonService<Integer,RolePermission> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.system.impl.RolePermissionServiceImpl";
}
