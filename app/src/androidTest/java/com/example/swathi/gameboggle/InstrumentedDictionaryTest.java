package com.example.swathi.gameboggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;




@RunWith(AndroidJUnit4.class)
public class InstrumentedDictionaryTest {

    private Dictionary aDictionary;
    private Context appContext;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        aDictionary = new Dictionary(appContext);
    }

    // TESTS 1:  isWord
    // isWord will take in a string and return true if that string is a word in the dictionary

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


    // TESTS 2: isPrefix
    // isPrefix will return true if there are any words starting with the passed in string

    @Test
    public void isPre_validPre_short_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix("ab"));
    }

    @Test
    public void isPre_validPre_full_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix("year"));
    }

    @Test
    public void isPre_inValidPre_ReturnsFalse() {
        assertFalse(aDictionary.isPrefix("zx"));
    }

    @Test
    public void isPre_empty_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix(""));
    }

    @Test
    public void isPre_oneChar_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix("x"));
    }

    @Test
    public void isPre_long_hasPlural_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix("zitherist"));
    }

    @Test
    public void isPre_long_isThePlural_ReturnsTrue() {
        assertTrue(aDictionary.isPrefix("zitherists"));
    }


    @Test
    public void isPre_long_NotValidPlural_ReturnsFalse() {
        assertFalse(aDictionary.isPrefix("zitheristsz"));
    }

}