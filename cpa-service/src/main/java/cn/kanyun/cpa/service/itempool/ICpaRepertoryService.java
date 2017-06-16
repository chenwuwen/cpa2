package cn.kanyun.cpa.service.itempool;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.itempool.CpaRepertory;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ICpaRepertoryService extends ICommonDao<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.itempool.impl.CpaRepertoryDaoImpl";
}
