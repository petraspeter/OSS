package ics.upjs.sk.core.ssj;

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
@Table(name = "ssj_slovo")
public class SlovoSSJ {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "slovo")
    private String slovo;
    
    @Column(name = "id_skupina")
    private Long idSkupina;
    
    @Column(name = "id_vyznam")
    private Long idVyznam;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ssj_vazba", joinColumns = {
        @JoinColumn(name = "id_slovo") }, inverseJoinColumns = {
            @JoinColumn(name = "id_definicia") })    
    private Set<DefiniciaSSJ> definicie;
    
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

    public Long getIdVyznam() {
        return idVyznam;
    }

    public void setIdVyznam(Long idVyznam) {
        this.idVyznam = idVyznam;
    }
    
    public Set<DefiniciaSSJ> getDefinicie() {
        return definicie;
    }
    
    public void setDefinicie(Set<DefiniciaSSJ> definicie) {
        this.definicie = definicie;
    }
    
    public SlovoSSJ() {
    }
    
    public SlovoSSJ(String slovo) {
        this.slovo = slovo;
    }

    public SlovoSSJ(String slovo, Long idSkupina, Long idVyznam) {
        this.slovo = slovo;
        this.idSkupina = idSkupina;
        this.idVyznam = idVyznam;
    }
        
    public SlovoSSJ(Long id, String slovo) {
        this.id = id;
        this.slovo = slovo;
    }
    
    public SlovoSSJ(Long id, String slovo, Long idSkupina) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupina;
    }
    
    public SlovoSSJ(Long id, String slovo, Long idSkupina, Set<DefiniciaSSJ> definicie) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupina;
        this.definicie = definicie;
    }
    
}
