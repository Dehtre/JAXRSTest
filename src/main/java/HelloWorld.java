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
    @Produces(MediaType.APPLICATION_XML)
    public Something getOther(@PathParam("smth") int smth) {
        Something s = new Something();
        s.setNumber(smth);
        return s;
    }

    @GET
    @Path("{smth}")
    @Produces(MediaType.APPLICATION_XML)
    public Something getOther(@PathParam("smth") String smth) {
        Something s = new Something();
        s.setName(smth);
        return s;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createEntity(Something something) throws URISyntaxException {
        if (something == null){
            throw new BadRequestException();
        }

        return Response.created(context.getAbsolutePathBuilder().path(something.getName()).build()).build();
        }
}