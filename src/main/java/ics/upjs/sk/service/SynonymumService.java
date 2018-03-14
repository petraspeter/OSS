package ics.upjs.sk.service;

import ics.upjs.sk.core.synonyma.Synonymum;
import ics.upjs.sk.dao.SynonymumDao;
import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.util.VahaSlova;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Raven
 */
public class SynonymumService {
    
    private final double SYNONYMICKA_VAHA = 0.93;
    
    private final SynonymumDao synonymumDao;
    
    public SynonymumService(SynonymumDao synonymumDao) {
        this.synonymumDao = synonymumDao;
    }
    
    public List<SynonymumDto> vratVsetkySynonyma() {
        return new SynonymumDto().vratDTOSynonym(synonymumDao.findAll());
    }
    
    public SynonymumDto najdiPodlaId(Long id) {
        return new SynonymumDto().vytvorSynonymumDtoZoSynonyma(synonymumDao.najdiPodlaId(id));
    }
    
    public List<SynonymumDto> vratDominanty(String dominanta) {
        return new SynonymumDto().vratDTOSynonym(synonymumDao.najdiDominanty(dominanta));
    }
    
    public List<SynonymumDto> vratSynonymickeRady(String slovo) {
        List<SynonymumDto> vysledok = new SynonymumDto().vratDTOSynonym(
                synonymumDao.najdiDominanty(slovo));
        vysledok.addAll(new SynonymumDto().vratDTOSynonym(
                synonymumDao.najdiClenovRaduPodlaSlova(slovo)));
        return vysledok;
    }
    
    public List<VahaSlova> vypocitajHodnotySynonym(String slovo) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        List<Synonymum> dominanta = synonymumDao.najdiDominanty(slovo);
        List<Synonymum> clenRadu =  synonymumDao.najdiClenovRaduPodlaSlova(slovo);
        for (Synonymum synonymum : dominanta) {
            vyslednyZoznam.addAll(vypocitajVahyDominanty(synonymum.getSynonyma()));
        }
        for (Synonymum synonymum : clenRadu) {
            vyslednyZoznam.addAll(vypocitajVahyClenaRadu(synonymum.getSynonyma(), slovo, synonymum.getSlovo()));
        }
        return vyslednyZoznam;
    }
    
    private List<VahaSlova> vypocitajVahyClenaRadu(String synonyma, String clenRadu, String dominanta) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        Scanner citacSynonyma = new Scanner(synonyma);
        citacSynonyma.useDelimiter(";");
        double vaha = 1;
        double hodnotaClena = 0;
        vyslednyZoznam.add(new VahaSlova(dominanta, 1.0));
        while(citacSynonyma.hasNext()) {
            String slovo = citacSynonyma.next();
            vaha = vaha * SYNONYMICKA_VAHA;
            if(!clenRadu.equals(slovo)) {
                vyslednyZoznam.add(new VahaSlova(slovo, vaha));
            } else {
                hodnotaClena = vaha;
            }
        }
        for (VahaSlova vahaSlova : vyslednyZoznam) {
            vahaSlova.setVaha(vahaSlova.getVaha() * hodnotaClena);
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
