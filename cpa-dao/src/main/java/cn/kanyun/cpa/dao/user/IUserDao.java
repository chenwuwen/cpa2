package cn.kanyun.cpa.dao.user;


import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.user.CpaUser;

public interface IUserDao extends ICommonDao<Integer,CpaUser> {
	public static final String SERVICE_NAME="cn.kanyun.cpa.dao.user.impl.UserDaoImpl";
	CpaUser findByUserName(String userName);
}
