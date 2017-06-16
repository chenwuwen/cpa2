package cn.kanyun.cpa.dao.itempool.impl;

import cn.kanyun.cpa.dao.CommonDaoImpl;
import cn.kanyun.cpa.dao.itempool.ICpaRepertoryDao;
import cn.kanyun.cpa.model.itempool.CpaRepertory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/16.
 */
@Repository(ICpaRepertoryDao.SERVICE_NAME)
public class CpaRepertoryDaoImpl extends CommonDaoImpl<Integer, CpaRepertory> implements ICpaRepertoryDao {
    /**
     * 保留指定clatt值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clatt
     */
    public CpaRepertoryDaoImpl(Class<CpaRepertory> clatt) {
        super(clatt);
    }
}
