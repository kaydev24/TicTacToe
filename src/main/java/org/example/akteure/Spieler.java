package org.example.akteure;
import org.example.spiel.TicTacToe;
import org.example.spiel.Zeichen;
import org.example.spiel.zugInterface;

public class Spieler implements zugInterface {
    private TicTacToe zug;
    private Zeichen zeichen;

    public Spieler(TicTacToe zug, Zeichen zeichen) {
        this.zeichen = zeichen;
        this.zug = zug;
    }

    @Override
    public void setzeZeichen(int x, int y) {

        if (zug.getMatrix()[x][y] == zug.getLeeresFeld()) {
            zug.getMatrix()[x][y] = zeichen;
        } else {
            throw new RuntimeException("Das Feld ist bereits belegt.");
        }
    }
}

