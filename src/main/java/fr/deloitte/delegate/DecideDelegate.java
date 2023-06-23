package fr.deloitte.delegate;



import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.kie.dmn.api.core.DMNModel;

import org.springframework.stereotype.Component;

import java.io.InputStream;
@Component
public class DecideDelegate implements JavaDelegate {

    private static final String DMN_FILE = "C:\\Users\\szoui\\OneDrive\\Desktop\\security-buy\\src\\main\\resources\\decisions.dmn";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


    }
}
