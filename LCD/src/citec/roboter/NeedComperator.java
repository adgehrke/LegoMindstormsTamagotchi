package citec.roboter;

import java.util.Comparator;

public class NeedComperator implements Comparator<Need> {
    @Override
    public int compare(Need o1, Need o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
