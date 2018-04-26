package ics.upjs.sk.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author peter
 */
public class VahaSlova {
    
    @JsonProperty("slovo")
    private String slovo;
    
    @JsonProperty("vaha")
    private Double vaha;
    
    @JsonProperty("poznamka")
    private String poznamka;
    
    
    @JsonProperty("idZoznamu")
    private Integer idZoznamu;
    
    
    public VahaSlova() {
    }
    
    public VahaSlova(String slovo, Double vaha) {
        this.slovo = slovo;
        this.vaha = vaha;
    }
    
    public VahaSlova(String slovo, Double vaha, String poznamka) {
        this.slovo = slovo;
        this.vaha = vaha;
        this.poznamka = poznamka;
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
    
    public String getPoznamka() {
        return poznamka;
    }
    
    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    public Integer getIdZoznamu() {
        return idZoznamu;
    }

    public void setIdZoznamu(Integer idZoznamu) {
        this.idZoznamu = idZoznamu;
    }

    @Override
    public String toString() {
        return "VahaSlova{" + "slovo=" + slovo + ", vaha=" + vaha + ", poznamka=" + poznamka + ", idZoznamu=" + idZoznamu + '}';
    }
    
}
