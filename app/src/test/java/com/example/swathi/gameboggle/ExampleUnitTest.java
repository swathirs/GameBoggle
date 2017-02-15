package com.example.swathi.gameboggle;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
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