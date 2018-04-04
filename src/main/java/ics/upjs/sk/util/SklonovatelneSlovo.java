package ics.upjs.sk.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raven
 */
public class SklonovatelneSlovo {
    
    List<String> nominativ = new ArrayList<>();
    
    List<String> genitiv = new ArrayList<>();
    
    List<String> dativ = new ArrayList<>();
    
    List<String> akuzativ = new ArrayList<>();
    
    List<String> lokal = new ArrayList<>();
    
    List<String> instrumental = new ArrayList<>();
    
    public List<String> getNominativ() {
        return nominativ;
    }
    
    public void setNominativ(List<String> nominativ) {
        this.nominativ = nominativ;
    }
    
    public List<String> getGenitiv() {
        return genitiv;
    }
    
    public void setGenitiv(List<String> genitiv) {
        this.genitiv = genitiv;
    }
    
    public List<String> getDativ() {
        return dativ;
    }
    
    public void setDativ(List<String> dativ) {
        this.dativ = dativ;
    }
    
    public List<String> getAkuzativ() {
        return akuzativ;
    }
    
    public void setAkuzativ(List<String> akuzativ) {
        this.akuzativ = akuzativ;
    }
    
    public List<String> getLokal() {
        return lokal;
    }
    
    public void setLokal(List<String> lokal) {
        this.lokal = lokal;
    }
    
    public List<String> getInstrumental() {
        return instrumental;
    }
    
    public void setInstrumental(List<String> instrumental) {
        this.instrumental = instrumental;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String string : nominativ) {
            sb.append(string).append(";");
        }
        sb.append("|");
        for (String string : genitiv) {
            sb.append(string).append(";");
        }
        sb.append("|");
        for (String string : dativ) {
            sb.append(string).append(";");
        }
        sb.append("|");
        for (String string : akuzativ) {
            sb.append(string).append(";");
        }
        sb.append("|");
        for (String string : lokal) {
            sb.append(string).append(";");
        }
        sb.append("|");
        for (String string : instrumental) {
            sb.append(string).append(";");
        }
        return sb.toString();
    }
    
}
