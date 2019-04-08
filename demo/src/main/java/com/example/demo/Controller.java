package com.example.demo;


import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

    private final HashMap<String, ProcessInstance> processes = new HashMap<>();

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;


    @GetMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public OutputModel startMyProcess() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("a123456");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("a123456")
                .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        System.out.println("STARTED");
        return new OutputModel(pi.getId(), bpmnModel);

    }

    @PostMapping(value = "/taskone/{procId}")
    public void finish1Task(@PathVariable("procId") String procId,
                            @RequestParam("taskDefKey") String taskDefKey,
                            @RequestBody UserData userData){
        //TODO: VERIFICAR SE VEIO OS INPUTS TODOS
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", userData.getName());
        variables.put("city", userData.getCity());
        variables.put("cc", userData.getCc());

        Task task = taskService.createTaskQuery()
                .processInstanceId(procId)
                .active()
                .taskDefinitionKey(taskDefKey)
                .singleResult();

        taskService.complete(task.getId(),variables);
        System.out.println("END 1");
    }

    @PostMapping(value = "/tasktwo/{procId}")
    public void finish2Task(@PathVariable("procId") String procId,
                            @RequestParam("taskDefKey") String taskDefKey,
                            @RequestBody Product product){

        Map<String, Object> variables = new HashMap<>();
        variables.put("product", product.getProduct());

        Task task = taskService.createTaskQuery()
                .processInstanceId(procId)
                .active()
                .taskDefinitionKey(taskDefKey)
                .singleResult();
        taskService.complete(task.getId(),variables);
        System.out.println("END 2");
    }
}