import java.util.Comparator;

public class RC{
    // this dingen
    public int x;
    public int y;

    public RC(int initialX, int initialY){
        this.x = initialX;
        this.y = initialY;
    }

    public double slope(){
        return y/ (double)x;
    }

    @Override
    public String toString() {
        return "RC{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
