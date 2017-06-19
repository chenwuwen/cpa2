package cn.kanyun.cpa.dao.system;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface IUserRoleDao extends ICommonDao<Integer,UserRole> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.system.impl.UserRoleDaoImpl";

    Set<CpaRole> findRoleByUserId(Integer userId);
}
