package fr.deloitte.delegate;

import fr.deloitte.camundaDto.SecurityCamunda;
import fr.deloitte.models.Security;
import fr.deloitte.services.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component @AllArgsConstructor
public class BuySecurityDelegate implements JavaDelegate {

    private SecurityService securityService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getVariable("security");
        SecurityCamunda security = (SecurityCamunda) delegateExecution.getVariable("security");
        int quantity = (int) delegateExecution.getVariable("RequestedQuantity");
        log.info("Buying Security = " + security);
        quantity = securityService.buySecurityBySymbol(security.getSymbol(), quantity);
        security.setQuantity(quantity);
        log.info("done!\nSecurity = " + security);
    }
}
