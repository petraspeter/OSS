package ics.upjs.sk.service;

import ics.upjs.sk.core.ssj.MorfologickaDefiniciaSSJ;
import ics.upjs.sk.core.ssj.SlovoSSJ;
import ics.upjs.sk.dao.SsjDao;
import ics.upjs.sk.util.SklonovatelneSlovo;
import ics.upjs.sk.util.VahaSlova;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Raven
 */
public class SSJService {
    
    private final double KOEFICIENT_PADU = 0.97;
    
    private final double VAHA_SLOVESA = 0.8;
    private final double VAHA_PODSTATNE = 0.75;
    private final double VAHA_PRIDAVNE = 0.7;
    private final double VAHA_PRISLOVKY = 0.65;
    private final double VAHA_CISLOVKY = 0.6;
    private final double VAHA_ZAMENA = 0.55;
    
    // na rozdiel od synonymickeho slovnika, nemame zaruceny synonymicky rad, konstanta bude teda rovnaka pre vsetky
    // slova, vaha odpoveda vahe stvrteho clena synonymickeho radu 0.95^4
    private final double VAHA_SYNONYMA = 0.95;
    // slova v skupine su slovne drhuy odvodene od podstatneho mena
    //vaha odpoveda vahe siedmeho clena synonymickeho radu 0.95^7
    private final double VAHA_SKUPINY = 0.8145;
    
    private final SsjDao ssjDao;
    
    public SSJService(SsjDao ssjDao) {
        this.ssjDao = ssjDao;
    }
    
    public List<SlovoSSJ> najdiPodlaId(Long id){
        return ssjDao.najdiSlovoPodlaId(id);
    }
    
    public List<SlovoSSJ> najdiPodlaSlova(String slovo){
        return ssjDao.najdiSlovo(slovo);
    }
    
