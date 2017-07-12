package cn.kanyun.cpa.dao.system.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.system.IRolePermissionDao;
import cn.kanyun.cpa.model.entity.system.CpaPermission;
import cn.kanyun.cpa.model.entity.system.RolePermission;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
@Repository(IRolePermissionDao.REPOSITORY_NAME)
public class RolePermissionDaoImpl extends CommonDaoImpl<Integer,RolePermission> implements IRolePermissionDao {

    public RolePermissionDaoImpl() {
        super(RolePermission.class);
    }

    /**
     * 保留指定clatt值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clatt
     */
    public RolePermissionDaoImpl(Class<RolePermission> clatt) {
        super(clatt);
    }




    @Override
    public Set<CpaPermission> findPermissionByRoleId(Set roleIds) {
        Session session = getSession();
        String hql = " from RolePermission o where roleId in (:roleIds)";
        Query query = session.createQuery(hql);
        query.setParameterList("roleIds",roleIds);
        return (Set<CpaPermission>) query.list();
    }
}
