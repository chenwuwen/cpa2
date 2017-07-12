package cn.kanyun.cpa.service.itempool.impl;


import cn.kanyun.cpa.dao.itempool.ICpaRepertoryDao;
import cn.kanyun.cpa.model.entity.CpaResult;
import cn.kanyun.cpa.model.entity.itempool.CpaOption;
import cn.kanyun.cpa.model.entity.itempool.CpaRepertory;
import cn.kanyun.cpa.service.CommonServiceImpl;
import cn.kanyun.cpa.service.itempool.ICpaRepertoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/6/16.
 */
@Service(ICpaRepertoryService.SERVICE_NAME)
public class CpaRepertoryServiceImpl extends CommonServiceImpl<Integer, CpaRepertory> implements ICpaRepertoryService {
    @Resource(name = ICpaRepertoryDao.REPOSITORY_NAME)
    private IUnitExamDao unitExamDao;
    //获取单元测试数据
    public CpaResult getUnitExam(String where, Object[] params) {
        CpaResult result = unitExamDao.getScrollData(-1, -1, where, params);
        if (result.getTotalCount() > 0) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<CpaRepertory> listcr = (List<CpaRepertory>) result.getData();
            for (CpaRepertory cr : listcr) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("stem", cr.getTestStem());
                map.put("cr_id", cr.getId());
                Set<CpaOption> setco = cr.getCpaOptions();
//                将Set集合转换为List集合
                List<CpaOption> listco = new ArrayList<CpaOption>();
                for (CpaOption co : setco) {
                    listco.add(co);
                }
//                将转换后的List集合，自定义排序，根据属性内的ABCD进行升序排列
                Collections.sort(listco, new Comparator<CpaOption>() {
                    @Override
                    public int compare(CpaOption o1, CpaOption o2) {
                        int sortResult = o1.getSelectData().compareTo(o2.getSelectData());
                        return sortResult;
                    }
                });
//                从排序后的List集合里取出选项内容，可以保证，他们的顺序不变
                List<Map<String, Object>> listoptions = new ArrayList<>();
//                List<CpaOption> listoptions = new ArrayList<CpaOption>(); //原写法,只取出选项的内容,ABCD在前台用Angular的过滤器来得到,由于选项内容已经排序故不必担心顺序不对的问题
                for (CpaOption co : listco) {
//                    listoptions.add(co.getOptionData()); //原写法,只取出数据即可.但后来发现在提交的时候有些问题,故将ABCD也加上
                    Map<String, Object> mapco = new HashMap<String, Object>();
                    mapco.put("optionKey",co.getSelectData());
                    mapco.put("optionData",co.getOptionData());
                    listoptions.add(mapco);
                }
                map.put("optionList", listoptions);
                list.add(map);
            }
            result.setStatus(1);
            result.setData(list);
        } else {
            result.setStatus(0);
            result.setMsg("未获取到记录！");
        }
        return result;
    }

}
