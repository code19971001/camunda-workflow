package com.itcode.task;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class VideoNegativeService {


    @ExternalTaskSubscription(
            topicName = "video_negative_validation2",
            processDefinitionKey = "process_01_parallel_gateway",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler videoNegativeServiceHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务检查视频名称");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String videoName = externalTask.getVariable("video_name");
            if (videoName.isEmpty() || videoName.contains("死")) {
                System.out.println("视频名称不符合规范~~~");
                externalTaskService.handleFailure(externalTask, "视频名称不符合规范~~~", "打印异常堆栈信息", 0, 10000);
            } else {
                System.out.println("视频名称符合规范");
                externalTaskService.complete(externalTask);
            }
        };
    }


    @ExternalTaskSubscription(
            topicName = "video_negative_validation3",
            processDefinitionKey = "process_01_embedded_sub_process",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler videoNegativeServiceSubProcessorHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务检查视频名称");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String videoName = externalTask.getVariable("video_name");
            if (videoName.isEmpty() || videoName.contains("死")) {
                System.out.println("视频名称不符合规范~~~");
                externalTaskService.handleFailure(externalTask, "视频名称不符合规范~~~", "打印异常堆栈信息", 0, 10000);
            } else {
                System.out.println("视频名称符合规范");
                externalTaskService.complete(externalTask);
            }
        };
    }


    @ExternalTaskSubscription(
            topicName = "video_negative_validation3",
            processDefinitionKey = "process_01_callactivity_sub_process",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler videoNegativeServiceSubProcessor3Handler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务检查视频名称");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String videoName = externalTask.getVariable("targetVideoName");
            if (videoName.isEmpty() || videoName.contains("死")) {
                System.out.println("视频名称不符合规范~~~");
                externalTaskService.handleFailure(externalTask, "视频名称不符合规范~~~", "打印异常堆栈信息", 0, 10000);
            } else {
                System.out.println("视频名称符合规范");
                externalTaskService.complete(externalTask);
            }
        };
    }

}
