package ics.upjs.sk.resource;

import ics.upjs.sk.dto.ScsDto;
import ics.upjs.sk.dto.SsjDto;
import ics.upjs.sk.dto.SynonymumDto;
import ics.upjs.sk.service.OssService;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Raven
 */
@Path("/oss")
@Produces(MediaType.APPLICATION_JSON)
public class OssResource {
    
    private final OssService ossService;
    
    public OssResource(OssService ossService) {
        this.ossService = ossService;
    }
    
    @GET
    @Path("/scs")
    @UnitOfWork
    public Response vratVsetkySlovaSCS() {
        List<ScsDto> slova = ossService.vratVsetkySlovaSCS();
        return Response.ok(slova).build();
    }
    
    @GET
    @Path("/ssj")
    @UnitOfWork
    public Response vratVsetkySlovaSSJ() {
        List<SsjDto> slova = ossService.vratVsetkySlovaSSJ();
        return Response.ok(slova).build();
    }
    
    
}
