package fr.deloitte.mapper;

import fr.deloitte.camundaDto.SecurityCamunda;
import fr.deloitte.models.Security;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@AllArgsConstructor
public class SecurityCamundaMapper {

    public SecurityCamunda toSecurityCamunda(Security security){
        SecurityCamunda securityCamunda = new SecurityCamunda();
        securityCamunda.setCountry(security.getCountry());
        securityCamunda.setIndustry(security.getIndustry());
        securityCamunda.setName(security.getName());
        securityCamunda.setQuantity(security.getQuantity());
        securityCamunda.setSector(security.getSector());
        securityCamunda.setSymbol(security.getSymbol());
        securityCamunda.setLastSale(security.getLastSale());

        return securityCamunda;
    }

    public List<SecurityCamunda> toSecuritiesCamunda(List<Security> securities){
        List<SecurityCamunda> securitiesCamunda = new ArrayList<SecurityCamunda>();
        for (Security security: securities){
            securitiesCamunda.add(this.toSecurityCamunda(security));
        }
        return securitiesCamunda;
    }
}
