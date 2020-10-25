import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12Exercise {

    public static void main(String []args) throws FileNotFoundException {
        File file = new File("inputDay12.txt");
        Scanner sc = new Scanner(file);

        Moon[] moons = new Moon[4];
        // reading initial data from file
        for(int m = 0; m < moons.length; m++){
            String line = sc.nextLine();
            String xString = line.split("=")[1];
            String yString = line.split("=")[2];
            String zString = line.split("=")[3];
            int x = Integer.parseInt(xString.substring(0,xString.indexOf(",")));
            int y = Integer.parseInt(yString.substring(0,yString.indexOf(",")));
            int z = Integer.parseInt(zString.substring(0,zString.indexOf(">")));
            moons[m] = new Moon(x,y,z,0,0,0);
        }

//        System.out.println("After "+0+" steps:");
//        System.out.println(moons[0]);
//        System.out.println(moons[1]);
//        System.out.println(moons[2]);
//        System.out.println(moons[3]);
//        for(int t = 1; t <= 20; t++){
//            moons = gravity(moons);
//            moons = velocity(moons);
//            System.out.println("After "+t+" steps:");
//            System.out.println(moons[0]);
//            System.out.println(moons[1]);
//            System.out.println(moons[2]);
//            System.out.println(moons[3]);
//        }
//        int totalEnergy = 0;
//        for(int m = 0; m < moons.length; m++){
//            totalEnergy += moons[m].potentialEnergy()*moons[m].kineticEnergy();
//        }
//        System.out.println("total energy:"+totalEnergy);

        int[][] initialState = new int[3][4];
        for(int m = 0; m < moons.length; m++){
            initialState[0][m] = moons[m].xLoc;
            initialState[1][m] = moons[m].yLoc;
            initialState[2][m] = moons[m].zLoc;
        }
        boolean xDuplicateFound = false;
        int xSteps = 0;
        while(xDuplicateFound == false){
            moons = xGravity(moons);
            moons = xVelocity(moons);
            if(moons[0].xLoc == initialState[0][0] && moons[0].xVel == 0 &&
               moons[1].xLoc == initialState[0][1] && moons[1].xVel == 0 &&
               moons[2].xLoc == initialState[0][2] && moons[2].xVel == 0 &&
               moons[3].xLoc == initialState[0][3] && moons[3].xVel == 0){
                xDuplicateFound = true;
            }
            xSteps++;
        }

        boolean yDuplicateFound = false;
        int ySteps = 0;
        while(yDuplicateFound == false){
            moons = yGravity(moons);
            moons = yVelocity(moons);
            if(moons[0].yLoc == initialState[1][0] && moons[0].yVel == 0 &&
               moons[1].yLoc == initialState[1][1] && moons[1].yVel == 0 &&
               moons[2].yLoc == initialState[1][2] && moons[2].yVel == 0 &&
               moons[3].yLoc == initialState[1][3] && moons[3].yVel == 0){
                yDuplicateFound = true;
            }
            ySteps++;
        }

        boolean zDuplicateFound = false;
        int zSteps = 0;
        while(zDuplicateFound == false){
            moons = zGravity(moons);
            moons = zVelocity(moons);
            if(moons[0].zLoc == initialState[2][0] && moons[0].zVel == 0 &&
               moons[1].zLoc == initialState[2][1] && moons[1].zVel == 0 &&
               moons[2].zLoc == initialState[2][2] && moons[2].zVel == 0 &&
               moons[3].zLoc == initialState[2][3] && moons[3].zVel == 0){
                zDuplicateFound = true;
            }
            zSteps++;
        }

        System.out.println(xSteps+" "+ySteps+" "+zSteps);
        System.out.println(lcm(lcm(xSteps,ySteps),zSteps));

    }

    public static Moon[] xGravity(Moon[] moons){
        for(int i = 0; i < moons.length; i++){
            for(int j = 0; j < moons.length; j++){
                // updating x velocity
                if(moons[i].xLoc < moons[j].xLoc){
                    moons[i].xVel += 1;
                }else if(moons[i].xLoc > moons[j].xLoc){
                    moons[i].xVel -= 1;
                }
            }
        }
        return moons;
    }

    public static Moon[] yGravity(Moon[] moons){
        for(int i = 0; i < moons.length; i++){
            for(int j = 0; j < moons.length; j++){
                // updating y velocity
                if(moons[i].yLoc < moons[j].yLoc){
                    moons[i].yVel += 1;
                }else if(moons[i].yLoc > moons[j].yLoc){
                    moons[i].yVel -= 1;
                }
            }
        }
        return moons;
    }

    public static Moon[] zGravity(Moon[] moons){
        for(int i = 0; i < moons.length; i++){
            for(int j = 0; j < moons.length; j++){
                // updating z velocity
                if(moons[i].zLoc < moons[j].zLoc){
                    moons[i].zVel += 1;
                }else if(moons[i].zLoc > moons[j].zLoc){
                    moons[i].zVel -= 1;
                }
            }
        }
        return moons;
    }

    public static Moon[] xVelocity(Moon[] moons){
        for(int m = 0; m < moons.length; m++){
            moons[m].xLoc += moons[m].xVel;
        }
        return moons;
    }

    public static Moon[] yVelocity(Moon[] moons){
        for(int m = 0; m < moons.length; m++){
            moons[m].yLoc += moons[m].yVel;
        }
        return moons;
    }

    public static Moon[] zVelocity(Moon[] moons){
        for(int m = 0; m < moons.length; m++){
            moons[m].zLoc += moons[m].zVel;
        }
        return moons;
    }

    // Recursive method to return gcd of a and b
    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
//
//    public static int lcm(int... args){
//        int r = args[0];
//        int i = 0;
//        while(i < args.length - 1)
//            r = lcm(r,args[++i]);
//        return r;
//    }
}
