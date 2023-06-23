package fr.deloitte.services;

import fr.deloitte.configuration.excel.ExcelConfig;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.deloitte.configuration.excel.ExcelConfig.*;

@Component
@AllArgsConstructor
public class ExcelOperationsService {

    final private ExcelConfig excelConfig;
    private ExcelFileService excelFileService;

    List<Row> getSecurityRowsBySymbol(String symbol) throws IOException {
        Sheet sheet = excelFileService.getSheetFromFileName(excelConfig.getFileLocation(), excelConfig.getSheet());

        return getRowBySearchValue(symbol, excelConfig.getBusinessRuleCol().get(SYMBOL_COL), sheet);
    }

    List<Row> getRowBySearchValue(String searchValue, int columnIndex, Sheet sheet){
        List<Row> result = new ArrayList<Row>();
        Row row;
        for(int i = 0; i < sheet.getLastRowNum(); i++){
            row = sheet.getRow(i);
            if(this.getCellValueString(row.getCell(columnIndex)).equals(searchValue))
                result.add(row);
        }

        return result;
    }

    void deleteRow(Sheet sheet, Row row){

    }
    void deleteRow(Sheet sheet, int rowNum){

    }

    public String getCellValueString(Cell cell){
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

    public int updateSecurityQuantity(String symbol, int quantity) throws IOException {
        Workbook workbook = excelFileService.getWorkbook(excelConfig.getFileLocation());
        Sheet sheet = excelFileService.getSheet(excelConfig.getSheet(), workbook);
        Row row = this.getRowBySearchValue(symbol, excelConfig.getBusinessRuleCol().get(SYMBOL_COL), sheet).get(0);
        Cell cell = row.getCell(excelConfig.getBusinessRuleCol().get(QUANTITY_COL));
        int existingQuantity = Integer.parseInt(this.getCellValueString(cell).replace(".0", ""));
        if(existingQuantity > quantity)
            cell.setCellValue(existingQuantity-quantity);
        else{
            cell.setCellValue(0);
            quantity = existingQuantity;
        }
        excelFileService.saveWorkbook(excelConfig.getFileLocation(), workbook);

        return quantity;
    }

}
