package ics.upjs.sk.core.scs;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Raven
 */
@Entity
@Table(name = "scs_slovo")
public class SlovoSCS implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "slovo")
    private String slovo;
    
    @Column(name = "id_skupina")
    private Long idSkupina;    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "scs_vazba", joinColumns = {
        @JoinColumn(name = "id_slovo") }, inverseJoinColumns = {
            @JoinColumn(name = "id_definicia") })
    private Set<DefiniciaSCS> definicie;
    
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

    public Long getIdSkupina() {
        return idSkupina;
    }

    public void setIdSkupina(Long idSkupina) {
        this.idSkupina = idSkupina;
    }
    
    public Set<DefiniciaSCS> getDefinicie() {
        return definicie;
    }
    
    public void setDefinicie(Set<DefiniciaSCS> definicie) {
        this.definicie = definicie;
    }
    
    public SlovoSCS() {
    }
    
    public SlovoSCS(String slovo) {
        this.slovo = slovo;
    }
    
    public SlovoSCS(Long id, String slovo) {
        this.id = id;
        this.slovo = slovo;
    }

    public SlovoSCS(Long id, String slovo, Long idSkupiny) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupiny;
    }

    public SlovoSCS(Long id, String slovo, Long idSkupiny, Set<DefiniciaSCS> definicie) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupiny;
        this.definicie = definicie;
    }

    @Override
    public String toString() {
        return "SlovoSCS{" + "id=" + id + ", slovo=" + slovo + ", idSkupina=" + idSkupina + '}';
    }
     
}
