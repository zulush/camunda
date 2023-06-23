package fr.deloitte;

import fr.deloitte.models.DmnTable;
import fr.deloitte.services.DMNExcelParserService;
import fr.deloitte.services.DMNParserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.xlsx.XlsxConverter;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;

@Component @AllArgsConstructor @Slf4j
public class checkDMNChanges /*implements CommandLineRunner*/ {

    private DMNExcelParserService excelParserService;
    private DMNParserService dmnParserService;
    private static final String EXCEL_FILE = "C:\\Users\\szoui\\OneDrive\\Desktop\\security-buy\\src\\main\\resources\\decisions.xlsx";
    private static final String DMN_FILE = "C:\\Users\\szoui\\OneDrive\\Desktop\\security-buy\\src\\main\\resources\\decisionOldOne.dmn";
    //@Override
    public void run(String... args) throws Exception {
        DmnTable dmnExcel = excelParserService.parse(EXCEL_FILE, 1, 1);
        DmnTable currentDmn = dmnParserService.parse(DMN_FILE);

        if(!dmnExcel.equals(currentDmn)){
            log.info("the DMN Excel File is updated !");
            log.info("updating DMN file ...");
            generateDMN();
            log.info("restating the execution ...");
            Application.rerun();
        }



    }
    public void generateDMN() throws FileNotFoundException {
        // Create an input stream from the file path
        InputStream xlsxInputStream = new FileInputStream(EXCEL_FILE);

        // convert
        XlsxConverter converter = new XlsxConverter();
        DmnModelInstance dmnModelInstance = converter.convert(xlsxInputStream);

        // write
        OutputStream dmnOutputStream = new FileOutputStream(DMN_FILE); // open outputstream to file here
        Dmn.writeModelToStream(dmnOutputStream, dmnModelInstance);
    }
}

