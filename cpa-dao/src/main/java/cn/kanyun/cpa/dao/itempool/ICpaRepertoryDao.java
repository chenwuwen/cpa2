package cn.kanyun.cpa.dao.itempool;

import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.itempool.CpaRepertory;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ICpaRepertoryDao extends ICommonDao<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.itempool.impl.CpaRepertoryDaoImpl";
}
