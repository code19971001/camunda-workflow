package com.itcode.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("noticeCustomerListener")
public class NoticeCustomerListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("通知客户...");
        String clientHome = (String) delegateExecution.getVariable("clientHome");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("你好，师傅正在赶往:" + clientHome + ", 为您服务~~~");
    }
}
