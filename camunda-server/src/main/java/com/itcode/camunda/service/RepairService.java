package com.itcode.camunda.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RepairService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("开始预约维修服务~~");
        String name = delegateExecution.getCurrentActivityName();
        //预约流程
        String processDefinitionId = delegateExecution.getProcessDefinitionId();
        System.out.println("当前活动的名称:" + name + ", 流程定义的id:" + processDefinitionId);
        delegateExecution.setVariable("isFree", false);
    }
}
