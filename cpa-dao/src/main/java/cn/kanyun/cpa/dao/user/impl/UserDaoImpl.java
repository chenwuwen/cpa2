package cn.kanyun.cpa.dao.user.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.HibernateSessionFactory;
import cn.kanyun.cpa.dao.user.IUserDao;
import cn.kanyun.cpa.model.entity.user.CpaUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(IUserDao.REPOSITORY_NAME)
public class UserDaoImpl extends CommonDaoImpl<Integer,CpaUser> implements IUserDao {
	//通过调用父类的构造函数指定clazz值，即实体类的类类型  
    public UserDaoImpl() {  
        super(CpaUser.class);  
    }
    Session session = HibernateSessionFactory.getSession();
    @Override
    public CpaUser findByUserName(String userName) {
        String hql = "select o from CpaUser o where o.username = :userName";
        Query query=session.createQuery(hql);
        query.setParameter("userName",userName);
        return (CpaUser) query.uniqueResult();
    }
}

