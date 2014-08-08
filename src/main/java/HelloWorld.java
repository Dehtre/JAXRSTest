import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    @Path("{smth}")
    public Response getOther(@PathParam("smth") String smth) {
        return Response.status(200).entity("Hello world! App: " + smth).build();
    }
}