package cn.kanyun.cpa.service.user;


import cn.kanyun.cpa.model.CpaResult;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.ICommonService;

public interface IUserService extends ICommonService<Integer,CpaUser> {
	public static final String SERVICE_NAME="cn.kanyun.cpa.service.user.impl.UserServiceImpl";
	CpaResult checkLogin(String username, String password);
}
