package com.mycompany.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5ExerciseTest {

    @Test
    public void test() throws Exception {
        int[] program = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        Day5Exercise.intcode(program);
    }
}