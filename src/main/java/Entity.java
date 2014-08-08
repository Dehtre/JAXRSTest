import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Michał on 2014-08-08.
 */

@XmlType(name = "Entity")
public class Entity {
    private int number;
    private String name;

    public Entity(int n, String s) {
        this.name = s;
        this.number = n;
    }

    @XmlElement(name = "number")
    public int getNumber() {
        return number;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
