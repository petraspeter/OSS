package ics.upjs.sk.dao;

import ics.upjs.sk.core.synonyma.Synonymum;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Raven
 */
public class SynonymumDao extends AbstractDAO<Synonymum> {
    
    public SynonymumDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Synonymum createOrUpdate(Synonymum synonymum) {
        return this.persist(synonymum);
    }
    
    public Synonymum najdiPodlaId(Long id) {
        return uniqueResult(currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.eq("id", id))
        );
    }
    
    public List<Synonymum> najdiDominanty(String dominanta) {
        List<Synonymum> dominanty = new ArrayList<>();
        List<Synonymum> docasnyZoznam = currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.like("slovo", dominanta))
                .list();
        for (Synonymum synonymum : docasnyZoznam) {
            if(synonymum.getSynonyma().contains(";")) dominanty.add(synonymum);
        }
        return dominanty;
    }
    
    public List<Synonymum> najdiClenovRadu(String slovo) {
        slovo = "%" + slovo + ";%";
        return currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.like("synonyma", slovo))
                .list();
    }
    
}
