package ics.upjs.sk.dao;

import ics.upjs.sk.core.scs.SlovoSCS;
import ics.upjs.sk.core.scs.SlovoSCS;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Raven
 */
public class ScsDao extends AbstractDAO<SlovoSCS> {
    
    private final SessionFactory sessionFactory;
    
    public ScsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
    
    public SlovoSCS createOrUpdate(SlovoSCS slovoSCS) {
        return this.persist(slovoSCS);
    }
    
    public List<SlovoSCS> findAll() {
        return list(currentSession().createCriteria(SlovoSCS.class));
    }
    
    public SlovoSCS najdiPodlaExaktnehoNazvuSlova(String name) {
        List<SlovoSCS> podobneSlova = najdiPodlaNazvuSlova(name);
        for (SlovoSCS slovoSCS : podobneSlova) {
            if (slovoSCS.getSlovo().equals(name)) {
                return slovoSCS;
            }
        }
        return null;
    }
    
    public List<SlovoSCS> najdiPodlaNazvuSlova(String name) {
        return list(currentSession().createCriteria(SlovoSCS.class).add(Restrictions.eq("slovo", name)));
    }
    
}
