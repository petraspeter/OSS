package ics.upjs.sk.dao;

import ics.upjs.sk.core.ssj.SlovoSSJ;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

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
    
    
}