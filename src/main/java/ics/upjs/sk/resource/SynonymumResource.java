package ics.upjs.sk.resource;

import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.service.SynonymumService;
import ics.upjs.sk.util.VahaSlova;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Raven
 */
@Path("/oss/synonymum")
@Produces(MediaType.APPLICATION_JSON)
public class SynonymumResource {
    
    private final SynonymumService synonymumService;
    
    public SynonymumResource(SynonymumService synonymumService) {
        this.synonymumService = synonymumService;
    }
    
    @GET
    @Path("")
    @UnitOfWork
    public Response vratVsetkySynonyma() {
        List<SynonymumDto> synonyma = synonymumService.vratVsetkySynonyma();
        return Response.ok(synonyma).build();
    }
    
    @GET
    @Path("/id")
    @UnitOfWork
    public Response najdiPodlaId(
            @QueryParam("id") Long id
    ) {
        SynonymumDto synonymum = synonymumService.najdiPodlaId(id);
        return Response.ok(synonymum).build();
    }
    
    @GET
    @Path("/dominanta")
    @UnitOfWork
    public Response vratDominantu(
            @QueryParam("dominanta") String dominanta
    ) {
        List<SynonymumDto> synonyma = synonymumService.vratDominanty(dominanta);
        return Response.ok(synonyma).build();
    }
    
    @GET
    @Path("/synonymicky_rad")
    @UnitOfWork
    public Response vratSynoynmickyRad(
            @QueryParam("slovo") String slovo
    ) {
        List<VahaSlova> synonyma = synonymumService.vypocitajHodnotySynonym(slovo);
        return Response.ok(synonyma).build();
    }
    
}
