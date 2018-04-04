package ics.upjs.sk.core.ssj;

import ics.upjs.sk.util.FlexnyKonvertor;
import ics.upjs.sk.util.SklonovatelneSlovo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "ssj_morfologicka_definicia")
public class MorfologickaDefiniciaSSJ implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "terminy")
    private String terminy;
    
    @Column(name = "sloveso")
    private String slovesa;
    
    @Column(name = "podstatne_singular")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo podstatneSingular;
    
    @Column(name = "podstatne_plural")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo podstatnePlural;
    
    @Column(name = "podstatne_pomnozne")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo podstatnePomnozne;
    
    @Column(name = "pridavne_singular")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo pridavneSingular;
    
    @Column(name = "pridavne_plural")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo pridavnePlural;
    
    @Column(name = "zameno_singular")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo zamenoSingular;
    
    @Column(name = "zameno_plural")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo zamenoPlural;
    
    @Column(name = "cislovka_singular")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo cislovkaSingular;
    
    @Column(name = "cislovka_plural")
    @Convert(converter = FlexnyKonvertor.class)
    private SklonovatelneSlovo cislovkaPlural;
    
    @Column(name = "prislovka")
    private String prislovka;
    
    @Override
    public String toString() {
        return "MorfologickeDefinicieSSJ{" + "id=" + id + ", terminy=" + terminy + ", slovesa=" + slovesa
                + ", podstatneSingular=" + podstatneSingular.toString() + ", podstatnePlural=" + podstatnePlural.toString()
                + ", podstatnePomnozne=" + podstatnePomnozne.toString() + ", pridavneSingular="
                + pridavneSingular.toString() + ", pridavnePlural=" + pridavnePlural.toString() + ", zamenoSingular="
                + zamenoSingular.toString() + ", zamenoPlural=" + zamenoPlural.toString() + ", cislovkaSingular="
                + cislovkaSingular.toString() + ", cislovkaPlural=" + cislovkaPlural.toString() + ", prislovka=" + prislovka + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminy() {
        return terminy;
    }

    public void setTerminy(String terminy) {
        this.terminy = terminy;
    }

    public String getSlovesa() {
        return slovesa;
    }

    public void setSlovesa(String slovesa) {
        this.slovesa = slovesa;
    }

    public SklonovatelneSlovo getPodstatneSingular() {
        return podstatneSingular;
    }

    public void setPodstatneSingular(SklonovatelneSlovo podstatneSingular) {
        this.podstatneSingular = podstatneSingular;
    }

    public SklonovatelneSlovo getPodstatnePlural() {
        return podstatnePlural;
    }

    public void setPodstatnePlural(SklonovatelneSlovo podstatnePlural) {
        this.podstatnePlural = podstatnePlural;
    }

    public SklonovatelneSlovo getPodstatnePomnozne() {
        return podstatnePomnozne;
    }

    public void setPodstatnePomnozne(SklonovatelneSlovo podstatnePomnozne) {
        this.podstatnePomnozne = podstatnePomnozne;
    }

    public SklonovatelneSlovo getPridavneSingular() {
        return pridavneSingular;
    }

    public void setPridavneSingular(SklonovatelneSlovo pridavneSingular) {
        this.pridavneSingular = pridavneSingular;
    }

    public SklonovatelneSlovo getPridavnePlural() {
        return pridavnePlural;
    }

    public void setPridavnePlural(SklonovatelneSlovo pridavnePlural) {
        this.pridavnePlural = pridavnePlural;
    }

    public SklonovatelneSlovo getZamenoSingular() {
        return zamenoSingular;
    }

    public void setZamenoSingular(SklonovatelneSlovo zamenoSingular) {
        this.zamenoSingular = zamenoSingular;
    }

    public SklonovatelneSlovo getZamenoPlural() {
        return zamenoPlural;
    }

    public void setZamenoPlural(SklonovatelneSlovo zamenoPlural) {
        this.zamenoPlural = zamenoPlural;
    }

    public SklonovatelneSlovo getCislovkaSingular() {
        return cislovkaSingular;
    }

    public void setCislovkaSingular(SklonovatelneSlovo cislovkaSingular) {
        this.cislovkaSingular = cislovkaSingular;
    }

    public SklonovatelneSlovo getCislovkaPlural() {
        return cislovkaPlural;
    }

    public void setCislovkaPlural(SklonovatelneSlovo cislovkaPlural) {
        this.cislovkaPlural = cislovkaPlural;
    }

    public String getPrislovka() {
        return prislovka;
    }

    public void setPrislovka(String prislovka) {
        this.prislovka = prislovka;
    }
    
}
