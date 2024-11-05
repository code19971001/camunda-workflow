package com.itcode.camunda.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("handleOrder")
public class HandleOrderService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("处理订餐订单");
        String payEndpoint = (String) execution.getVariable("endpoint");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        if ("wechatPay".equalsIgnoreCase(payEndpoint)){
            System.out.println("微信支付");
            runtimeService.startProcessInstanceByMessage("Message_order_wechat");
        }else if ("alipay".equalsIgnoreCase(payEndpoint)){
            System.out.println("支付宝支付");
            runtimeService.startProcessInstanceByMessage("Message_order_alipay");
        }else {
            System.out.println("not support pay endpoint");
        }


    }
}
