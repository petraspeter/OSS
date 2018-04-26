package ics.upjs.sk.resource;

import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.service.SSJService;
import ics.upjs.sk.util.VahaSlova;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.ArrayList;
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
        System.out.println(slovo);
         List<List<VahaSlova>> slova = ssjService.vypocitajVahuSuvisiacichSlov(slovo);
         List<VahaSlova> vyslednyZoznam = new ArrayList<>();
         int pocitadlo = 0;
         for (List<VahaSlova> list : slova) {
             for (VahaSlova vahaSlova : list) {
                 vahaSlova.setIdZoznamu(pocitadlo);
                 vyslednyZoznam.add(vahaSlova);
             }
            pocitadlo++;
        }
        return Response.ok(vyslednyZoznam).build();
    }
        
}
