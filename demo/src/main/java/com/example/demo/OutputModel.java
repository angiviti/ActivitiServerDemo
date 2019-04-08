package com.example.demo;

import org.activiti.bpmn.model.*;

import java.util.List;

public class OutputModel {

    private String processInstanceID;
    private BpmnModel bpmnModel;
    List<UserTask> userTasks;
    List<ScriptTask> scriptTasks;
    List<SequenceFlow> sequenceFlow;
    List<StartEvent> startEvents;
    List<EndEvent> endEvents;

    public OutputModel(String processInstanceID, BpmnModel bpmnModel) {
        this.processInstanceID = processInstanceID;
        this.bpmnModel = bpmnModel;
        this.userTasks =  bpmnModel.getProcesses().get(0).findFlowElementsOfType(UserTask.class);
        this.scriptTasks = bpmnModel.getProcesses().get(0).findFlowElementsOfType(ScriptTask.class);
        this.sequenceFlow = bpmnModel.getProcesses().get(0).findFlowElementsOfType(SequenceFlow.class);
        this.startEvents = bpmnModel.getProcesses().get(0).findFlowElementsOfType(StartEvent.class);
        this.endEvents = bpmnModel.getProcesses().get(0).findFlowElementsOfType(EndEvent.class);
    }

    public String getProcessInstanceID() {
        return processInstanceID;
    }

    public BpmnModel getBpmnModel() {
        return bpmnModel;
    }

    public List<UserTask> getUserTasks() {
        return userTasks;
    }

    public List<ScriptTask> getScriptTasks() {
        return scriptTasks;
    }

    public List<SequenceFlow> getSequenceFlow() {
        return sequenceFlow;
    }

    public List<StartEvent> getStartEvents() {
        return startEvents;
    }

    public List<EndEvent> getEndEvents() {
        return endEvents;
    }
}
