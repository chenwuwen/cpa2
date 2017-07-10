package cn.kanyun.cpa.controller.user;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.kanyun.cpa.model.CpaResult;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.user.IUserService;
import cn.kanyun.cpa.util.MD5util;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name= IUserService.SERVICE_NAME)
	private IUserService userService;
	/* 登陆检查 */
	@RequestMapping("/login.do")
	@ResponseBody
	public CpaResult toLogin(String v_code, CpaUser user,HttpSession session){
		CpaResult result = new CpaResult();
		String s_code = (String) session.getAttribute("validateCode");
		// 先比较验证码(equalsIgnoreCase忽略大小写，equals不忽略)
		if (!s_code.equalsIgnoreCase(v_code)) {
			result.setStatus(2);
			result.setMsg("验证码错误！");
		}else{
        /*就是代表当前的用户。*/
		Subject currentUser = SecurityUtils.getSubject();
		//获取基于用户名和密码的令牌
		//这里的token大家叫他令牌，也就相当于一张表格，你要去验证，你就得填个表，里面写好用户名密码，交给公安局的同志给你验证。
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), user.getPassword());
//      但是，“已记住”和“已认证”是有区别的：
//      已记住的用户仅仅是非匿名用户，你可以通过subject.getPrincipals()获取用户信息。但是它并非是完全认证通过的用户，当你访问需要认证用户的功能时，你仍然需要重新提交认证信息。
//      这一区别可以参考亚马逊网站，网站会默认记住登录的用户，再次访问网站时，对于非敏感的页面功能，页面上会显示记住的用户信息，但是当你访问网站账户信息时仍然需要再次进行登录认证。
		token.setRememberMe(true);

		try {
			//这句是提交申请，验证能不能通过，也就是交给公安局同志了。这里会回调reaml里的一个方法
			// 回调doGetAuthenticationInfo，进行认证
			currentUser.login(token);
		} catch (AuthenticationException e) {
			result.setMsg("登陆失败");
			result.setStatus(2);

		}
		//验证是否通过
		if(currentUser.isAuthenticated()){
			user.setUsername(user.getUsername());
			session.setAttribute("userinfo", user);
			result.setMsg("登陆成功");
			result.setStatus(1);
		}else{
			result.setMsg("登陆失败");
			result.setStatus(2);
		}
		}
		return result;
		
	}
	/* 注册Ajax检查用户名是否可用 */
	@RequestMapping("/checkname.do")
	@ResponseBody
	public CpaResult checkName(String username) {
		Object[] params = { username };
		String where = "o.username=? ";
		CpaResult result = userService.getScrollData(-1,-1,where,params);
		if(result.getTotalCount()>0){
			result.setStatus(2);
			result.setMsg("当前用户名已存在!");
		}
		return result;
	}
	/*用户注册*/
	@RequestMapping("/register.do")
	@ResponseBody
	public CpaResult saveUser(String v_code,String username, String password,HttpSession session) throws NoSuchAlgorithmException{
		CpaResult result = new CpaResult();
		// 获取session中保存的验证码
		String s_code = (String) session.getAttribute("code");
		// 先比较验证码(equalsIgnoreCase忽略大小写，equals不忽略)
		if (!s_code.equalsIgnoreCase(v_code)) {
			result.setStatus(2);
			result.setMsg("验证码错误！");
		}else{
		String md5_pwd = MD5util.md5(password);
		CpaUser user = new CpaUser();
		user.setPassword(md5_pwd);
		user.setUsername(username);
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		Integer k =userService.save(user);
		if (k!=null){
		result.setStatus(1);
		}else{
		result.setStatus(2);
		result.setMsg("注册失败,请重试！");
		}
		}
		return result;
}
}
