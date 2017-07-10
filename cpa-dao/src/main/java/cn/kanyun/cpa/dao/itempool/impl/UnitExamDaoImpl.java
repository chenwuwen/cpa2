package cn.kanyun.cpa.dao.itempool.impl;
import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.itempool.IUnitExamDao;
import cn.kanyun.cpa.model.itempool.CpaRepertory;
import org.springframework.stereotype.Repository;


/**
 * Created by KANYUN on 2017/4/13.
 */
@Repository(IUnitExamDao.REPOSITORY_NAME)
public class UnitExamDaoImpl extends CommonDaoImpl<Integer, CpaRepertory> implements IUnitExamDao {
    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public UnitExamDaoImpl() {
        super(CpaRepertory.class);
    }


}
