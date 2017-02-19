package com.example.swathi.gameboggle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class DiceControllerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void random_board_generation() {
        DiceController x = new DiceController();
        ArrayList<String> y = null;
        for(int i = 0; i < 5; ++i){
            y = x.genBoard();
            for (String s : y){
                System.out.print(s + ", ");
            }
            System.out.print("\n");
        }
        ArrayList<String> z = x.genBoard();
        assertNotSame(y, z);
    }

}