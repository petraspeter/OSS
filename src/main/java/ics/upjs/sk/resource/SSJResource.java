package ics.upjs.sk.resource;

import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.service.SSJService;
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
@Path("/oss/ssj")
@Produces(MediaType.APPLICATION_JSON)
public class SSJResource {

    private final SSJService ssjService;

    public SSJResource(SSJService ssjService) {
        this.ssjService = ssjService;
    }
    
    @GET
    @Path("/slovo")
    @UnitOfWork
    public Response vratClenaRadu(
            @QueryParam("slovo") String slovo
    ) {
         List<List<VahaSlova>> slova = ssjService.vypocitajVahuSuvisiacichSlov(slovo);
        return Response.ok(slova).build();
    }
    
    
    
}
