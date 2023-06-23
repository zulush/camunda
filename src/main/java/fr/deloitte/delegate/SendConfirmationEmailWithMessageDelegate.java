package fr.deloitte.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendConfirmationEmailWithMessageDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("send confirmation email ...");

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("Send Confirmation Email")
                .correlate();
    }
}
