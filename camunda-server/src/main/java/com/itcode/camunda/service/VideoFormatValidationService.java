package com.itcode.camunda.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("videoFormatValidation")
public class VideoFormatValidationService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("开始校验视频格式~~");
        String videoFormat = (String)execution.getVariable("video_format");
        System.out.println("视频format为"+videoFormat);
    }
}
