package cn.kanyun.cpa.dao.system.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.system.IRolePermissionDao;
import cn.kanyun.cpa.model.system.RolePermission;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/16.
 */
@Repository(IRolePermissionDao.SERVICE_NAME)
public class RolePermissionDaoImpl extends CommonDaoImpl<Integer,RolePermission> implements IRolePermissionDao {
    /**
     * 保留指定clatt值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clatt
     */
    public RolePermissionDaoImpl(Class<RolePermission> clatt) {
        super(clatt);
    }
}
