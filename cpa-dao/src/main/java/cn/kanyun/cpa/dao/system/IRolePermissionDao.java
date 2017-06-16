package cn.kanyun.cpa.dao.system;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.system.RolePermission;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface IRolePermissionDao extends ICommonDao<Integer,RolePermission> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.system.impl.RolePermissionDaoImpl";
}
