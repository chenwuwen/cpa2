package cn.kanyun.cpa.controller.unitexam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by KANYUN on 2017/3/18.
 */
@Controller
@RequestMapping("/unitExam")
public class UnitExamController {
    @Resource(name = IUnitExamService.SERVICE_NAME)
    private IUnitExamService unitExamService;
    @RequestMapping("/getUnitExam.do")
    @ResponseBody
    public CpaResult getUnitExam(String typeCode) {
        Object[] params = {typeCode};
        String where = "o.testType=? ";
        CpaResult result = unitExamService.getUnitExam(where, params);
        return result;
    }
}