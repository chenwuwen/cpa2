package cn.kanyun.cpa.service.user;


import cn.kanyun.cpa.dao.HibernateSessionFactory;
import cn.kanyun.cpa.model.CpaResult;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.ICommonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IUserService extends ICommonService<Integer,CpaUser> {
	public static final String SERVICE_NAME="cn.kanyun.cpa.service.user.impl.UserServiceImpl";
	CpaResult checkLogin(String username, String password);
	CpaUser findByUserName(String userName);
}
