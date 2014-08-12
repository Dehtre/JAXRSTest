
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Michał on 2014-08-08.
 */
public class HelloClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder().build();
        final WebTarget target = client.target("http://localhost:8080/JAXRSTest/app/");
        Response response;

        //Posts
        List<Something> postList = IntStream.rangeClosed(1, 10).mapToObj(value -> new Something("abc")).collect(Collectors.toList());
        postList.forEach(s -> {System.out.println("Response status " + ClientBuilder.newBuilder().build().target("http://localhost:8080/JAXRSTest/app/").request().post(Entity.entity(s, MediaType.APPLICATION_XML)).getStatus() + " for " + s.toString());});
        /*Something en = new Something(99, "abc");
        response = target.request().post(Entity.entity(en, MediaType.APPLICATION_XML));
        if(response.getStatus() == 201)
            System.out.println("Response status - Created: " + response.getLocation());
        else
            System.out.println("Some error in post: " + response.getLocation());*/
        System.out.println("");

        //Get number
        int number = 1;
        client = ClientBuilder.newBuilder().build();
        response = client.target("http://localhost:8080/JAXRSTest/app/" + number).request().get();
        SomethingWrapper sw = response.readEntity(SomethingWrapper.class);
        System.out.println("Getting somethings with number = " + number + ":");
        sw.getAll().forEach(something -> System.out.println(something.toString()));
        System.out.println("");

        //Get name
        String name = "abc";
        response = client.target("http://localhost:8080/JAXRSTest/app/" + name).request().get();
        System.out.println("Getting somethings with name = " + name + ":");
        if(response.hasEntity()) {
            sw = response.readEntity(SomethingWrapper.class);
            sw.getAll().forEach(something -> System.out.println(something.toString()));
        } else
            System.out.println("Something named " + name + " not found.");
        System.out.println("");

        response.close();
    }
}
