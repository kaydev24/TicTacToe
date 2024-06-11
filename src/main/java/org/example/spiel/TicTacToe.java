package org.example.spiel;

import static java.lang.System.out;

public class TicTacToe {
    private Zeichen[][] matrix;

    public TicTacToe() {
        initialisiereMatrix();
    }

    public Zeichen getZeichenGegner() {
        return Zeichen.O;
    }

    public Zeichen getZeichenSpieler() {
        return Zeichen.X;
    }

    public Zeichen getLeeresFeld() {
        return Zeichen.I;
    }

    public Zeichen getZeichenOpposite(Zeichen zeichen) {
        switch (zeichen) {
            case X:
                return getZeichenGegner();
            case O:
                return getZeichenSpieler();
            default:
                return getLeeresFeld();
        }
    }

    public Zeichen[][] getMatrix() {
        return matrix;
    }

    public Zeichen getFeld(int x, int y) {
        return matrix[x][y];
    }

    public boolean isLeer(Position pos) {
        return getLeeresFeld() == matrix[pos.x][pos.y];
    }

    public boolean isLeer(int x, int y) {
        return getLeeresFeld() == matrix[x][y];
    }

    public void initialisiereMatrix() {
        matrix = new Zeichen[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = getLeeresFeld();
            }
        }
    }

    public Spielstatus getSpielstatus() {
        if (pruefeGewinner()) {
            return Spielstatus.MENSCH_GEWINNT;
        } else if (Spielstatus.COMPUTER_GEWINNT == pruefeVerlierer()) {
            return Spielstatus.COMPUTER_GEWINNT;
        } else if (Spielstatus.UNENTSCHIEDEN == pruefeUnentschieden()) {
            return Spielstatus.UNENTSCHIEDEN;
        } else {
            return Spielstatus.SPIEL_NICHT_BEENDET;
        }
    }

    public boolean pruefeGewinner() {
        if (pruefeGewinner(getZeichenSpieler())) {
            out.println(Spielstatus.MENSCH_GEWINNT.toString());
            return true;
        }
        return false;
    }

    public boolean pruefeGewinner(Zeichen zeichen) {
        return (matrix[0][0] == zeichen && matrix[0][1] == zeichen && matrix[0][2] == zeichen) ||
                (matrix[1][0] == zeichen && matrix[1][1] == zeichen && matrix[1][2] == zeichen) ||
                (matrix[2][0] == zeichen && matrix[2][1] == zeichen && matrix[2][2] == zeichen) ||
                (matrix[0][0] == zeichen && matrix[1][0] == zeichen && matrix[2][0] == zeichen) ||
                (matrix[0][1] == zeichen && matrix[1][1] == zeichen && matrix[2][1] == zeichen) ||
                (matrix[0][2] == zeichen && matrix[1][2] == zeichen && matrix[2][2] == zeichen) ||
                (matrix[0][0] == zeichen && matrix[1][1] == zeichen && matrix[2][2] == zeichen) ||
                (matrix[2][0] == zeichen && matrix[1][1] == zeichen && matrix[0][2] == zeichen);
    }

    public Spielstatus pruefeVerlierer() {
        if (pruefeGewinner(getZeichenSpieler().getOppositeSpieler())) {
            out.println(Spielstatus.COMPUTER_GEWINNT.toString());
            return Spielstatus.COMPUTER_GEWINNT;
        }
        return Spielstatus.SPIEL_NICHT_BEENDET;
    }


    public Spielstatus pruefeUnentschieden() {
        boolean istVoll = true;

        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix()[i].length; j++) {
                if (getMatrix()[i][j] == getLeeresFeld()) {
                    istVoll = false;
                    break;
                }
            }
        }
        if (istVoll) {
            out.println(Spielstatus.UNENTSCHIEDEN.toString());
            return Spielstatus.UNENTSCHIEDEN;
        }
        return Spielstatus.SPIEL_NICHT_BEENDET;
    }
}