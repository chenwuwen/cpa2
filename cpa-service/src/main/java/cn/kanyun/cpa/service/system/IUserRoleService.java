package cn.kanyun.cpa.service.system;

import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.service.ICommonService;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface IUserRoleService extends ICommonService<Integer,UserRole> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.system.impl.UserRoleServiceImpl";
}
