package cn.kanyun.cpa.service.itempool;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.itempool.CpaOption;
import cn.kanyun.cpa.service.ICommonService;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ICpaOptionService extends ICommonService<Integer,CpaOption> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.itempool.impl.CpaOptionDaoImpl";
}
