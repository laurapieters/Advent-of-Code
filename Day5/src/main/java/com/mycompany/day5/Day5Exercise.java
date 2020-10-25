package com.mycompany.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author s139445
 */
public class Day5Exercise {

    public static void main(String[] args) throws FileNotFoundException, Exception {
        File file = new File("inputDay5.txt");
        Scanner sc = new Scanner(file);

        // Reading program and saving
        String[] programString = sc.nextLine().split(",");
        int programLength = programString.length;
        int[] program;
        program = new int[programLength];
        for (int i = 0; i < programLength; i++) {
            program[i] = Integer.parseInt(programString[i]);
        }

        intcode(program);
    }

    public static void intcode(int[] program) throws Exception {

        Scanner sc1 = new Scanner(System.in);
        int input;

        int i = 0;
        while (i < program.length) {
            if (program[i] == 1) {
                // addition
                program[program[i + 3]] = program[program[i + 1]] + program[program[i + 2]];
                i += 4;
            } else if (program[i] == 2) {
                // multiplication
                program[program[i + 3]] = program[program[i + 1]] * program[program[i + 2]];
                i += 4;
            } else if (program[i] == 3) {
                // input
                System.out.println("Give input please");
                input = sc1.nextInt();
                sc1.close();
                program[program[i + 1]] = input;
                i += 2;
            } else if (program[i] == 4) {
                // output
                System.out.println("This is the output");
                System.out.println(program[program[i + 1]]);
                i += 2;
            } else if (program[i] == 5) {
                // jump if true
                if (program[program[i + 1]] != 0) {
                    i = program[program[i + 2]];
                }else{
                    i+=3;
                }
            } else if (program[i] == 6) {
                // jump if false
                if (program[program[i + 1]] == 0) {
                    i = program[program[i + 2]];
                }else{
                    i+=3;
                }
            } else if (program[i] == 7) {
                // less than
                if (program[program[i + 1]] < program[program[i + 2]]) {
                    program[program[i + 3]] = 1;
                } else {
                    program[program[i + 3]] = 0;
                }
                i += 4;
            } else if (program[i] == 8) {
                // equal
                if (program[program[i + 1]] == program[program[i + 2]]) {
                    program[program[i + 3]] = 1;
                } else {
                    program[program[i + 3]] = 0;
                }
                i += 4;
            } else if (program[i] == 99) {
                // halt
                break;
            } else if (program[i] > 99) {
                // modes
                int opcode = program[i] % 10;

                int mode1 = (program[i] / 100) % 10;
                int mode2 = (program[i] / 1000) % 10;
                int valuePar1 = 0;
                int valuePar2 = 0;

                if (opcode == 1) {
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    program[program[i+3]] = valuePar1 + valuePar2;
                    i += 4;
                } else if (opcode == 2) {
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    program[program[i+3]] = valuePar1 * valuePar2;
                    i += 4;
                } else if (opcode == 3) {
                    System.out.println("Give input please");
                    input = sc1.nextInt();
                    sc1.close();
                    program[program[i+1]] = input;
                    i += 2;
                } else if (opcode == 4) {
                    System.out.println("This is the output");
                    System.out.println(program[i+1]);
                    i += 2;
                } else if (opcode == 5) {
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    // jump if true
                    if (valuePar1 != 0) {
                        i = valuePar2;
                    }else{
                        i+=3;
                    }
                } else if (opcode == 6) {
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    // jump if false
                    if (valuePar1 == 0) {
                        i = valuePar2;
                    }else{
                        i+=3;
                    }
                } else if (opcode == 7) {
                    // less than
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    if (valuePar1 < valuePar2) {
                        program[program[i + 3]] = 1;
                    } else {
                        program[program[i + 3]] = 0;
                    }
                    i += 4;
                } else if (opcode == 8) {
                    // equal
                    if (mode1 == 0) {
                        // position mode
                        valuePar1 = program[program[i + 1]];
                    } else if (mode1 == 1) {
                        // immediate mode
                        valuePar1 = program[i + 1];
                    }
                    if (mode2 == 0) {
                        // position mode
                        valuePar2 = program[program[i + 2]];
                    } else if (mode2 == 1) {
                        // immediate mode
                        valuePar2 = program[i + 2];
                    }
                    if (valuePar1 == valuePar2) {
                        program[program[i + 3]] = 1;
                    } else {
                        program[program[i + 3]] = 0;
                    }
                    i += 4;
                } else {
                    throw new Exception("Not a valid opcode");
                }

            } else {
                throw new Exception("Not a valid opcode");
            }
        }
    }

}
