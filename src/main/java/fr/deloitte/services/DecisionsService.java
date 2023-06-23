package fr.deloitte.services;

import fr.deloitte.configuration.excel.ExcelConfig;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component @AllArgsConstructor
public class DecisionsService {

    private ExcelConfig excelConfig;
    private ExcelOperationsService excelOperationsService;
    
    public Map<String, String> getDecisionsTable() {
        return null;
    }
}
