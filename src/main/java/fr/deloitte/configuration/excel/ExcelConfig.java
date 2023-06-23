package fr.deloitte.configuration.excel;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("excel-config")
@Data
public class ExcelConfig {

    static public final String SYMBOL_COL = "symbol";
    static public final String NAME_COL = "name";
    static public final String LAST_SALE_COL = "lastSale";
    static public final String NET_CHANGE_COL = "netChange";
    static public final String CHANGE_COL = "change";
    static public final String MARKET_CAP_COL = "marketCap";
    static public final String COUNTRY_COL = "country";
    static public final String IPO_YEAR_COL = "ipoYear";
    static public final String VOLUME_SECTOR_COL = "volumeSector";
    static public final String SECTOR_COL = "sector";
    static public final String INDUSTRY_COL = "industry";
    static public final String QUANTITY_COL = "quantity";

    private String fileLocation;
    private int sheet;
    private int header;
    private Map<String, Integer> businessRuleCol;

}
