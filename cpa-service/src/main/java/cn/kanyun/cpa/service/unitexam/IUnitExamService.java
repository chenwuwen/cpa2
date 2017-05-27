package cn.kanyun.cpa.service.unitexam;

import cn.kanyun.cpa.model.CpaResult;
import cn.kanyun.cpa.model.exam.CpaRepertory;
import cn.kanyun.cpa.service.ICommonService;


/**
 * Created by KANYUN on 2017/3/18.
 */
public interface IUnitExamService extends ICommonService<Integer,CpaRepertory> {
    public static final String SERVICE_NAME="cn.kanyun.cpa.service.unitexam.impl.UnitExamServiceImpl";
    CpaResult getUnitExam(String where, Object[] params);
}
