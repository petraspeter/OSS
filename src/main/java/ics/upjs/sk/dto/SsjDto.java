package ics.upjs.sk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ics.upjs.sk.core.ssj.DefiniciaSSJ;
import ics.upjs.sk.core.ssj.SlovoSSJ;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Raven
 */
public class SsjDto {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("slovo")
    private String slovo;
    
    @JsonProperty("skupina")
    private Long idSkupina;
    
    @JsonProperty("definicie")
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
    
    public Set<DefiniciaSSJ> getDefinicie() {
        return definicie;
    }
    
    public void setDefinicie(Set<DefiniciaSSJ> definicie) {
        this.definicie = definicie;
    }
    
    public List<SsjDto> vratDTOSlovaSSJ(List<SlovoSSJ> zoznamSlov) {
        List<SsjDto> dtoSlova = new ArrayList<>();
        for (SlovoSSJ slovo : zoznamSlov) {
            dtoSlova.add(vytvorDtoSsjZoSlovaSsj(slovo));
        }
        return dtoSlova;
    }
    
    private SsjDto vytvorDtoSsjZoSlovaSsj(SlovoSSJ slovo) {
        return new SsjDto(
                slovo.getId(),
                slovo.getSlovo(),
                slovo.getIdSkupina(),
                slovo.getDefinicie()
        );
    }
    
    public SsjDto(Long id, String slovo, Long idSkupina, Set<DefiniciaSSJ> definicie) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupina;
        this.definicie = definicie;
    }
    
    public SsjDto() {
    }
    
    
}
