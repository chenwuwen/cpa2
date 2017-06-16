package cn.kanyun.cpa.service.itempool;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.itempool.CpaSolution;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ICpaSolutionService extends ICommonDao<Integer,CpaSolution> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.itempool.impl.CpaSolutionDaoImpl";
}
