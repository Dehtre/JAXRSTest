import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URISyntaxException;

@Path("/")
public class HelloWorld {
    @Context
    private UriInfo context;

    public HelloWorld() {
    }

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello world!!</h1></body></html>";
    }

    @GET
    @Path("{smth : [1-9][0-9]*}")
    //@Produces(MediaType.APPLICATION_XML)
    public Response getOther(@PathParam("smth") int smth) {
        return Response.status(200).entity("Hello world2! App: " + smth).build();
    }

    @GET
    @Path("{smth}")
    //@Produces(MediaType.APPLICATION_XML)
    public Response getOther(@PathParam("smth") String smth) {
        // Load from db entity.name = smth
        return Response.status(200).entity("Hello world! App: " + smth).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createEntity(Entity entity) throws URISyntaxException {
        if (entity == null){
            throw new BadRequestException();
        }

        // Add to database

        return Response.created(context.getAbsolutePathBuilder().path(entity.getName()).build()).build();
        }
}