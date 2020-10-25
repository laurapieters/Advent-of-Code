/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *
 * @author s139445
 */
public class Day1Exercise {
    
    public static void main(String []args) throws FileNotFoundException {
        File file = new File("inputDay1.txt"); 
        Scanner sc = new Scanner(file); 
        int totalFuel = 0;
        while(sc.hasNextLine()){
            int module = Integer.parseInt(sc.nextLine());
            module = (int)Math.floor(module/3)-2;
            int fuelsum = 0;
            while(module > 0){
                fuelsum += module;
                module = (int)Math.floor(module/3)-2;
            }
            totalFuel += fuelsum;
        }
        System.out.println(totalFuel);
        
    }
    
}
