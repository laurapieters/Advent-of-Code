
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s139445
 */
public class Day3Exercise {
    
    public static void main(String []args) throws FileNotFoundException, Exception {
        File file = new File("inputDay3.txt"); 
        Scanner sc = new Scanner(file);
        
        String[] firstWire = sc.nextLine().split(",");
        String[] secondWire = sc.nextLine().split(",");
        
        int firstX = 0;
        int firstY = 0;
        int secondX = 0;
        int secondY = 0;
        
        ArrayList<int[]> firstCoordinates = new ArrayList<int[]>();
        ArrayList<int[]> secondCoordinates = new ArrayList<int[]>();
        ArrayList<int[]> crossings = new ArrayList<int[]>();
        
        // move first wire
        for(int i = 0; i < firstWire.length; i++){   
            if(firstWire[i].substring(0,1).equals("R")){
                for(int r = firstX+1; r < firstX+Integer.parseInt(firstWire[i].substring(1))+1; r++){
                    int[] arr = {r,firstY};
                    firstCoordinates.add(arr);
                }
                firstX += Integer.parseInt(firstWire[i].substring(1));
                
            }else if(firstWire[i].substring(0,1).equals("L")){
                for(int r = firstX-1; r > firstX-Integer.parseInt(firstWire[i].substring(1))-1; r--){
                    int[] arr = {r,firstY};
                    firstCoordinates.add(arr);
                }
                firstX -= Integer.parseInt(firstWire[i].substring(1));
            }else if(firstWire[i].substring(0,1).equals("U")){
                for(int r = firstY+1; r < firstY+Integer.parseInt(firstWire[i].substring(1))+1; r++){
                    int[] arr = {firstX,r};
                    firstCoordinates.add(arr);
                }
                firstY += Integer.parseInt(firstWire[i].substring(1));
            }else if(firstWire[i].substring(0,1).equals("D")){
                for(int r = firstY-1; r > firstY-Integer.parseInt(firstWire[i].substring(1))-1; r--){
                    int[] arr = {firstX,r};
                    firstCoordinates.add(arr);
                }
                firstY -= Integer.parseInt(firstWire[i].substring(1));
            }else{
                throw new Exception("Not a direction");
            }  
        }     
                
        // move second wire        
        for(int j = 0; j < secondWire.length; j++){
                if(secondWire[j].substring(0,1).equals("R")){
                    for(int r = secondX+1; r < secondX+Integer.parseInt(secondWire[j].substring(1))+1; r++){
                        int[] arr = {r,secondY};
                        secondCoordinates.add(arr);
                    }
                    secondX += Integer.parseInt(secondWire[j].substring(1));
                }else if(secondWire[j].substring(0,1).equals("L")){
                    for(int r = secondX-1; r > secondX-Integer.parseInt(secondWire[j].substring(1))-1; r--){
                        int[] arr = {r,secondY};
                        secondCoordinates.add(arr);
                    }
                    secondX -= Integer.parseInt(secondWire[j].substring(1));
                }else if(secondWire[j].substring(0,1).equals("U")){
                    for(int r = secondY+1; r < secondY+Integer.parseInt(secondWire[j].substring(1))+1; r++){
                        int[] arr = {secondX,r};
                        secondCoordinates.add(arr);
                    }
                    secondY += Integer.parseInt(secondWire[j].substring(1));
                }else if(secondWire[j].substring(0,1).equals("D")){
                    for(int r = secondY-1; r > secondY-Integer.parseInt(secondWire[j].substring(1))-1; r--){
                        int[] arr = {secondX,r};
                        secondCoordinates.add(arr);
                    }
                    secondY -= Integer.parseInt(secondWire[j].substring(1));
                }else{
                    throw new Exception("Not a direction");
                }  
        }    
        
//        for(int i=0;i<firstCoordinates.size();i++){
//            System.out.println(firstCoordinates.get(i)[0]+","+firstCoordinates.get(i)[1]);
//        }
               
        // look for crossings
        for(int i = 0; i < firstCoordinates.size(); i++){
            for(int j = 0; j < secondCoordinates.size(); j++){
                if(firstCoordinates.get(i)[0]==secondCoordinates.get(j)[0] && 
                        firstCoordinates.get(i)[1]==secondCoordinates.get(j)[1]){
                    int[] arr = {firstCoordinates.get(i)[0],firstCoordinates.get(i)[1]};
                    crossings.add(arr);
                }
            }
        }
        
//        for(int i =0; i<crossings.size();i++){
//            System.out.println(crossings.get(i)[0]+","+crossings.get(i)[1]);
//        }
        
        // find closest crossing
        int[] closestCrossing = crossings.get(1);
        for(int i=0; i < crossings.size(); i++){
            if(crossings.get(i)[0]+crossings.get(i)[1] != 0 &&
                    abs(crossings.get(i)[0])+abs(crossings.get(i)[1]) <
                    abs(closestCrossing[0])+abs(closestCrossing[1])){
                closestCrossing = crossings.get(i);
            }
        }
        
        int[][] crossingStepsFirst = new int[crossings.size()][3];
        int[][] crossingStepsSecond = new int[crossings.size()][3];
        
        // finding number of steps to each crossing for first wire
        for(int j =0; j < crossings.size(); j++){
            int stepsFirst = 0;
            for(int i=0; i < firstCoordinates.size(); i++){
                stepsFirst++;
                if(firstCoordinates.get(i)[0]==crossings.get(j)[0] && firstCoordinates.get(i)[1]==crossings.get(j)[1]){
                    crossingStepsFirst[j][0] = stepsFirst;
                    crossingStepsFirst[j][1] = crossings.get(j)[0];
                    crossingStepsFirst[j][2] = crossings.get(j)[1];
                    break;
                }
            }
        }
        
        // finding number of steps to each crossing for second wire
        for(int j = 0; j < crossings.size();j++){
            int stepsSecond = 0;
            for(int i=0; i < secondCoordinates.size(); i++){
                stepsSecond++;
                if(secondCoordinates.get(i)[0]==crossings.get(j)[0] && secondCoordinates.get(i)[1]==crossings.get(j)[1]){
                    crossingStepsSecond[j][0] = stepsSecond;
                    crossingStepsSecond[j][1] = crossings.get(j)[0];
                    crossingStepsSecond[j][2] = crossings.get(j)[1];
                    break;
                }
            }    
        }
        
//        for(int i=0;i<crossingStepsFirst.length;i++){
//            System.out.println(crossingStepsFirst[i][0]);
//        }
        
        // to do: crossingStepsFirst en Second op zelfde manier sorteren en #steps optellen en minimum pakken

        int stepsMin = Integer.MAX_VALUE;
        for(int i = 0; i < crossings.size(); i++){
            if(crossingStepsFirst[i][0]+crossingStepsSecond[i][0] < stepsMin){
                stepsMin = crossingStepsFirst[i][0]+crossingStepsSecond[i][0];
            }
        }
        
        System.out.println(stepsMin);
        
    }          
}
