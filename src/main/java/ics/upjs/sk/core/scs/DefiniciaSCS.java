package ics.upjs.sk.core.scs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Raven
 */
@Entity
@Table(name = "scs_definicia")
public class DefiniciaSCS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "definicia")
    private String definicia;
    
    @Column(name = "terminy")
    private String terminy;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDefinicia() {
        return definicia;
    }
    
    public void setDefinicia(String definicia) {
        this.definicia = definicia;
    }
    
    public String getTerminy() {
        return terminy;
    }
    
    public void setTerminy(String terminy) {
        this.terminy = terminy;
    }
    
    public DefiniciaSCS() {
    }
    
    public DefiniciaSCS(String definicia) {
        this.definicia = definicia;
    }
    
    public DefiniciaSCS(Long id, String definicia) {
        this.id = id;
        this.definicia = definicia;
    }
    
    public DefiniciaSCS(String definicia, String terminy) {
        this.definicia = definicia;
        this.terminy = terminy;
    }
    
    public DefiniciaSCS(Long id, String definicia, String terminy) {
        this.id = id;
        this.definicia = definicia;
        this.terminy = terminy;
    }
    
}
