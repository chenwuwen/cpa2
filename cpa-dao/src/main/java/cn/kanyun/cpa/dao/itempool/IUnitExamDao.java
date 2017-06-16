package cn.kanyun.cpa.dao.itempool;


import cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.model.itempool.CpaRepertory;

/**
 * Created by KANYUN on 2017/4/13.
 */
public interface IUnitExamDao  extends ICommonDao<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.itempool.impl.UnitExamDaoImpl";
}
