package cn.kanyun.cpa.dao.unitexam.impl;
import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.unitexam.IUnitExamDao;
import cn.kanyun.cpa.model.exam.CpaRepertory;
import org.springframework.stereotype.Repository;


/**
 * Created by KANYUN on 2017/4/13.
 */
@Repository(IUnitExamDao.SERVICE_NAME)
public class UnitExamDaoImpl extends CommonDaoImpl<Integer, CpaRepertory> implements IUnitExamDao {
    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public UnitExamDaoImpl() {
        super(CpaRepertory.class);
    }


}
