package fr.deloitte.services;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

@Component
public class CustomTaskService {
    public static void assignNewTask(String username, String taskName, DelegateExecution delegateExecution) {
        System.out.println("assigning new task ... ");
        TaskService taskService = delegateExecution.getProcessEngineServices().getTaskService();
        Task newTask = taskService.newTask();
        newTask.setName(taskName);
        newTask.setAssignee(username);
        taskService.saveTask(newTask);
    }
}
