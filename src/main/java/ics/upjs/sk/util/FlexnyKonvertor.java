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
        String[] pady = vstup.split(SEPARATOR_PADOV);
        vysledneSlovo.setNominativ(vytvorZoznamSlov(pady[0]));
        vysledneSlovo.setGenitiv(vytvorZoznamSlov(pady[1]));
        vysledneSlovo.setDativ(vytvorZoznamSlov(pady[2]));
        vysledneSlovo.setAkuzativ(vytvorZoznamSlov(pady[3]));
        vysledneSlovo.setLokal(vytvorZoznamSlov(pady[4]));
        vysledneSlovo.setInstrumental(vytvorZoznamSlov(pady[5]));
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
