package com.example.swathi.gameboggle;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;




@RunWith(MockitoJUnitRunner.class)
public class DictionaryTest {
    private Dictionary aDictionary;

    public DictionaryTest() {
        aDictionary = new Dictionary(mMockContext);
    }

    @Mock
    Context mMockContext;

    @Test
    public void isWord_validWord_ReturnsTrue() {
        assertTrue(aDictionary.isWord("aback"));
    }

    @Test
    public void isWord_invalidWord_ReturnsFalse() {
        assertFalse(aDictionary.isWord("ab&*2d"));
    }

    @Test
    public void isWord_emptyString_ReturnsFalse() {
        assertFalse(aDictionary.isWord(""));
    }

    @Test
    public void isWord_nullWord_ReturnsFalse() {
        assertFalse(aDictionary.isWord(null));
    }

}