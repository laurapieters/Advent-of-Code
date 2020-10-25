import java.util.Comparator;

public class slopeComparator implements Comparator<RC>{
    @Override
    public int compare(RC rc1, RC rc2) {
        double slope1 = rc1.slope();
        double slope2 = rc2.slope();
        if(slope1 < slope2){
            return -1;
        }else if(slope1 == slope2){
            return 0;
        }else{
            return 1;
        }
    }
}
