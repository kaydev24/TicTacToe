package org.example.spiel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeichenTest {
    @Test
    void testGetDarstellung(){
        assertEquals('X', Zeichen.X.getDarstellung());
        assertEquals('O', Zeichen.O.getDarstellung());
        assertEquals('-', Zeichen.I.getDarstellung());
    }

    @Test
    void testGetOppositeSpieler() {
        assertEquals(Zeichen.O, Zeichen.X.getOppositeSpieler());
        assertEquals(Zeichen.X, Zeichen.O.getOppositeSpieler());
        assertEquals(Zeichen.I, Zeichen.I.getOppositeSpieler());
    }

    @Test
    void testGetFromChar() {
        assertEquals(Zeichen.X, Zeichen.getFromChar('X'));
        assertEquals(Zeichen.O, Zeichen.getFromChar('O'));
        assertEquals(Zeichen.I, Zeichen.getFromChar('-'));
        assertNull(Zeichen.getFromChar('Y'));
    }


}