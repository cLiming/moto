package com.woniu.soft.controller;


import com.woniu.soft.service.SettlementService;
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
@RequestMapping("/settlement")
public class SettlementController {
    @Resource
    private SettlementService settlementService;

    //结算，点击结算之后，传过来一个用户账号，更改用户的状态和余额，更改结算表用户的状态，
    //更改该用户下所有医嘱的状态
    @RequestMapping("pay")
    public JSONResult payMoney(Integer id){
        System.err.println(id);
       settlementService.updateManyInfo(id);
        return  new JSONResult("200","success",null,null);
    }
}

