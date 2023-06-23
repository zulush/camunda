package fr.deloitte.services;

import fr.deloitte.configuration.excel.ExcelConfig;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ExcelFileService {

    public Workbook getWorkbook(String fileLocation) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        return new XSSFWorkbook(file);
    }

    public Sheet getSheet(int sheetNumber, Workbook workbook){
        return workbook.getSheetAt(sheetNumber);
    }

    Sheet getSheetFromFileName(String filePath, int sheet) throws IOException {
        return this.getSheet(sheet, this.getWorkbook(filePath));
    }

    void saveWorkbook(String fileLocation, Workbook workbook) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
