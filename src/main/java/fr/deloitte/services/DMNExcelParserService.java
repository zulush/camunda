package fr.deloitte.services;

import fr.deloitte.models.DmnTable;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component @AllArgsConstructor
public class DMNExcelParserService {

    private ExcelFileService excelFileService;

    public DmnTable parse(String filePath, int inputNumber, int outputNumber) throws IOException {
        Sheet sheet = excelFileService.getSheet(0, excelFileService.getWorkbook(filePath));
        List<Row> rows = getAllRows(sheet);
        List<String> inputs = new ArrayList<>();
        List<String> outputs = new ArrayList<>();
        for (Row row: rows) {
            for (int i = 0; i <inputNumber; i++){
                inputs.add(getCellValueString(row.getCell(i)));
            }
            for (int j = inputNumber; j <outputNumber+inputNumber; j++){
                outputs.add(getCellValueString(row.getCell(j)));
            }
        }

        return new DmnTable(inputNumber, outputNumber, inputs, outputs);
    }

    List<Row> getAllRows(Sheet sheet){
        List<Row> result = new ArrayList<>();
        for(int i = 1; i < sheet.getLastRowNum()+1; i++)
            result.add(sheet.getRow(i));

        return result;
    }

    private String getCellValueString(Cell cell){
        if(cell == null){
            return "";
        } else {
            CellType cellType = cell.getCellType();
            String val = "";

            switch (cellType){
                case STRING:
                    val = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)){
                        DataFormatter dataFormatter = new DataFormatter();
                        val = dataFormatter.formatCellValue(cell);
                    } else {
                        val = cell.getNumericCellValue() + "";
                    }
                    break;
                case BOOLEAN:
                    val = String.valueOf(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    break;
            }
            return val.strip();
        }
    }
}
