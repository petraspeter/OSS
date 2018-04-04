package ics.upjs.sk.service;

import ics.upjs.sk.core.synonyma.Synonymum;
import ics.upjs.sk.dao.SynonymumDao;
import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.util.VahaSlova;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raven
 */
public class SynonymumService {
    
    private final double SYNONYMICKA_VAHA = 0.95;
    private final double VAHA_CLENA = 0.75;
    
    private final SynonymumDao synonymumDao;
    
    public SynonymumService(SynonymumDao synonymumDao) {
        this.synonymumDao = synonymumDao;
    }
    
    public SynonymumDto najdiPodlaId(Long id) {
        return new SynonymumDto().vytvorSynonymumDtoZoSynonyma(synonymumDao.najdiPodlaId(id));
    }
    
    public List<SynonymumDto> vratDominanty(String dominanta) {
        return new SynonymumDto().vratDTOSynonym(synonymumDao.najdiDominanty(dominanta));
    }
    
    public List<SynonymumDto> vratClenovRadu(String slovo) {
        return new SynonymumDto().vratDTOSynonym(synonymumDao.najdiClenovRadu(slovo));
    }
    
    public List<SynonymumDto> vratSynonymickeRady(String slovo) {
        List<SynonymumDto> vysledok = new SynonymumDto().vratDTOSynonym(
                synonymumDao.najdiDominanty(slovo));
        vysledok.addAll(new SynonymumDto().vratDTOSynonym(
                synonymumDao.najdiClenovRadu(slovo)));
        return vysledok;
    }
    
    public List<List<VahaSlova>> vypocitajHodnotySynonym(String slovo) {
        List<List<VahaSlova>> vyslednyZoznam = new ArrayList<>();
        List<Synonymum> dominanta = synonymumDao.najdiDominanty(slovo);
        List<Synonymum> clenRadu =  synonymumDao.najdiClenovRadu(slovo);
        for (Synonymum synonymum : dominanta) {
            vyslednyZoznam.add(vypocitajVahyDominanty(synonymum.getSynonyma()));
        }
        for (Synonymum synonymum : clenRadu) {
            vyslednyZoznam.add(vypocitajVahyClenaRadu(synonymum, slovo));
        }
        return vyslednyZoznam;
    }
    
    
    private List<VahaSlova> vypocitajVahyClenaRadu(Synonymum synonymum, String slovo) {
        List<VahaSlova> pomocnyZoznam = vypocitajVahyDominanty(synonymum.getSynonyma());
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        double vahaClenaRadu = VAHA_CLENA;
        for (VahaSlova vahaSlova : pomocnyZoznam) {
            if (vahaSlova.getSlovo().equals(slovo)) {
                vahaSlova.setSlovo(synonymum.getSlovo());
                vyslednyZoznam.add(vahaSlova);
                vahaClenaRadu = vahaSlova.getVaha() * VAHA_CLENA;
                break;
            }
        }
        for (VahaSlova vahaSlova : pomocnyZoznam) {
            if (!vahaSlova.getSlovo().equals(synonymum.getSlovo())) {
                vahaSlova.setVaha(vahaSlova.getVaha() * vahaClenaRadu);
                vyslednyZoznam.add(vahaSlova);
            }
        }
        return vyslednyZoznam;
    }
    
    private List<VahaSlova> vypocitajVahyDominanty(String synonyma) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        Scanner citacSynonyma = new Scanner(synonyma);
        citacSynonyma.useDelimiter(";");
        double vaha = 1;
        while(citacSynonyma.hasNext()) {
            vaha = vaha * SYNONYMICKA_VAHA;
            vyslednyZoznam.add(new VahaSlova(citacSynonyma.next(), vaha));
        }
        return vyslednyZoznam;
    }
    
}
