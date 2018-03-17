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
    
    private final double SYNONYMICKA_VAHA = 0.95;
    
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
                synonymumDao.najdiClenovRaduPodlaSlova(slovo)));
        return vysledok;
    }
    
    public List<VahaSlova> vypocitajHodnotySynonym(String slovo) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        List<Synonymum> dominanta = synonymumDao.najdiDominanty(slovo);
        List<Synonymum> clenRadu =  synonymumDao.najdiClenovRadu(slovo);
        for (Synonymum synonymum : dominanta) {
            vyslednyZoznam.addAll(vypocitajVahyDominanty(synonymum.getSynonyma()));
        }
        for (Synonymum synonymum : clenRadu) {
            vyslednyZoznam.addAll(vypocitajVahyClenaRadu(synonymum));
        }
        return vyslednyZoznam;
    }
    
    private List<VahaSlova> vypocitajVahyClenaRadu(Synonymum slovo) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        List<Synonymum> dominanty = synonymumDao.najdiDominanty(slovo.getSynonyma());
        for (Synonymum dominanta : dominanty) {  
            vyslednyZoznam.addAll(vypocitajVahyPreClenaRaduNaZakladeDominanty(dominanta, slovo.getSlovo()));
        }
        return vyslednyZoznam;
    }
    
    private List<VahaSlova> vypocitajVahyPreClenaRaduNaZakladeDominanty(Synonymum dominanta, String slovo) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        vyslednyZoznam.add(new VahaSlova(dominanta.getSlovo(), 1.0));
        double vahaClenaRadu = 1;
        int indexDo = 0;
        boolean pocitajHned = false;
        Scanner citacSynonyma = new Scanner(dominanta.getSynonyma());
        citacSynonyma.useDelimiter(";");
        double vaha = 1;
        while(citacSynonyma.hasNext()) {
            String synonymum = citacSynonyma.next();
            vaha = vaha * SYNONYMICKA_VAHA;
            if(synonymum.equals(slovo)) {
                pocitajHned = true;
                vyslednyZoznam.get(0).setVaha(vaha);
                vahaClenaRadu = vaha;
                continue;
            }
            if(pocitajHned) {
                vyslednyZoznam.add(new VahaSlova(synonymum, vaha * vahaClenaRadu));
            } else {
                vyslednyZoznam.add(new VahaSlova(synonymum, vaha));
                indexDo++;
            }
        }
        if(indexDo > 0) {
            for (int i = 1; i < indexDo; i++) {
                vyslednyZoznam.get(i).setVaha(vyslednyZoznam.get(i).getVaha() * vahaClenaRadu);
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
