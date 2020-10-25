package com.mycompany.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
public class Day6Exercise {

    public static void main(String []args) throws FileNotFoundException, Exception{
        // reading file and saving
        File file = new File("inputDay6.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String>[] input = new ArrayList[2];
        input[0] = new ArrayList<String>();
        input[1] = new ArrayList<String>();
        while(sc.hasNextLine()){
            String[] arr = sc.nextLine().split("\\)");
            input[0].add(arr[0]);
            input[1].add(arr[1]);
        }

        ArrayList<String>[] counterList1 = new ArrayList[2];
        counterList1[0] = new ArrayList<String>();
        counterList1[1] = new ArrayList<String>();
        for(int j = 0; j < input[0].size(); j++){
            int counter = 1;
            counterList1[0].add(input[1].get(j));
            String code = input[0].get(j);
            int index = input[1].indexOf(code);
            while(index != -1){
                code = input[0].get(index);
                index = input[1].indexOf(code);
                counter++;
            }
            counterList1[1].add(String.valueOf(counter));
        }

        // filling YOU list
        ArrayList<String> YOUlist = new ArrayList<>();
        String YOUcode = "YOU";
        YOUlist.add(YOUcode);
        int YOUindex = input[1].indexOf(YOUcode);
        while(YOUindex != -1){
            YOUcode = input[0].get(YOUindex);
            YOUlist.add(YOUcode);
            YOUindex = input[1].indexOf(YOUcode);
        }

        // filling SANta list
        ArrayList<String> SANlist = new ArrayList<>();
        String SANcode = "SAN";
        YOUlist.add(SANcode);
        int SANindex = input[1].indexOf(SANcode);
        while(SANindex != -1){
            SANcode = input[0].get(SANindex);
            SANlist.add(SANcode);
            SANindex = input[1].indexOf(SANcode);
        }

        int commonIndexYOU = 0;
        int commonIndexSAN = 0;
        boolean hasFound = false;
        for(int y = 0; y < YOUlist.size() && !hasFound; y++){
            if(SANlist.contains(YOUlist.get(y))){
                commonIndexYOU = y;
                commonIndexSAN = SANlist.indexOf(YOUlist.get(y));
                hasFound = true;
            }
        }
        System.out.println(commonIndexYOU+commonIndexSAN-1);

//        System.out.println("YOU list");
//        for(int k = 0; k < YOUlist.size(); k++){
//            System.out.println(YOUlist.get(k));
//        }
//        System.out.println("SAN list");
//        for(int k = 0; k < SANlist.size(); k++){
//            System.out.println(SANlist.get(k));
//        }

        // simple example (op volgorde)
//        ArrayList<String>[] counterList = new ArrayList[2];
//        counterList[0] = new ArrayList<String>();
//        counterList[1] = new ArrayList<String>();
//        counterList[0].add(0, input[0].get(0));
//        counterList[1].add(0, String.valueOf(0));
//        for(int j = 0; j < input[0].size(); j++){
//            counterList[0].add(input[1].get(j));
//            int index = counterList[0].indexOf(input[0].get(j));
//            int counter = 1 + Integer.parseInt(counterList[1].get(index));
//            counterList[1].add(String.valueOf(counter));
//        }

        int total = 0;
        for(int k = 0; k < counterList1[0].size(); k++){
            total += Integer.parseInt(counterList1[1].get(k));
        }

    }

}
