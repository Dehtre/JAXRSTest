import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by Michał on 2014-08-08.
 */
public class HelloClient {

    public static void main(String[] args) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/JAXRSTest/app/");

        //Post
        /*Something en = new Something(99, "abc");
        Response response = target.request().post(Entity.entity(en, "application/vnd.com.demo.user-management.user+xml;charset=UTF-8;version=1"));
        System.out.println(response.getStatus());
        response.close();*/

        //Get number
        int number = 12345;
        target = client.target("http://localhost:8080/JAXRSTest/app/" + number);
        Response response = target.request().get();
        System.out.println(response.readEntity(Something.class).toString());

        //Get name
        String name = "abc12345";
        target = client.target("http://localhost:8080/JAXRSTest/app/" + name);
        response = target.request().get();
        System.out.println(response.readEntity(Something.class).toString());
    }
}
