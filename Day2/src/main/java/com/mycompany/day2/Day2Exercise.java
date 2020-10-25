/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author s139445
 */
public class Day2Exercise {
    
     public static void main(String []args) throws FileNotFoundException {
        File file = new File("inputDay2.txt"); 
        Scanner sc = new Scanner(file); 
        
        // Reading program and saving
        String[] programString = sc.nextLine().split(",");
        int programLength = programString.length;
        int[] program;
        program = new int[programLength];
        for(int i=0; i < programLength; i++){
             program[i] = Integer.parseInt(programString[i]);
        }
        
        int foundNoun = 0;
        int foundVerb = 0;
        int[] newprogram;
        newprogram = new int[programLength];
        for(int noun = 0; noun < 100; noun++){
            for(int verb = 0; verb < 100; verb++){
                
                System.arraycopy(program, 0, newprogram, 0, programLength);
                newprogram[1] = noun;
                newprogram[2] = verb;
                
                for(int i=0; i < programLength; i+=4){
                    if(newprogram[i] == 1){
                        newprogram[newprogram[i+3]] = newprogram[newprogram[i+1]] + newprogram[newprogram[i+2]];
                    }else if (newprogram[i] == 2){
                        newprogram[newprogram[i+3]] = newprogram[newprogram[i+1]] * newprogram[newprogram[i+2]];
                    }else{
                        break;
                    }
                    
                }
                if(newprogram[0]==19690720){
                    foundNoun=noun;
                    foundVerb=verb;
                }
            }
        }
        System.out.println(100*foundNoun+foundVerb);
    }
    
}
