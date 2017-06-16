package cn.kanyun.cpa.service.itempool;

import cn.kanyun.cpa.model.CpaResult;
import cn.kanyun.cpa.model.itempool.CpaRepertory;
import cn.kanyun.cpa.service.ICommonService;


/**
 * Created by KANYUN on 2017/3/18.
 */
public interface IUnitExamService extends ICommonService<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.itempool.impl.UnitExamServiceImpl";
    CpaResult getUnitExam(String where, Object[] params);
}
