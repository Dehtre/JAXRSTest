
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Michał on 2014-08-08.
 */
public class HelloClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8080/JAXRSTest/app/");

        //Post
        Something en = new Something(99, "abc");
        Response response = target.request().post(Entity.entity(en, MediaType.APPLICATION_XML));
        if(response.getStatus() == 201)
            System.out.println("Response status - Created: " + response.getLocation());
        else
            System.out.println("Some error in post: " + response.getLocation());

        //Get number
        int number = 1;
        client = ClientBuilder.newBuilder().build();
        target = client.target("http://localhost:8080/JAXRSTest/app/" + number);
        response = target.request().get();
        SomethingWrapper sw = response.readEntity(SomethingWrapper.class);
        sw.getAll().forEach(something -> System.out.println(something.toString()));

        //Get name
        String name = "abc";
        target = client.target("http://localhost:8080/JAXRSTest/app/" + name);
        response = target.request().get();
        if(response.hasEntity()) {
            sw = response.readEntity(SomethingWrapper.class);
            sw.getAll().forEach(something -> System.out.println(something.toString()));
        } else
            System.out.println("Something named " + name + " not found.");


        response.close();
    }
}
