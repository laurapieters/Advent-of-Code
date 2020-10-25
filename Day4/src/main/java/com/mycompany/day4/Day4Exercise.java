
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s139445
 */
public class Day4Exercise {
    
    public static void main(String []args){
    
        int start = 264793;
        int end = 803935;

        int counterPasswords = 0;
        for(int i = start; i <= end; i++){   
            if(adjacentFound(i) && doubleFound(i)){
                counterPasswords ++;
            }
        }
     
        System.out.println(counterPasswords);
    }
    
    public static boolean adjacentFound(int number){
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while(number > 0){
            digits.add(number%10);
            number /= 10;
        }
        boolean adjacent = true;
        for(int i = 0; i < digits.size()-1; i++){
            if(digits.get(i) < digits.get(i+1)){
                adjacent = false;
            }
        }
        return adjacent;
    }
    
    public static boolean doubleFound(int number){
        String strNumber = Integer.toString(number);
        boolean doubl = false;
        for(int i = 1; i <= strNumber.length()- 3; i++){
            if(!strNumber.substring(i-1,i).equals(strNumber.substring(i,i+1)) &&
                    strNumber.substring(i,i+1).equals(strNumber.substring(i+1,i+2)) &&
                    !strNumber.substring(i+1,i+2).equals(strNumber.substring(i+2,i+3)) ){
                doubl = true;
            }
        }
        if(strNumber.substring(0,1).equals(strNumber.substring(1,2)) &&
                    !strNumber.substring(1,2).equals(strNumber.substring(2,3))){
            doubl = true;
        }
        if(!strNumber.substring(strNumber.length()-3,strNumber.length()-2).equals(strNumber.substring(strNumber.length()-2,strNumber.length()-1)) &&
                    strNumber.substring(strNumber.length()-2,strNumber.length()-1).equals(strNumber.substring(strNumber.length()-1,strNumber.length()))){
            doubl = true;
        }
        return doubl;
    }
}
