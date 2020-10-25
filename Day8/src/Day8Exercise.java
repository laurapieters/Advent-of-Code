package com.mycompany.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8Exercise {

    public static void main(String []args) throws FileNotFoundException{
        int width = 25;
        int height = 6;

        File file = new File("inputDay8.txt");
        Scanner sc = new Scanner(file);

        ArrayList<int[][]> input = new ArrayList<>();
        String inputString = sc.next();

        int l = 0;
        while(l*width*height < inputString.length()) {
            int[][] arr = new int[width][height];
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    arr[i][j] = Character.getNumericValue(inputString.charAt(l*width*height+(j*width)+i));
                }
            }
            input.add(arr);
            l++;
        }

        int[][] numberCounter = new int[l][3];
        for(int k = 0; k < l; k++){
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    if(input.get(k)[i][j] == 0){
                        numberCounter[k][0]++;
                    }else if(input.get(k)[i][j] == 1){
                        numberCounter[k][1]++;
                    }else if(input.get(k)[i][j] == 2){
                        numberCounter[k][2]++;
                    }
                }
            }
        }

        int minZero = 9999999;
        int minZeroIndex = 0;
        for(int k = 0; k < l; k++){
            if(numberCounter[k][0] <= minZero){
                minZero = numberCounter[k][0];
                minZeroIndex = k;
            }
        }

//        System.out.println(numberCounter[minZeroIndex][1]*numberCounter[minZeroIndex][2]);

        int[][] finalImage = new int[width][height];
        for(int k = l-1; k >= 0; k--) {
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    if(input.get(k)[i][j] != 2){
                        finalImage[i][j] = input.get(k)[i][j];
                    }
                }
            }
        }

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(finalImage[i][j]);
            }
            System.out.println();
        }
    }
}
