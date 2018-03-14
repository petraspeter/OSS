package ics.upjs.sk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ics.upjs.sk.core.scs.DefiniciaSCS;
import ics.upjs.sk.core.scs.SlovoSCS;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Raven
 */
public class ScsDto {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("slovo")
    private String slovo;
    
    @JsonProperty("skupina")
    private Long idSkupina;
    
    @JsonProperty("definicie")
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
    
    public List<ScsDto> vratDTOSlovaSCS(List<SlovoSCS> zoznamSlov) {
        List<ScsDto> dtoSlova = new ArrayList<>();
        for (SlovoSCS slovoSCS : zoznamSlov) {
            dtoSlova.add(vytvorDtoScsZoSlovaScs(slovoSCS));
        }
        return dtoSlova;
    }
    
    private ScsDto vytvorDtoScsZoSlovaScs(SlovoSCS slovo) {
        return new ScsDto(
                slovo.getId(),
                slovo.getSlovo(),
                slovo.getIdSkupina(),
                slovo.getDefinicie()
        );
    }
    
    public ScsDto(Long id, String slovo, Long idSkupina, Set<DefiniciaSCS> definicie) {
        this.id = id;
        this.slovo = slovo;
        this.idSkupina = idSkupina;
        this.definicie = definicie;
    }

    public ScsDto() {
    }
    
}
