package cn.kanyun.cpa.dao.unitexam;

import cn.kanyun.cpa.dao.cn.kanyun.cpa.dao.ICommonDao;
import cn.kanyun.cpa.pojo.CpaRepertory;

/**
 * Created by KANYUN on 2017/4/13.
 */
public interface IUnitExamDao  extends ICommonDao<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.dao.unitexam.impl.UnitExamDaoImpl";
}