    public List<List<VahaSlova>> vypocitajVahuSuvisiacichSlov(String slovo) {
        List<List<VahaSlova>> vyslednyZoznam = new ArrayList<>();
        List<SlovoSSJ> vyhladaneSlova = ssjDao.najdiSlovo(slovo);
        for (SlovoSSJ slovoSSJ : vyhladaneSlova) {
            for (MorfologickaDefiniciaSSJ morfologickeDefinicieSSJ : slovoSSJ.getDefinicie()) {
                List<VahaSlova> aktualnyZoznam = vypocitajVahuDefinicie(morfologickeDefinicieSSJ);
                if(aktualnyZoznam != null && !aktualnyZoznam.isEmpty()) {
                    vyslednyZoznam.add(aktualnyZoznam);
                }
            }
            List<VahaSlova> synonyma = vypocitajVahuVyznamovoTotoznychSlov(
                    ssjDao.najdiVsetkySynonymaSlova(slovoSSJ.getIdVyznam(), slovoSSJ.getId()));
            if(synonyma != null && !synonyma.isEmpty()) {
                vyslednyZoznam.add(synonyma);
            }
            List<VahaSlova> suvisiaceSlova = vypocitajVahuSkupinovoSuvisiacichSlov(
                    ssjDao.najdiVsetkySuvisiaceSlova(slovoSSJ.getIdVyznam(), slovoSSJ.getIdSkupina()));
            if(suvisiaceSlova != null && !suvisiaceSlova.isEmpty()) {
                vyslednyZoznam.add(suvisiaceSlova);
            }
        }
        return vyslednyZoznam;
    }
    
    
    private List<VahaSlova> vypocitajVahuDefinicie(MorfologickaDefiniciaSSJ morfologickeDefinicieSSJ) {
        List<VahaSlova> suvisiaceSlova = new ArrayList();
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getPodstatneSingular(), VAHA_PODSTATNE, "pod. m."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getPodstatnePlural(), VAHA_PODSTATNE, "pod. m."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getPodstatnePomnozne(), VAHA_PODSTATNE, "pod. m."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getPridavneSingular(), VAHA_PRIDAVNE, "prid. m."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getPridavnePlural(), VAHA_PRIDAVNE, "prid. m."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getCislovkaSingular(), VAHA_CISLOVKY, "cisl."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getCislovkaPlural(), VAHA_CISLOVKY, "cisl."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getZamenoSingular(), VAHA_ZAMENA, "zam."));
        suvisiaceSlova.addAll(vypocitajVahuSklonovatelnehoSlova(morfologickeDefinicieSSJ.getZamenoPlural(), VAHA_ZAMENA, "zam."));
        suvisiaceSlova.addAll(vypocitajVahuNesklonovatelnehoSlova(morfologickeDefinicieSSJ.getSlovesa(), VAHA_SLOVESA, "slov."));
        suvisiaceSlova.addAll(vypocitajVahuNesklonovatelnehoSlova(morfologickeDefinicieSSJ.getPrislovka(), VAHA_PRISLOVKY, "prisl."));
        return  suvisiaceSlova;
    }
    
    private List<VahaSlova> vypocitajVahuVyznamovoTotoznychSlov(List<SlovoSSJ> slova) {
        List<VahaSlova> vyznamovoTotozne = new ArrayList();
        for (SlovoSSJ slovoSSJ : slova) {
            vyznamovoTotozne.add(new VahaSlova(slovoSSJ.getSlovo(), VAHA_SYNONYMA, "vyznamovo totozne"));
        }
        return  vyznamovoTotozne;
    }
    
    private List<VahaSlova> vypocitajVahuSkupinovoSuvisiacichSlov(List<SlovoSSJ> slova) {
        List<VahaSlova> suvisiaceSlova = new ArrayList();
        for (SlovoSSJ slovoSSJ : slova) {
            suvisiaceSlova.add(new VahaSlova(slovoSSJ.getSlovo(), VAHA_SKUPINY, "suvisiace slova"));
        }
        return  suvisiaceSlova;
    }
    
    private List<VahaSlova> vypocitajVahuSklonovatelnehoSlova(SklonovatelneSlovo sklonovatelneSlovo, double koeficientSlovnehoDruhu, String slovnyDruh) {
        Map<String, List<Double>> mapa = new HashMap<>();
        double koeficientPadu = 1.0;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getNominativ(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        koeficientPadu = koeficientPadu * KOEFICIENT_PADU;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getAkuzativ(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        koeficientPadu = koeficientPadu * KOEFICIENT_PADU;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getGenitiv(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        koeficientPadu = koeficientPadu * KOEFICIENT_PADU;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getDativ(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        koeficientPadu = koeficientPadu * KOEFICIENT_PADU;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getLokal(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        koeficientPadu = koeficientPadu * KOEFICIENT_PADU;
        mapa = vypocitajVahuPadu(sklonovatelneSlovo.getInstrumental(), mapa, koeficientSlovnehoDruhu, koeficientPadu);
        return  vratZoznamOhodnotenychSlovZMapy(mapa, slovnyDruh);
    }
    
    
    private List<VahaSlova> vratZoznamOhodnotenychSlovZMapy(Map<String, List<Double>> mapa, String slovnyDruh) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        Iterator<Map.Entry<String, List<Double>>> it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<Double>> par = it.next();
            double sucetVah = 0.0;
            for (Double vaha : par.getValue()) {
                sucetVah += vaha;
            }
            vyslednyZoznam.add(new VahaSlova(par.getKey(), sucetVah / par.getValue().size(), slovnyDruh));
            // pridat sem slovny druh a cislo
            // vyslednyZoznam.add(new VahaSlova(par.getKey(), sucetVah / par.getValue().size(), ""));
        }
        return vyslednyZoznam;
    }
    
    private  Map<String, List<Double>> vypocitajVahuPadu(List<String> zoznamSlov, Map<String, List<Double>> mapa,
            double koeficientSlovnehoDruhu, double koeficientPadu) {
        for (String string : zoznamSlov) {
            if(mapa.containsKey(string)) {
                List<Double> hodnoty = mapa.get(string);
                hodnoty.add(koeficientSlovnehoDruhu * koeficientPadu);
                mapa.put(string, hodnoty);
            } else {
                List<Double> hodnoty =new ArrayList<>();
                hodnoty.add(koeficientSlovnehoDruhu * koeficientPadu);
                mapa.put(string, hodnoty);
            }
        }
        return mapa;
    }
    
    private List<VahaSlova> vypocitajVahuNesklonovatelnehoSlova(String slova, double  koeficientSlovnehoDruhu, String slovnyDruh) {
        List<VahaSlova> vyslednyZoznam = new ArrayList<>();
        Scanner citacSlov = new Scanner(slova);
        citacSlov.useDelimiter(";");
        while (citacSlov.hasNext()) {
            vyslednyZoznam.add(new VahaSlova(citacSlov.next(), koeficientSlovnehoDruhu, slovnyDruh));
            // pridat sem slovny druh
            // vyslednyZoznam.add(new VahaSlova(citacSlov.next(), koeficientSlovnehoDruhu, ""));
        }
        return  vyslednyZoznam;
    }
    
}
