import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Michał on 2014-08-08.
 */
public class HelloClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/JAXRSTest/app/");
        Invocation invocation = target.request().buildGet();
        Response response = invocation.invoke();

        //Add new
        Entity en = new Entity(10, "b");
        Response res1 = target.request().buildPost(javax.ws.rs.client.Entity.entity(en, MediaType.APPLICATION_XML)).invoke();

        //Request
        String entityName = "a";
        WebTarget t2 = client.target("http://localhost:8080/JAXRSTest/app" + entityName);
        Response res = target.request().buildGet().invoke();
    }
}
