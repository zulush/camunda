package fr.deloitte.services;

import fr.deloitte.camundaDto.SecurityCamunda;
import fr.deloitte.mapper.SecurityMapper;
import fr.deloitte.models.Security;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SecurityService {

    private ExcelOperationsService excelOperationsService;
    private SecurityMapper securityMapper;
    public List<Security> getSercuritiesBySymbol(String symbol) throws IOException {
        return securityMapper.toSecurities(excelOperationsService.getSecurityRowsBySymbol(symbol));
    }

    public int buySecurityBySymbol(String symbol, int quantity) throws IOException {
        return excelOperationsService.updateSecurityQuantity(symbol, quantity);
    }

}
