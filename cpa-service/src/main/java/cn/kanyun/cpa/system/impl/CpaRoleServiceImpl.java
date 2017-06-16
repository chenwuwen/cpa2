package cn.kanyun.cpa.system.impl;

import cn.kanyun.cpa.model.system.CpaPermission;
import cn.kanyun.cpa.model.system.CpaRole;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.system.ICpaRoleService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(ICpaRoleService.SERVICE_NAME)
public class CpaRoleServiceImpl extends CommonServiceImpl<Integer,CpaRole> implements ICpaRoleService {
}
