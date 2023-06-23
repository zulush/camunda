package fr.deloitte.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Security {
    private String symbol;
    private String name;
    private float lastSale;
    private float netChange;
    private float change;
    private float marketCap;
    private String country;
    private String IPOYear;
    private double volume;
    private String sector;
    private String industry;
    private int quantity;

    @Override
    public String toString() {
        return "Security{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", lastSale=" + lastSale +
                ", netChange=" + netChange +
                ", change=" + change +
                ", marketCap=" + marketCap +
                ", country='" + country + '\'' +
                ", IPOYear='" + IPOYear + '\'' +
                ", volume=" + volume +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
