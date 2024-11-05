package com.itcode.camunda.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("doRepair")
public class DoRepairService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("师傅上门修理");
        String activityName = delegateExecution.getCurrentActivityName();
        System.out.println("activityName: " + activityName);
        delegateExecution.setVariable("repairManName","xiaowang");
    }
}
