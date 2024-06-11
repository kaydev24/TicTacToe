package org.example.akteure;

import org.example.spiel.TicTacToe;
import org.example.spiel.Zeichen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpielerTest {
    TicTacToe spiel = new TicTacToe();
    Spieler spieler = new Spieler(spiel, spiel.getZeichenSpieler());

    @Test
    void testSetzeZeichenSpieler(){
        Zeichen leeresFeld = spiel.getLeeresFeld();
        Zeichen spielerZeichen = spiel.getZeichenSpieler();

        assertTrue(spiel.getMatrix()[1][1] == leeresFeld);
        spieler.setzeZeichen(1, 1);
        assertEquals(spielerZeichen, spiel.getMatrix()[1][1]);

        try {
            spieler.setzeZeichen(1, 1);
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Das Feld ist bereits belegt"));
        }
    }
}