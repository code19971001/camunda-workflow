package com.itcode.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("addressListener")
public class AddressListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入检查用户地址任务");
        Object clientHome = delegateExecution.getVariable("clientHome");
        if (clientHome == null){
            //using client init address replace、
            System.out.println("使用客户注册地址");
            delegateExecution.setVariable("clientHome","河南省郑州市xxx路");
        }
    }
}
