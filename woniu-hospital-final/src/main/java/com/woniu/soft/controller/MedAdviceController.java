package com.woniu.soft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.soft.entity.Adviceinfo;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.PresDrug;
import com.woniu.soft.entity.Prescription;
import com.woniu.soft.service.AdviceinfoService;
import com.woniu.soft.service.MedAdviceService;
import com.woniu.soft.service.PresDrugService;
import com.woniu.soft.service.PrescriptionService;
import com.woniu.soft.utils.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Controller
@RequestMapping("/medAdvice")
public class MedAdviceController {
    @Resource
    private MedAdviceService medAdviceService;
    @Resource
    private AdviceinfoService adviceinfoService;
    @Resource
    private PrescriptionService prescriptionService;
    @Resource
    private PresDrugService presDrugService;
    private List<Adviceinfo> adviceinfosList;
    //查询所有的医嘱
    @RequestMapping("getAdvice")
    public JSONResult getAdvice() throws Exception{
        Page<MedAdvice> page = new Page<MedAdvice>(1, 5);
        //查到所有的医嘱
        List<MedAdvice> list = medAdviceService.getAdvice(page);
        //通过医嘱查到医嘱详情
        if (list != null) {
            for (MedAdvice medAdvice : list) {
                //查到每个医嘱的医嘱详情
                adviceinfosList = adviceinfoService.getAdviceinfo(medAdvice);
                //查询每个医嘱对应的处方
                Prescription presiption = prescriptionService.getPresiption(medAdvice);
                //将医嘱详情和处方放入医嘱表之中
                medAdvice.setAdviceinfo(adviceinfosList);
                medAdvice.setPrescription(presiption);
                //得到处方详情
                if (presiption != null) {
                    List<PresDrug> drug = presDrugService.getpresDrug(presiption);
                    presiption.setPresDrugs(drug);
                }
            }
        }
        System.err.println(page.getRecords());
        return new JSONResult("200", "success", null, page);
        //return null;
    }
    //护士修改医嘱状态
    @RequestMapping("updateAdvice")
    public JSONResult updateAdvice(MedAdvice medAdvice) throws Exception{
        medAdviceService.updateAdvice(medAdvice);
        System.out.println(medAdvice.getId());
        return new JSONResult("200", "success", null, null);
        //return null;
    }

}

