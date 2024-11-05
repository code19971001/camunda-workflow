package com.itcode.task.shopping;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class SubTask {

    private static final String CAMUNDA_BASE_URL = "http://localhost:8080/engine-rest";

    private ExternalTaskClient client = null;

    private ExternalTaskClient getClient() {
        if (client == null) {
            client = ExternalTaskClient.create()
                    .baseUrl(CAMUNDA_BASE_URL)
                    .asyncResponseTimeout(10000)
                    .build();
        }
        return client;
    }


    @PostConstruct
    public void handleShoppingCart() {
        getClient().subscribe("shoppingCart")
                .processDefinitionKey("Process_Shopping")
                .lockDuration(2000)
                .handler(((externalTask, externalTaskService) -> {
                    Map<String, Object> goods = Variables.createVariables().putValue("size", "xl").putValue("count", 2);
                    externalTaskService.complete(externalTask, goods);
                })).open();
    }


    @PostConstruct
    public void handleDeliverGoods() {
        getClient().subscribe("deliverGoods")
                .processDefinitionKey("Process_Shopping")
                .lockDuration(2000)
                .handler(((externalTask, externalTaskService) -> {
                    Object dest = externalTask.getVariable("dest");
                    log.info("收到发货任务，目的地：{}", dest);
                    externalTaskService.complete(externalTask);
                })).open();
    }


    @PostConstruct
    public void handlePay() {
        getClient().subscribe("pay")
                .processDefinitionKey("Process_Shopping")
                .lockDuration(2000)
                .handler(((externalTask, externalTaskService) -> {
                    Object size = externalTask.getVariable("size");
                    Object count = externalTask.getVariable("count");
                    log.info("客户下单尺寸: {}，数量: {}", size, count);
                    String dest = "shanghai";
                    log.info("支付完成，准备发货到去 {}", dest);
                    Map<String, Object> destVar = Variables.createVariables().putValue("dest", dest);
                    externalTaskService.complete(externalTask, destVar);
                })).open();
    }
}
