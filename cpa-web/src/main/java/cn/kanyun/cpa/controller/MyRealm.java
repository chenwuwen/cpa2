package cn.kanyun.cpa.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Resource;

import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.system.IRolePermissionService;
import cn.kanyun.cpa.service.system.IUserRoleService;
import cn.kanyun.cpa.service.user.IUserService;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import net.sf.ehcache.CacheManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * 权限认证
 * <p>
 * Created by Administrator on 2017/6/14.
 */
public class MyRealm extends AuthorizingRealm {
    //由于shiro是在其他service之前加载，所以必须在运行时加载
    @Resource
    private IUserService userService;
    @Resource
    private IUserRoleService userRoleService;
    @Resource
    private IRolePermissionService rolePermissionService;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyRealm.class);
    public MyRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     * 认证回调函数,登录时调用.经测试登陆时会调用两次
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
       logger.info("=================Shiro开始登录认证===================");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String  password = String.valueOf(token.getPassword());
        CpaUser user = userService.findByUserName(token.getUsername());
        // 账号不存在
        if (user == null) {
            return null;
        }else if(user.getStatus() == 1){
            // 账号状态异常
            return null;
        }else if(!password.equals(user.getPassword())){
            //密码不匹配
            return null;
        }else {
            //若存在，将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }


//        // 读取用户的url和角色
//        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        //用户的角色集合
//        info.setRoles(userRoleService.findRoleByUserId(user.getId()));
//        //用户的角色对应的所有权限，如果只使用角色定义访问权限
//        Collection<CpaPermission> permissions=userRoleService.findPermissionByUserId(user.getId());
//        for (CpaPermission permission : permissions) {
//            info.addStringPermissions(permission.getPermission());
//        }
//        return info;
//        // 认证缓存信息
//        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(),
//                ShiroByteSource.of(user.getSalt()), getName());
    }

    /**
     * Shiro权限认证 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * 当用户进行访问链接时的授权方法 将权限查出来
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.info("===========检测权限=========");
        CpaUser user = (CpaUser) principals.getPrimaryPrincipal();
        if (user!=null) {
            Set<CpaRole> roleSet = userRoleService.findRoleByUserId(user.getId());
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(shiroUser.getRoles());
            info.addStringPermissions(shiroUser.getUrlSet());

            return info;
        }
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        removeUserCache(shiroUser);
    }

    /**
     * 清除用户缓存
     *
     * @param CpaUser
     */
    public void removeUserCache(CpaUser user) {
        removeUserCache(user.getUsername());
    }

    /**
     * 清除用户缓存
     *
     * @param userName
     */
    public void removeUserCache(String userName) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(userName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }
}

