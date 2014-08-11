import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Random;

/**
 * Created by Michał on 2014-08-08.
 */

@XmlRootElement
@XmlType(name = "Something", propOrder = {"name", "number"})
public class Something {
    private int number;
    private String name;

    public Something() {
        this(new Random().nextInt(10000), randomString());
    }

    public Something(int n, String s) {
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

    private static String randomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Something named " + name + ", number: " + number;
    }
}
