
import java.util.Comparator;

public class Comp implements Comparator<data> {
 
    @Override
    public int compare(data data1, data data2) {
        return new Double(data1.weight).compareTo( new Double(data2.weight));
    }
}