package cn.kanyun.cpa.dao.system.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.system.IUserRoleDao;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
@Repository(IUserRoleDao.SERVICE_NAME)
public class UserRoleDaoImpl extends CommonDaoImpl<Integer,UserRole> implements  IUserRoleDao {
    /**
     * 保留指定clatt值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clatt
     */
    public UserRoleDaoImpl(Class<UserRole> clatt) {
        super(clatt);
    }

    @Override
    public Set<CpaRole> findRoleByUserId(Integer userId) {
        Session session = getSession();
        String hql = "select o from UserRole o where userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId",userId);
        return (Set<CpaRole>) query.list();
    }
}
