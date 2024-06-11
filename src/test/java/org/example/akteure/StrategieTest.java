package org.example.akteure;

import org.example.spiel.Position;
import org.example.spiel.TicTacToe;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StrategieTest {
    TicTacToe ticTacToe = new TicTacToe();
    Strategie strategie = new Strategie(ticTacToe, ticTacToe.getZeichenGegner());
    Position[] ecken = {new Position(0, 0), new Position(2, 0), new Position(0, 2), new Position(2, 2)};

    @Test
    void TestGetZug() {
        TicTacToe zug = strategie.getZug();
        assertNotNull(zug);
        assertEquals(ticTacToe, zug);
    }

    @Test
    void TestSpielerHatInDieMitteGelegt() {
        ticTacToe = new TicTacToe();
        ticTacToe.getMatrix()[1][1] = ticTacToe.getZeichenSpieler();
        strategie = new Strategie(ticTacToe, ticTacToe.getZeichenSpieler());
        assertFalse(strategie.SpielerHatInDieMitteGelegt());

        ticTacToe = new TicTacToe();
        ticTacToe.getMatrix()[1][1] = ticTacToe.getZeichenGegner();
        strategie = new Strategie(ticTacToe, ticTacToe.getZeichenSpieler());
        assertTrue(strategie.SpielerHatInDieMitteGelegt());
    }


    @Test
    void diagonaleGelegt() {
        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getZeichenSpieler();
        ticTacToe.getMatrix()[2][2] = ticTacToe.getZeichenGegner();
        assertTrue(strategie.diagonaleGelegt());

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[2][0] = ticTacToe.getZeichenSpieler();
        ticTacToe.getMatrix()[0][2] = ticTacToe.getZeichenGegner();
        assertTrue(strategie.diagonaleGelegt());

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[2][2] = ticTacToe.getZeichenGegner();
        assertFalse(strategie.diagonaleGelegt());

        //usw.

    }

    @Test
    void TestSpieleInDieEcke() {
        for (int i = 0; i < ecken.length; i++) {
            ticTacToe.initialisiereMatrix();

            ticTacToe.getMatrix()[ecken[i].x][ecken[i].y] = ticTacToe.getZeichenGegner();

            assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[ecken[i].x][ecken[i].y]);

            for (int j = 0; j < ecken.length; j++) {
                if (j != i) {
                    assertEquals(ticTacToe.getLeeresFeld(), ticTacToe.getMatrix()[ecken[j].x][ecken[j].y]);
                }
            }
        }
    }

    @Test
    void TestSpieleZweitesZeichen() {
        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[2][2] = ticTacToe.getLeeresFeld();
        strategie.spieleZweitesZeichen();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[2][2]);

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][2] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[2][0] = ticTacToe.getLeeresFeld();
        strategie.spieleZweitesZeichen();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[2][0]);

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[2][0] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[0][2] = ticTacToe.getLeeresFeld();
        strategie.spieleZweitesZeichen();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[0][2]);

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[2][2] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getLeeresFeld();
        strategie.spieleZweitesZeichen();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[0][0]);
    }

    @Test
    void TestSpieleLetzenZug() {
        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][2] = ticTacToe.getZeichenOpposite(ticTacToe.getZeichenGegner());
        strategie.spieleLetztenZug();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[1][0]);

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[2][2] = ticTacToe.getZeichenOpposite(ticTacToe.getZeichenGegner());
        strategie.spieleLetztenZug();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[1][0]);


        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[2][0] = ticTacToe.getZeichenOpposite(ticTacToe.getZeichenGegner());
        strategie.spieleLetztenZug();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[1][2]);

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getZeichenOpposite(ticTacToe.getZeichenGegner());
        strategie.spieleLetztenZug();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[1][2]);

    }

    @Test
    void TestGewinnZug() {
        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[0][0] = ticTacToe.getZeichenSpieler();
        ticTacToe.getMatrix()[0][1] = ticTacToe.getZeichenSpieler();
        ticTacToe.getMatrix()[0][2] = ticTacToe.getLeeresFeld();


        Position erwartetePositionSpieler = new Position(0, 2);
        Position gewinnPositionSpieler = strategie.gewinnZug();
        assertNotNull(gewinnPositionSpieler);
        assertEquals(erwartetePositionSpieler.x, gewinnPositionSpieler.x);
        assertEquals(erwartetePositionSpieler.y, gewinnPositionSpieler.y);

        //---------------------------------------

        ticTacToe.initialisiereMatrix();
        ticTacToe.getMatrix()[1][0] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[1][1] = ticTacToe.getZeichenGegner();
        ticTacToe.getMatrix()[1][2] = ticTacToe.getLeeresFeld();

        Position erwartetePositionGegner = new Position(1, 2);
        Position gewinnPositionGegner = strategie.gewinnZug();
        assertNotNull(gewinnPositionGegner);
        assertEquals(erwartetePositionGegner.x, gewinnPositionGegner.x);
        assertEquals(erwartetePositionGegner.y, gewinnPositionGegner.y);
    }

    @Test
    void legeZufaellig() {
        ticTacToe.initialisiereMatrix();
        for (int i = 0; i < ticTacToe.getMatrix().length; i++) {
            for (int j = 0; j < ticTacToe.getMatrix()[i].length; j++) {
                ticTacToe.getMatrix()[i][j] = ticTacToe.getZeichenGegner();
            }
        }
        int leeresX = 1;
        int leeresY = 1;
        ticTacToe.getMatrix()[leeresX][leeresY] = ticTacToe.getLeeresFeld();

        strategie.legeZufaellig();
        assertEquals(ticTacToe.getZeichenGegner(), ticTacToe.getMatrix()[leeresX][leeresY]);
    }


}