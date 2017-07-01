package cn.kanyun.cpa.service.system.impl;

import cn.kanyun.cpa.dao.system.IRolePermissionDao;
import cn.kanyun.cpa.dao.system.IUserRoleDao;
import cn.kanyun.cpa.dao.system.impl.UserRoleDaoImpl;
import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.service.system.IRolePermissionService;
import cn.kanyun.cpa.service.system.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(IUserRoleService.SERVICE_NAME)
public class UserRoleServiceImpl extends CommonServiceImpl<Integer, UserRole> implements IUserRoleService {
    @Resource
    private IUserRoleDao userRoleDao;
    @Resource
    private IRolePermissionDao rolePermissionDao;

    @Override
    public Set<String> findRoleByUserId(Integer userId) {
        Set<CpaRole> setRoles = userRoleDao.findRoleByUserId(userId);
        Set<String> set = new HashSet<>();
        for (CpaRole role : setRoles) {
//            set.add(String.valueOf(role.getId())); //shiro中要的是roleName不是roleId
            set.add(role.getRoleName());
        }
        return set;
    }

    @Override
    public  Set<String> findPermissionByUerId(Integer userId) {
        Set<CpaRole> setRoles = userRoleDao.findRoleByUserId(userId);
        Set<String> roleIds = new HashSet<>();
        for (CpaRole role : setRoles) {
            roleIds.add(String.valueOf(role.getId()));
        }
        Set<CpaPermission> setPermissions = rolePermissionDao.findPermissionByRoleId(roleIds);
        Set<String> set = new HashSet<>();
        for (CpaPermission cpaPermission:setPermissions){
            set.add(cpaPermission.getPermissionCode());
        }
        return set;
    }
}
