package fr.deloitte.delegate;

import fr.deloitte.camundaDto.SecurityCamunda;
import fr.deloitte.mapper.SecurityCamundaMapper;
import fr.deloitte.mapper.SecurityMapper;
import fr.deloitte.services.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j @Component @AllArgsConstructor
public class InventoryCheckDelegate implements JavaDelegate {

    private SecurityService securityService;
    private SecurityCamundaMapper securityCamundaMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Execute InventoryCheckDelegate");
        String symbol = (String) delegateExecution.getVariable("symbol");
        int requestedQuantity = (Integer) delegateExecution.getVariable("requestedQuantity");
        delegateExecution.setVariable("RequestedQuantity", requestedQuantity);

        List<SecurityCamunda> securitiesCamunda = this.securityCamundaMapper.toSecuritiesCamunda(securityService.getSercuritiesBySymbol(symbol));

        if(securitiesCamunda.size() > 0){
            SecurityCamunda security = securitiesCamunda.get(0);

            if(security.getQuantity() == 0){
                delegateExecution.setVariable("enoughQuantity", false);
                delegateExecution.setVariable("exist", false);
            } else {
                delegateExecution.setVariable("exist", true);
                delegateExecution.setVariable("security", security);
                if(security.getQuantity() >= requestedQuantity)
                    delegateExecution.setVariable("enoughQuantity", true);
                else
                    delegateExecution.setVariable("enoughQuantity", false);
            }

        } else {
            delegateExecution.setVariable("enoughQuantity", false);
            delegateExecution.setVariable("exist", false);
        }
    }
}
