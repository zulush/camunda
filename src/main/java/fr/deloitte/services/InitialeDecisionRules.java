package fr.deloitte.services;

import fr.deloitte.models.DmnTable;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.xlsx.XlsxConverter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.ApplicationRunner;

@Component @Slf4j
public class InitialeDecisionRules {

    private final String EXCEL_PATH = "C:\\Users\\szoui\\OneDrive\\Desktop\\security-buy\\src\\main\\resources\\decisions.xlsx";
    private final String DMN_PATH = "C:\\Users\\szoui\\OneDrive\\Desktop\\security-buy\\src\\main\\resources\\decision.dmn";


    public boolean compareExcelWithDMN(){

        DmnTable excelDecisions = getExcelDmnTable();
        System.out.println(excelDecisions);

        return false;
    }

    private DmnTable getExcelDmnTable() {
        return null;
    }




    public void generateDMN() throws FileNotFoundException {
        /*log.info("Initiale Decision Rules ...");
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
            String newContent = content.replace("<!--add rules here-->", RULES_CODE);
            Files.write(Paths.get(FILE_PATH), newContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }*/
        // Create an input stream from the file path
        InputStream xlsxInputStream = new FileInputStream(EXCEL_PATH);

        // convert
        XlsxConverter converter = new XlsxConverter();
        DmnModelInstance dmnModelInstance = converter.convert(xlsxInputStream);

        // write
        OutputStream dmnOutputStream = new FileOutputStream(DMN_PATH); // open outputstream to file here
        Dmn.writeModelToStream(dmnOutputStream, dmnModelInstance);
    }
}
