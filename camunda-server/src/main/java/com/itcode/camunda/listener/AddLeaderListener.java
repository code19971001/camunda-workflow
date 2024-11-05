package com.itcode.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component("addLeaders")
public class AddLeaderListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        long vacationDays = (long) execution.getVariable("vacation_days");
        System.out.println("请假天数为:" + vacationDays);
        List<String> leaders = new LinkedList<>();
        if (vacationDays <= 3) {
            leaders.add("code1997");
        } else {
            leaders.add("code1997");
            leaders.add("admin");
        }
        execution.setVariable("leaders", leaders);
        System.out.println("设置leader成功! leader.size = " + leaders.size());
    }
}
