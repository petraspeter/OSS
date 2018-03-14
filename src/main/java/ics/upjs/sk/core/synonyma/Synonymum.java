package ics.upjs.sk.core.synonyma;


import java.io.Serializable;
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
@Table(name = "synonyma")
public class Synonymum implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "slovo")
    private String slovo;
    
    @Column(name = "definicia")
    private String definicia;
    
    @Column(name = "synonyma")
    private String synonyma;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSlovo() {
        return slovo;
    }
    
    public void setSlovo(String slovo) {
        this.slovo = slovo;
    }
    
    public String getDefinicia() {
        return definicia;
    }
    
    public void setDefinicia(String definicia) {
        this.definicia = definicia;
    }
    
    public String getSynonyma() {
        return synonyma;
    }
    
    public void setSynonyma(String synonyma) {
        this.synonyma = synonyma;
    }
    
    public Synonymum() {
    }
    
    public Synonymum(String slovo) {
        this.slovo = slovo;
    }
    
    @Override
    public String toString() {
        return "Slovo{" + "id=" + id + ", slovo=" + slovo + ", definicia=" + definicia + ", klucoveSlova=" + synonyma +  '}';
    }
    
}
