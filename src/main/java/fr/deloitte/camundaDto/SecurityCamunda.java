package fr.deloitte.camundaDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityCamunda {

    private String symbol;
    private String name;
    private float lastSale;
    private String country;
    private String sector;
    private String industry;
    private int quantity;

    @Override
    public String toString() {
        return "SecurityCamunda{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", lastSale=" + lastSale +
                ", country='" + country + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
