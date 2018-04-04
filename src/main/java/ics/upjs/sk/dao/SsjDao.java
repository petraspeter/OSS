package ics.upjs.sk.dao;

import ics.upjs.sk.core.ssj.SlovoSSJ;
import io.dropwizard.hibernate.AbstractDAO;
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
        return currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("id_vyznam", idVyznam))
                .add(Restrictions.not(Restrictions.eq("id", idSlova)))
                .list();
    }
    
    public List<SlovoSSJ> najdiVsetkySuvisiaceSlova(Long idVyznam, Long idSlova, Long idSkupina) {
        return currentSession().createCriteria(SlovoSSJ.class)
                .add(Restrictions.eq("id_skupina", idSkupina))
                .add(Restrictions.not(Restrictions.eq("id_vyznam", idVyznam)))
                .add(Restrictions.not(Restrictions.eq("id", idSlova)))
                .list();
    }
        
}