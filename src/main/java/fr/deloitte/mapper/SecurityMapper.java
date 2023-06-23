package fr.deloitte.mapper;

import fr.deloitte.configuration.excel.ExcelConfig;
import fr.deloitte.models.Security;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static fr.deloitte.configuration.excel.ExcelConfig.*;

@Component @AllArgsConstructor
public class SecurityMapper {

    private ExcelConfig excelConfig;

    public List<Security> toSecurities(List<Row> securityRow) {
        List<Security> securities = new ArrayList<Security>();
        for (Row row: securityRow){
            securities.add(this.toSecurity(row));
        }
        return securities;
    }

    public Security toSecurity(Row row) {
        Security security = new Security();
        security.setSymbol(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(SYMBOL_COL))));
        security.setChange(Float.parseFloat(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(CHANGE_COL))).replace("%", "")));
        security.setCountry(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(COUNTRY_COL))));
        security.setName(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(NAME_COL))));
        security.setIndustry(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(INDUSTRY_COL))));
        security.setIPOYear(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(IPO_YEAR_COL))));
        security.setLastSale(Float.parseFloat(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(LAST_SALE_COL))).replace("$", "")));
        security.setMarketCap(Float.parseFloat(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(MARKET_CAP_COL)))));
        security.setSector(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(SECTOR_COL))));
        security.setVolume(Double.parseDouble((this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(VOLUME_SECTOR_COL))))));
        security.setQuantity(Integer.parseInt(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(QUANTITY_COL))).replace(".0", "")));
        security.setNetChange(Float.parseFloat(this.getCellValueString(row.getCell(excelConfig.getBusinessRuleCol().get(NET_CHANGE_COL)))));
        return security;
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
