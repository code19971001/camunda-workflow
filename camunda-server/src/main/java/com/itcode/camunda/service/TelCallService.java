package com.itcode.camunda.service;

import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

@Service("telCall")
public class TelCallService {

    public int doCall(ExecutionEntity execution) {
        System.out.println("call tel service : doCall");
        Object repairManName = execution.getVariable("repairManName");
        int score = 10;
        System.out.println("给 repairMan :" + repairManName + ",打分为:" + score);
        return score;
    }

    public void getScore(ExecutionEntity execution) {
        System.out.println("call tel service : getScore");
        int score = (int) execution.getVariable("score");
        System.out.println("评分为: " + score);
    }
}
