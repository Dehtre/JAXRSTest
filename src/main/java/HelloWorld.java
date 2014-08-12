import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/")
public class HelloWorld {
    @Context
    private UriInfo context;
    private static Boolean initiated = false;
    private static Set<Something> things = new HashSet<Something>();

    public HelloWorld() {
        if(!initiated) {
            Something s;
            for(int i = 0; i<50; i++) {
                s = new Something(i%5);
                things.add(s);
                System.out.println("Added: " + s.toString() + " size: " + things.size());
            }
            initiated = true;
        }
    }

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello world!!</h1></body></html>";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        Response response;
        return null;
    }

    @GET
    @Path("{smth : [1-9][0-9]*}")
    @Produces(MediaType.APPLICATION_XML)
    public SomethingWrapper getOther(@PathParam("smth") final int smth) {
        List<Something> list = things.stream().filter(something -> something.getNumber() == smth).collect(Collectors.toList());
        return new SomethingWrapper(list);
        //return list.isEmpty() ? null : list.get(0);
    }

    @GET
    @Path("{smth}")
    @Produces(MediaType.APPLICATION_XML)
    public SomethingWrapper getOther(@PathParam("smth") String smth) {
        List<Something> list = things.stream().filter(something -> something.getName().equals(smth)).collect(Collectors.toList());
        return new SomethingWrapper(list);
        //return list.isEmpty() ? null : list.get(0);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createEntity(Something something) throws URISyntaxException {
        if (something == null){
            throw new BadRequestException();
        }

        things.add(something);
        System.out.println("Added: " + something.toString() + " size: " + things.size());

        return Response.created(context.getAbsolutePathBuilder().path(something.getName()).build()).build();
        }
}