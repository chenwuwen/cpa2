package cn.kanyun.cpa.service.system.impl;

import cn.kanyun.cpa.dao.system.IUserRoleDao;
import cn.kanyun.cpa.dao.system.impl.UserRoleDaoImpl;
import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.model.user.CpaUser;
import cn.kanyun.cpa.service.CommonServiceImpl;
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

    @Override
    public Set<String> findRoleByUserId(Integer userId) {
        Set<CpaRole> setRoles = userRoleDao.findRoleByUserId(userId);
        Set<String> set = new HashSet<>();
        for (CpaRole role : setRoles) {
            set.add(String.valueOf(role.getId()));
//            set.add(role.getRoleName()); //不知道shiro中要的是roleName还是roleId
        }
        return set;
    }

    @Override
    public Collection<CpaPermission> findPermissionByUserId(Integer id) {
        return null;
    }
}
