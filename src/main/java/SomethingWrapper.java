import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Michał on 2014-08-11.
 */

@XmlRootElement
public class SomethingWrapper {
    @XmlElement
    private List<Something> things;

    public SomethingWrapper() {

    }

    public SomethingWrapper(List<Something> list) {
        things = list;
    }

    public void setThings(List<Something> things) {
        this.things = things;
    }

    public List<Something> getAll() {
        return things;
    }
}
