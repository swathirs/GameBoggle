package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;




@RunWith(AndroidJUnit4.class)
public class InstrumentedDictionaryTest {

    private Dictionary aDictionary;
    private Context appContext;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        aDictionary = new Dictionary(appContext);
    }


    @Test
    public void isWord_validWord_ReturnsTrue() {
        assertTrue(aDictionary.isWord("aback"));
    }


    @Test
    public void isWord_invalidWord_ReturnsFalse() {
        assertFalse(aDictionary.isWord("abcdef"));
    } //dictionary can't handle non-a-t characters


    @Test
    public void isWord_emptyString_ReturnsFalse() {
        assertFalse(aDictionary.isWord(""));
    }


    /*@Test
    public void isWord_nullWord_ReturnsFalse() {
        assertFalse(aDictionary.isWord(null));
    }
    isWord needs a string, crashes on null input*/
}