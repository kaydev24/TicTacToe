package org.example.spiel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    TicTacToe tictactoe = new TicTacToe();

    @Test
    public void testGetZeichenGegner() {
        Zeichen zeichen = tictactoe.getZeichenGegner();
        assertEquals(Zeichen.O, zeichen);
    }

    @Test
    public void testGetZeichenSpieler() {
        Zeichen zeichen = tictactoe.getZeichenSpieler();
        assertEquals(Zeichen.X, zeichen);
    }

    @Test
    public void testGetLeeresFeld() {
        Zeichen zeichen = tictactoe.getLeeresFeld();
        assertEquals(Zeichen.I, zeichen);
    }

    @Test
    public void testGetZeichenOpposite() {
        assertAll(
                () -> assertEquals(Zeichen.X, tictactoe.getZeichenOpposite(Zeichen.O)),
                () -> assertEquals(Zeichen.O, tictactoe.getZeichenOpposite(Zeichen.X)),
                () -> assertEquals(Zeichen.I, tictactoe.getZeichenOpposite(Zeichen.I))
        );
    }


    @Test
    void testGetMatrix() {
        Zeichen[][] erwartetesSpielbrett = new Zeichen[3][3];
        for (int i = 0; i < erwartetesSpielbrett.length; i++) {
            for (int j = 0; j < erwartetesSpielbrett[i].length; j++) {
                erwartetesSpielbrett[i][j] = tictactoe.getLeeresFeld();
            }
        }

        Zeichen[][] spielbrett = tictactoe.getMatrix();
        assertAll(
                () -> {
                    for (int i = 0; i < spielbrett.length; i++) {
                        assertArrayEquals(erwartetesSpielbrett[i], spielbrett[i]);
                    }
                }
        );
    }


    @Test
    void testGetFeld() {
        assertAll(
                () -> {
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            assertEquals(tictactoe.getMatrix()[x][y], tictactoe.getFeld(x, y));
                        }
                    }
                }
        );
    }


    @Test
    void testIsLeer() {
        assertAll(
                () -> {
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            assertTrue(tictactoe.isLeer(new Position(x, y)));
                        }
                    }
                }
        );
    }

    @Test
    void testIsLeer2() {
        assertAll(
                () -> {
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            assertTrue(tictactoe.isLeer(x, y));
                        }
                    }
                }
        );
    }


    @Test
    void testInitialisiereMatrix() {
        tictactoe.initialisiereMatrix();

        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                assertEquals(tictactoe.getLeeresFeld(), tictactoe.getMatrix()[i][j]);
            }
        }
    }


    @Test
    void testGetSpielstatus() {
        Spielstatus spielstatus = tictactoe.getSpielstatus();

        assertEquals(Spielstatus.MENSCH_GEWINNT, spielstatus);
        assertEquals(Spielstatus.COMPUTER_GEWINNT, spielstatus);
        assertEquals(Spielstatus.UNENTSCHIEDEN, spielstatus);
        assertEquals(Spielstatus.SPIEL_NICHT_BEENDET, spielstatus);
    }

    @ParameterizedTest
    @EnumSource(Zeichen.class)
    void testPruefeEinenGewinner(Zeichen zeichen) {
        //Vertikal
        //tictactoe.initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                tictactoe.getMatrix()[j][i] = zeichen;
            }
            assertTrue(tictactoe.pruefeGewinner(zeichen));
        }

        //Horizontal
        //tictactoe.initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                tictactoe.getMatrix()[i][j] = zeichen;
            }
            assertTrue(tictactoe.pruefeGewinner(zeichen));
        }

        //Diagonale (Ab)
        //tictactoe.initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            tictactoe.getMatrix()[i][i] = zeichen;
        }
        assertTrue(tictactoe.pruefeGewinner(zeichen));


        //Diagonale (Auf)
        //tictactoe.initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            tictactoe.getMatrix()[i][tictactoe.getMatrix().length - 1 - i] = zeichen;
        }
        assertTrue(tictactoe.pruefeGewinner(zeichen));
    }

    private void simuliereVertikalenGewinn(Zeichen zeichen) {
        //initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                tictactoe.getMatrix()[j][i] = zeichen;
            }
        }
    }

    private void simuliereHorizontalenGewinn(Zeichen zeichen) {
        //initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                tictactoe.getMatrix()[i][j] = zeichen;
            }
        }
    }

    private void simuliereDiagonaleAbGewinn(Zeichen zeichen) {
        //initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            tictactoe.getMatrix()[i][i] = zeichen;
        }
    }

    private void simuliereDiagonaleAufGewinn(Zeichen zeichen) {
        //initialisiereMatrix();
        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            tictactoe.getMatrix()[i][tictactoe.getMatrix().length - 1 - i] = zeichen;
        }
    }


    @Test
    void testPruefeSpielerVerlierer() {
        Zeichen zeichen = tictactoe.getZeichenSpieler().getOppositeSpieler();

        simuliereHorizontalenGewinn(zeichen);
        assertEquals(Spielstatus.COMPUTER_GEWINNT, tictactoe.pruefeVerlierer());

        simuliereVertikalenGewinn(zeichen);
        assertEquals(Spielstatus.COMPUTER_GEWINNT, tictactoe.pruefeVerlierer());

        simuliereDiagonaleAbGewinn(zeichen);
        assertEquals(Spielstatus.COMPUTER_GEWINNT, tictactoe.pruefeVerlierer());

        simuliereDiagonaleAufGewinn(zeichen);
        assertEquals(Spielstatus.COMPUTER_GEWINNT, tictactoe.pruefeVerlierer());

    }

    //Für testPrüfeGewinnerSpieler dementsprechend das gleiche....

    @Test
    void testPruefeUnentschieden() {

        for (int i = 0; i < tictactoe.getMatrix().length; i++) {
            for (int j = 0; j < tictactoe.getMatrix()[i].length; j++) {
                    tictactoe.getMatrix()[i][j] = null; //(als Pseudo Zeichen ??)
            }
        }
        assertEquals(Spielstatus.UNENTSCHIEDEN ,tictactoe.pruefeUnentschieden());
    }

}