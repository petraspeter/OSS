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
    
    public List<Synonymum> findAll() {
        return list(currentSession().createCriteria(Synonymum.class));
    }
    
    public Synonymum najdiPodlaId(Long id) {
        return uniqueResult(currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.eq("id", id))
        );
    }
    
    public List<Synonymum> najdiDominanty(String dominanta) {
        return list(currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.eq("slovo", dominanta))
                .add(Restrictions.isNotNull("definicia"))
        );
    }
    
    public List<Synonymum> najdiClenovRaduPodlaSlova(String clen) {
        List<Synonymum> clenovia = list(currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.eq("slovo", clen))
                .add(Restrictions.isNull("definicia"))
        );
        clen = "%;" + clen + ";%";
        System.out.println(clen);
        clenovia.addAll(list(currentSession().createCriteria(Synonymum.class)
                .add(Restrictions.like("synonyma", clen))
                .add(Restrictions.isNotNull("definicia"))
        ));       
        List<Synonymum> vysledok = new ArrayList<>();
        for (Synonymum synonymum : clenovia) {
            vysledok.addAll(najdiDominanty(synonymum.getSynonyma()));
        }
        
        return vysledok;
    }

    
    
}
