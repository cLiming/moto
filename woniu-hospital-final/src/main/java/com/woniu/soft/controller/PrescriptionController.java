package com.woniu.soft.controller;


import com.woniu.soft.entity.Prescription;
import com.woniu.soft.service.PrescriptionService;
import com.woniu.soft.utils.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    @Resource
    private PrescriptionService prescriptionService;
    //修改处方的状态
    @RequestMapping("updatePrescription")
    public JSONResult updatePrescription(Prescription prescription) throws Exception{
        prescriptionService.updatePrescription(prescription);
        return new JSONResult("200","success",null,null);
    }
}

