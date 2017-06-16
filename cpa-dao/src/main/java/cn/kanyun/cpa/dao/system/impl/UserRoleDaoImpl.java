package cn.kanyun.cpa.dao.system.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.system.IUserRoleDao;
import cn.kanyun.cpa.model.system.UserRole;
import org.springframework.stereotype.Repository;

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
}
