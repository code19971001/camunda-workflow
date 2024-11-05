package com.itcode.camunda.controller;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping("userTask")
public class UserTaskController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;


    @GetMapping("/start/{processKey}")
    public String start(@PathVariable(value = "processKey") String processKey) {
        identityService.setAuthenticatedUserId("xiaoming");
        VariableMap variables = Variables.createVariables();
        LinkedList<String> leaders = new LinkedList<>();
        leaders.add("code1997");
        leaders.add("admin");
        variables.put("leaders", leaders);
        variables.put("origin_days",15);
        identityService.setAuthenticatedUserId("xiaoming");
        runtimeService.startProcessInstanceByKey(processKey, variables);
        return "success";
    }

}
