package ics.upjs.sk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import javax.persistence.AttributeConverter;

/**
 *
 * @author Raven
 */
public class FlexnyKonvertor implements AttributeConverter<SklonovatelneSlovo, String>{
    
    private static final String SEPARATOR_PADOV = "|";
    
    private static final String SEPARATOR_SLOV = ";";
    
    @Override
    public String convertToDatabaseColumn(SklonovatelneSlovo zoznam) {
        StringJoiner sj = new StringJoiner(SEPARATOR_PADOV);
        sj.add(vytvorPad(zoznam.getNominativ()));
        sj.add(vytvorPad(zoznam.getGenitiv()));
        sj.add(vytvorPad(zoznam.getDativ()));
        sj.add(vytvorPad(zoznam.getAkuzativ()));
        sj.add(vytvorPad(zoznam.getLokal()));
        sj.add(vytvorPad(zoznam.getInstrumental()));
        return sj.toString();
    }
    
    @Override
    public SklonovatelneSlovo convertToEntityAttribute(String vstup) {
        SklonovatelneSlovo vysledneSlovo = new SklonovatelneSlovo();
        int pocitadlo = 0;
        int idx = 0;
        for (int i = 0; i < vstup.length(); i++) {
            if(vstup.charAt(i) == '|') {
                if(i > idx) {
                    if(pocitadlo == 0) {
                        vysledneSlovo.setNominativ(vytvorZoznamSlov(vstup.substring(idx, i)));
                    } else if(pocitadlo == 1) {
                        vysledneSlovo.setGenitiv(vytvorZoznamSlov(vstup.substring(idx, i)));
                    } else if(pocitadlo == 2) {
                        vysledneSlovo.setDativ(vytvorZoznamSlov(vstup.substring(idx, i)));
                    } else if(pocitadlo == 3) {
                        vysledneSlovo.setAkuzativ(vytvorZoznamSlov(vstup.substring(idx, i)));
                    } else if(pocitadlo == 4) {
                        vysledneSlovo.setLokal(vytvorZoznamSlov(vstup.substring(idx, i)));
                    }
                }
                idx = i + 1;
                pocitadlo++;
            }
        }
        if(idx < vstup.length() - 1) {
            vysledneSlovo.setLokal(vytvorZoznamSlov(vstup.substring(idx, vstup.length())));
        }
        return vysledneSlovo;
    }
    
    private String vytvorPad(List<String> vstup) {
        StringJoiner sj = new StringJoiner(SEPARATOR_SLOV);
        for (String slovo : vstup) {
            sj.add(slovo);
        }
        return sj.toString();
    }
    
    private List<String> vytvorZoznamSlov(String vstup) {
        List<String> novyZoznam = new ArrayList<>();
        Scanner citac = new Scanner(vstup);
        citac.useDelimiter(SEPARATOR_SLOV);
        while (citac.hasNext()) {
            novyZoznam.add(citac.next());
        }
        return  novyZoznam;
    }
    
}
