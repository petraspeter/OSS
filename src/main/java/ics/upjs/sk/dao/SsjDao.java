package ics.upjs.sk.dao;

import ics.upjs.sk.core.ssj.SlovoSSJ;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Raven
 */
public class SsjDao  extends AbstractDAO<SlovoSSJ> {
    
    public SsjDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public SlovoSSJ createOrUpdate(SlovoSSJ slovoSSJ) {
        return this.persist(slovoSSJ);
    }
    
    public List<SlovoSSJ> findAll() {
        return list(currentSession().createCriteria(SlovoSSJ.class));
    }
    
    public List<SlovoSSJ> najdiSlovoPodlaId(Long id) {
        return currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("id", id))
                .list();
    }
    
    public List<SlovoSSJ> najdiSlovo(String slovo) {
        return currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("slovo", slovo))
                .list();
    }
    
    public List<SlovoSSJ> najdiVsetkySynonymaSlova(Long idVyznam, Long idSlova) {
        List<SlovoSSJ> slova = currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("idVyznam", idVyznam)).list();
        List<SlovoSSJ> vysledneSlova = new ArrayList<>();
        for (SlovoSSJ slovoSSJ : slova) {
            if(!slovoSSJ.getId().equals(idSlova)) {
                vysledneSlova.add(slovoSSJ);
            }
        }
        return vysledneSlova;
    }
    
    public List<SlovoSSJ> najdiVsetkySuvisiaceSlova(Long idVyznam, Long idSkupina) {
        List<SlovoSSJ> slova = currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("idSkupina", idSkupina)).list();
        List<SlovoSSJ> vysledneSlova = new ArrayList<>();
        for (SlovoSSJ slovoSSJ : slova) {
            if(!slovoSSJ.getIdVyznam().equals(idVyznam)) {
                vysledneSlova.add(slovoSSJ);
            }
        }
        return vysledneSlova;
    }
    
}