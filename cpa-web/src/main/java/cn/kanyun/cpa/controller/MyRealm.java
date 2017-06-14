package cn.kanyun.cpa.controller;

/**
 * Created by Administrator on 2017/6/14.
 */
            import java.util.Date;
            import java.util.List;

            import javax.annotation.Resource;

            import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
            import net.shopxx.Setting.AccountLockType;
            import net.shopxx.Setting.CaptchaType;
            import net.shopxx.entity.Admin;
            import net.shopxx.service.AdminService;
            import net.shopxx.service.CaptchaService;
            import net.shopxx.util.SettingUtils;

            import org.apache.commons.codec.digest.DigestUtils;
            import org.apache.commons.lang.ArrayUtils;
            import org.apache.commons.lang.time.DateUtils;
            import org.apache.shiro.authc.AuthenticationInfo;
            import org.apache.shiro.authc.AuthenticationToken;
            import org.apache.shiro.authc.DisabledAccountException;
            import org.apache.shiro.authc.IncorrectCredentialsException;
            import org.apache.shiro.authc.LockedAccountException;
            import org.apache.shiro.authc.SimpleAuthenticationInfo;
            import org.apache.shiro.authc.UnknownAccountException;
            import org.apache.shiro.authc.pam.UnsupportedTokenException;
            import org.apache.shiro.authz.AuthorizationInfo;
            import org.apache.shiro.authz.SimpleAuthorizationInfo;
            import org.apache.shiro.realm.AuthorizingRealm;
            import org.apache.shiro.subject.PrincipalCollection;

    /**
     * 权限认证
     *
     * @author LinkCity Team
     * @version 3.0
     */
    public class MyRealm extends AuthorizingRealm {

        @Resource(name = "captchaServiceImpl")
        private CaptchaService captchaService;
        @Resource(name = "adminServiceImpl")
        private AdminService adminService;

        /**
         * 获取认证信息
         *
         * @param token
         *            令牌
         * @return 认证信息
         */
        @Override
    /*
     * 验证当前用户的合法性---也就是登录验证
     */
        protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
            //获取当前登录令牌（令牌包括的信息有username、password、remember、captcha验证码、登录IP）
            AuthenticationToken authenticationToken = (AuthenticationToken) token;
            String username = authenticationToken.getUsername();
            String password = new String(authenticationToken.getPassword());
            String captchaId = authenticationToken.getCaptchaId();
            String captcha = authenticationToken.getCaptcha();
            String ip = authenticationToken.getHost();
            //验证验证码是否正确
            if (!captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha)) {
                throw new UnsupportedTokenException();
            }
            if (username != null && password != null) {
                //根据当前username获取数据库的admin用户
                Admin admin = adminService.findByUsername(username);
                //判断是否有此用户
                if (admin == null) {
                    throw new UnknownAccountException();
                }
                //判断当前用户是否可用
                if (!admin.getIsEnabled()) {
                    throw new DisabledAccountException();
                }
                ManagementAssertion.Setting setting = SettingUtils.get();
                //判断用户是否被锁定
                if (admin.getIsLocked()) {
                    if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.admin)) {
                        int loginFailureLockTime = setting.getAccountLockTime();
                        if (loginFailureLockTime == 0) {
                            throw new LockedAccountException();
                        }
                        Date lockedDate = admin.getLockedDate();
                        Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                        if (new Date().after(unlockDate)) {
                            admin.setLoginFailureCount(0);
                            admin.setIsLocked(false);
                            admin.setLockedDate(null);
                            adminService.update(admin);
                        } else {
                            throw new LockedAccountException();
                        }
                    } else {
                        admin.setLoginFailureCount(0);
                        admin.setIsLocked(false);
                        admin.setLockedDate(null);
                        adminService.update(admin);
                    }
                }
                //判断密码是否正确
                if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                    int loginFailureCount = admin.getLoginFailureCount() + 1;
                    if (loginFailureCount >= setting.getAccountLockCount()) {
                        admin.setIsLocked(true);
                        admin.setLockedDate(new Date());
                    }
                    admin.setLoginFailureCount(loginFailureCount);
                    adminService.update(admin);
                    throw new IncorrectCredentialsException();
                }
                admin.setLoginIp(ip);
                admin.setLoginDate(new Date());
                admin.setLoginFailureCount(0);
                //更新用户登录信息
                adminService.update(admin);
                //返回一个封装了用户信息的AuthenticationInfo实例
                return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
            }
            throw new UnknownAccountException();
        }

        /**
         * 获取授权信息
         *
         * @param principals
         *            principals
         * @return 授权信息
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            //获取当前登录用户的信息
            Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
            if (principal != null) {
                //查找当前用户的角色
                List<String> authorities = adminService.findAuthorities(principal.getId());
                if (authorities != null) {
                    //获取当前用户的登录令牌
                    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                    //为当前用户令牌添加权限集合
                    authorizationInfo.addStringPermissions(authorities);
                    return authorizationInfo;
                }
            }
            return null;
        }

    }

