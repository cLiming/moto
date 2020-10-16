package com.woniu.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.soft.entity.MedAdvice;
import com.woniu.soft.entity.Settlement;
import com.woniu.soft.entity.User;
import com.woniu.soft.mapper.MedAdviceMapper;
import com.woniu.soft.mapper.SettlementMapper;
import com.woniu.soft.mapper.UserMapper;
import com.woniu.soft.service.SettlementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-10-15
 */
@Service
public class SettlementServiceImpl extends ServiceImpl<SettlementMapper, Settlement> implements SettlementService {
    @Resource
    private SettlementMapper settlementMapper;
    @Resource
    private MedAdviceMapper medAdviceMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public void updateManyInfo(Integer id) {
        //新增一个该用户的结算单
        Settlement settlement = new Settlement();
        settlement.setUid(id);
        settlement.setStatus(1);
        settlementMapper.insert(settlement);

        //查出该用户所有未结算的医嘱，并结算
        QueryWrapper<MedAdvice> adviceWrapper = new QueryWrapper<>();
        adviceWrapper.eq("u_id",id);
        adviceWrapper.ne("status",2);
        List<MedAdvice> adviceList = medAdviceMapper.selectList(adviceWrapper);
        for(MedAdvice m:adviceList){
            m.setStatus(2);
            medAdviceMapper.updateById(m);
        }
        //修改用户状态为已结算
        User user = userMapper.selectById(id);
        user.setStatus(7);
        user.setBalance(0.0);
        userMapper.updateById(user);
    }
}
