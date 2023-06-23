package fr.deloitte.delegate;

import fr.deloitte.services.CustomTaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

@Component @Slf4j @AllArgsConstructor
public class AssignTaskDelegate implements JavaDelegate {

    private CustomTaskService taskService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Assign Task Delegate ... ");
        this.createUsers(delegateExecution);
        //String username = (String) delegateExecution.getVariable("result");
        String username = "demo";
        //String taskName = "Assigned Task ( Type = "+ delegateExecution.getVariable("type")+")";
        String taskName = "just keep testing";
        CustomTaskService.assignNewTask(username, taskName, delegateExecution);
    }

    private void createUsers(DelegateExecution execution) {
        log.info("create Users ... ");
        IdentityService identityService = execution.getProcessEngineServices().getIdentityService();

        User existingUser = identityService.createUserQuery().userId("idlahcen").singleResult();

        if(existingUser != null)
            return;

        String[] names = {"idlahcen", "tarik", "hatim", "hadik"};

        for (String name: names) {
            UserEntity newUser = new UserEntity();
            newUser.setId(name);
            newUser.setFirstName(name);
            newUser.setLastName(name);
            newUser.setPassword(name);
            identityService.saveUser(newUser);
        }


        System.out.println("Users created: ");
    }

}
