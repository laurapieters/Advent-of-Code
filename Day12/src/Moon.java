public class Moon {
    public int xLoc;
    public int yLoc;
    public int zLoc;
    public int xVel;
    public int yVel;
    public int zVel;

    // constructor
    public Moon(int xInitial, int yInitial, int zInitial, int xVelInitial, int yVelInitial, int zVelInitial){
        this.xLoc = xInitial;
        this.yLoc = yInitial;
        this.zLoc = zInitial;
        this.xVel = xVelInitial;
        this.yVel = yVelInitial;
        this.zVel = zVelInitial;
    }

    int potentialEnergy(){
        return Math.abs(xLoc) + Math.abs(yLoc) + Math.abs(zLoc);
    }

    int kineticEnergy(){
        return Math.abs(xVel) + Math.abs(yVel) + Math.abs(zVel);
    }

    @Override
    public String toString() {
        return "pos = <x="+ xLoc +
                ", y=" + yLoc +
                ", z="+ zLoc +
                ">, vel = <x="+xVel+
                ", y="+ yVel +
                ", z="+zVel+
                ">";
    }

}
