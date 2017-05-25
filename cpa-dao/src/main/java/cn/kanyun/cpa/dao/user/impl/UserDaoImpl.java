package cn.kanyun.cpa.dao.user.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.user.IUserDao;
import cn.kanyun.cpa.model.user.CpaUser;
import org.springframework.stereotype.Repository;

@Repository(IUserDao.SERVICE_NAME)
public class UserDaoImpl extends CommonDaoImpl<Integer,CpaUser> implements IUserDao {
	//通过调用父类的构造函数指定clazz值，即实体类的类类型  
    public UserDaoImpl() {  
        super(CpaUser.class);  
    }     
	
}

