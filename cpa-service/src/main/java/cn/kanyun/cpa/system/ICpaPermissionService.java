package cn.kanyun.cpa.system;

import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.service.ICommonService;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ICpaPermissionService extends ICommonService<Integer,CpaPermission> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.system.impl.CpaPermissionServiceImpl";
}
