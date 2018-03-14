package ics.upjs.sk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ics.upjs.sk.core.synonyma.Synonymum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raven
 */
public class SynonymumDto {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("slovo")
    private String slovo;
    
    @JsonProperty("definicia")
    private String definicia;
    
    @JsonProperty("synonyma")
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

    public SynonymumDto(Long id, String slovo, String definicia, String synonyma) {
        this.id = id;
        this.slovo = slovo;
        this.definicia = definicia;
        this.synonyma = synonyma;
    }

    public SynonymumDto() {
    }
    
    public List<SynonymumDto> vratDTOSynonym(List<Synonymum> synonyma) {
        List<SynonymumDto> synonymaDto = new ArrayList<>();
        for (Synonymum synonymum : synonyma) {
            synonymaDto.add(vytvorSynonymumDtoZoSynonyma(synonymum));
        }
        return synonymaDto;
    }
    
    public SynonymumDto vytvorSynonymumDtoZoSynonyma(Synonymum synonymum) {
        return new SynonymumDto(
                synonymum.getId(),
                synonymum.getSlovo(),
                synonymum.getDefinicia(),
                synonymum.getSynonyma()
        );
    }
    
}
