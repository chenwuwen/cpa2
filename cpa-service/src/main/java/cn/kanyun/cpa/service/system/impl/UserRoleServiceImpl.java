package cn.kanyun.cpa.service.system.impl;

import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.UserRole;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.service.system.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(IUserRoleService.SERVICE_NAME)
public class UserRoleServiceImpl extends CommonServiceImpl<Integer,UserRole> implements IUserRoleService {


    @Override
    public Set<String> findRoleUserId(Integer id) {
        return null;
    }

    @Override
    public Collection<CpaPermission> findPermissionByUserId(Integer id) {
        return null;
    }
}
