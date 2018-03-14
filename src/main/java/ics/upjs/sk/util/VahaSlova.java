package ics.upjs.sk.util;

/**
 *
 * @author peter
 */
public class VahaSlova {

    private String slovo;
    
    private Double vaha;

    public VahaSlova() {
    }

    public VahaSlova(String slovo, Double vaha) {
        this.slovo = slovo;
        this.vaha = vaha;
    }

    public String getSlovo() {
        return slovo;
    }

    public void setSlovo(String slovo) {
        this.slovo = slovo;
    }

    public Double getVaha() {
        return vaha;
    }

    public void setVaha(Double vaha) {
        this.vaha = vaha;
    }
    
}
