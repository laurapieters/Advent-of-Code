import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day10Exercise {

    public static void main(String []args) throws FileNotFoundException {

        // read input
        File file = new File("inputDay10.txt");
        Scanner sc = new Scanner(file);
        int width = 34;
        int height = 34;
        char[][] input = new char[width][height];
        int nrAsteroids = 0;
        for(int j = 0; j < height; j++){
            String inputLine = sc.next();
            for(int i = 0; i < width; i++){
                input[i][j] = inputLine.charAt(i);
                if(inputLine.charAt(i) == '#'){
                    nrAsteroids++;
                }
            }
        }

        // asteroid locations
        int[][] asteroidLocations = new int[nrAsteroids][2];
        int asteroidCounter = 0;
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++) {
                if(input[i][j] == '#'){
                    asteroidLocations[asteroidCounter][0] = i;
                    asteroidLocations[asteroidCounter][1] = j;
                    asteroidCounter++;
                }
            }
        }

        ArrayList<RC> RCs = generateRC(width, height);
        int[] asteroidsInView = new int[nrAsteroids];
        for(int a = 0; a < nrAsteroids; a++) {
            // loop over RCs
            for (int i = 0; i < RCs.size(); i++){
                int newi = asteroidLocations[a][0];
                int newj = asteroidLocations[a][1];
                    boolean asteroidFound = false;
                    // look for asteroids while not blocked and not over the edge of input
                    while (asteroidFound == false && newi+RCs.get(i).x >= 0 && newi+RCs.get(i).x < width && newj+RCs.get(i).y >= 0 && newj+RCs.get(i).y< height) {
                        // update location in viewline
                        newi += RCs.get(i).x;
                        newj += RCs.get(i).y;
                        // asteroid found!
                        if (input[newi][newj] == '#') {
                            asteroidsInView[a]++; // adding found asteroid to counter
                            asteroidFound = true; // blocks further asteroids
                        }
                    }
            }
        }

        int maxAsteroids = 0;
        int maxAsteroidsLoc = 0;
        for(int a = 0; a<nrAsteroids; a++){
            if(asteroidsInView[a] >= maxAsteroids){
                maxAsteroids = asteroidsInView[a];
                maxAsteroidsLoc = a;
            }
        }
//        System.out.println(maxAsteroids);
//        System.out.println(RCs);

        boolean stop = false;
//        System.out.println(i+" "+j);
        int vaporizedCounter = 0;
        for (int k = 0; k < RCs.size() && !stop; k++){
            int i = asteroidLocations[maxAsteroidsLoc][0];
            int j = asteroidLocations[maxAsteroidsLoc][1];
            boolean asteroidFound = false;
            // look for asteroids while not blocked and not over the edge of input
            while (asteroidFound == false && i+RCs.get(k).x >= 0 && i+RCs.get(k).x < width && j+RCs.get(k).y >= 0 && j+RCs.get(k).y< height) {
                // update location in viewline
                i += RCs.get(k).x;
                j += RCs.get(k).y;
//                System.out.println(i+" "+j);
                // asteroid found!
                if (input[i][j] == '#') {
                    asteroidsInView[maxAsteroidsLoc]++; // adding found asteroid to counter
                    input[i][j] = '.'; // vaporizing asteroid
                    vaporizedCounter++;
                    if(vaporizedCounter == 200){
//                        System.out.println(RCs.get(k).x+" "+RCs.get(k).y+" "+RCs.get(k).slope()+" "+i+" "+j);
                        System.out.println(i*100+j);
                        stop = true;
                        break;
                    }
                    asteroidFound = true; // blocks further asteroids
                }
            }
            // resetting loop
            if(k == RCs.size()-1){
                k = 0;
            }
        }

    }

    static ArrayList<RC> generateRC(int width, int height){
        ArrayList<RC> RCList = new ArrayList<>();
        ArrayList<RC> RCList1 = new ArrayList<>();
        ArrayList<RC> RCList2 = new ArrayList<>();
        ArrayList<RC> RCList3 = new ArrayList<>();
        ArrayList<RC> RCList4 = new ArrayList<>();

        for(int x = 1; x <= width; x++){
            for(int y = 1; y <= height; y++){
                if(gcd(x,y)==1){
                    RCList1.add(new RC(x,-y));
                    RCList2.add(new RC(x,y));
                    RCList3.add(new RC(-x,y));
                    RCList4.add(new RC(-x,-y));
                }
            }
        }

        RCList.add(new RC(0,-1));
        Collections.sort(RCList1, new slopeComparator());
        RCList.addAll(RCList1);
        RCList.add(new RC(1,0));
        Collections.sort(RCList2, new slopeComparator());
        RCList.addAll(RCList2);
        RCList.add(new RC(0,1));
        Collections.sort(RCList3, new slopeComparator());
        RCList.addAll(RCList3);
        RCList.add(new RC(-1,0));
        Collections.sort(RCList4, new slopeComparator());
        RCList.addAll(RCList4);

        return RCList;
    }

    static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

}
