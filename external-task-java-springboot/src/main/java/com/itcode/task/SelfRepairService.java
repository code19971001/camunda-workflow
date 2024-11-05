package com.itcode.task;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SelfRepairService {


    @ExternalTaskSubscription(
            topicName = "try_self_repair",
            processDefinitionKey = "process_01_external_task",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler selfRepairServiceHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务尝试自修");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean isFree = externalTask.getVariable("isFree");
            if (isFree) {
                System.out.println("既然是免费的, 不想修了, 让师傅上门维修~~~");
                externalTaskService.handleFailure(externalTask, "既然是免费的, 不想修了, 让师傅上门维修~~~", "打印异常对战", 0, 10000);
            } else {
                System.out.println("需要收费, 自行进行修理");
                externalTaskService.complete(externalTask);
            }
        };
    }

}
