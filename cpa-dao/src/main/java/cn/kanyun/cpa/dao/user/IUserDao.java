package cn.kanyun.cpa.dao.user;


public interface IUserDao extends ICommonDao<Integer,CpaUser> {
	public static final String SERVICE_NAME="cn.kanyun.cpa.dao.user.impl.UserDaoImpl";
}
