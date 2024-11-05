package com.itcode.camunda.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("queryAnnualLeave")
public class QueryAnnualLeave implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入年假查询系统");
        Object leftAnnualDays = delegateExecution.getVariable("leftAnnualDays");
        System.out.println("剩余年假为:" + leftAnnualDays);
    }
}
