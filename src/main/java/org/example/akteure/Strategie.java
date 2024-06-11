package org.example.akteure;

import org.example.spiel.Position;
import org.example.spiel.TicTacToe;
import org.example.spiel.Zeichen;

import java.util.Random;

public class Strategie {
    private Random random = new Random();
    private TicTacToe zug;

    private Zeichen zeichen;

    private Position[] ecken = {new Position(0, 0), new Position(2, 0), new Position(0, 2), new Position(2, 2)};

    public Strategie(TicTacToe zug, Zeichen zeichen) {
        this.zug = zug;
        this.zeichen = zeichen;
    }

    public TicTacToe getZug() {
        return zug;
    }

    public boolean SpielerHatInDieMitteGelegt() {
        if (zeichen.getOppositeSpieler() == zug.getFeld(1, 1)) {
            return true;
        }
        return false;
    }

    public boolean diagonaleGelegt() {
        return (!zug.isLeer(0, 0) && zug.getFeld(0, 0) == zug.getFeld(2, 2).getOppositeSpieler())  ||
                (!zug.isLeer(2, 0) && zug.getFeld(2, 0) == zug.getFeld(0, 2).getOppositeSpieler());

/*        if (zug.getZeichenSpieler() == zug.getMatrix()[0][0] || zug.getZeichenSpieler() == zug.getMatrix()[0][2] ||
                zug.getZeichenSpieler() == zug.getMatrix()[2][0] || zug.getZeichenSpieler() == zug.getMatrix()[2][2]) {
            return true;
        }
        return false;*/
    }

    public void spieleInDieEcke() {
        Position pos = gewinnZug();
        if (pos != null) {                                      //Wenn dieser Zug gefunden wurde
            zug.getMatrix()[pos.x][pos.y] = zeichen;
            return;
        }

        int startpunkt = random.nextInt(4);
        while (true) {
            if (zug.isLeer(ecken[startpunkt])) {     //Wenn die zufällig ausgewählte Ecke (siehe Attribut) leer ist...
                // Beispiel: Ecke 0 (siehe Attribut):
                // (Rufe entsprechendes Objekt "ecke[0]" auf.) -> setze mein Zug an y = 0 und x = 0
                zug.getMatrix()[ecken[startpunkt].x][ecken[startpunkt].y] = zeichen;
                break;
            }

            startpunkt = (startpunkt + 1) % ecken.length;
        }
/*
        if (startpunkt == 0 && zug.getMatrix()[0][0] == zug.getLeeresFeld()) {
            zug.getMatrix()[0][0] = zug.getZeichenGegner();
        } else if (startpunkt == 1 && zug.getMatrix()[0][2] == zug.getLeeresFeld()) {
            zug.getMatrix()[0][2] = zug.getZeichenGegner();
        } else if (startpunkt == 2 && zug.getMatrix()[2][0] == zug.getLeeresFeld()) {
            zug.getMatrix()[2][0] = zug.getZeichenGegner();
        } else if (startpunkt == 3 && zug.getMatrix()[2][2] == zug.getLeeresFeld()) {
            zug.getMatrix()[2][2] = zug.getZeichenGegner();
        } else {
            spieleInDieEcke();
        }*/
    }

    public void spieleZweitesZeichen() {
        Position pos = gewinnZug();
        if (pos != null) {                                      //Wenn dieser Zug gefunden wurde
            zug.getMatrix()[pos.x][pos.y] = zeichen;
            return;
        }

        if (zug.getMatrix()[0][0] == zeichen && zug.getMatrix()[2][2] == zug.getLeeresFeld()) {
            zug.getMatrix()[2][2] = zeichen;
        } else if (zug.getMatrix()[0][2] == zeichen && zug.getMatrix()[2][0] == zug.getLeeresFeld()) {
            zug.getMatrix()[2][0] = zeichen;
        } else if (zug.getMatrix()[2][0] == zeichen && zug.getMatrix()[0][2] == zug.getLeeresFeld()) {
            zug.getMatrix()[0][2] = zeichen;
        } else if (zug.getMatrix()[2][2] == zeichen && zug.getMatrix()[0][0] == zug.getLeeresFeld()) {
            zug.getMatrix()[0][0] = zeichen;
        } else {
            legeZufaellig();
        }
    }

    public void spieleLetztenZug() {
        Position pos = gewinnZug();
        if (pos != null) {                              //Wenn dieser Zug gefunden wurde
            zug.getMatrix()[pos.x][pos.y] = zeichen;
            return;
        }

        //Überprüfe ob Ecken belegt und lege auf Ränder links und rechts
        if (zug.getZeichenOpposite(zeichen) == zug.getMatrix()[0][2] || zug.getZeichenOpposite(zeichen) == zug.getMatrix()[2][2]) {
            zug.getMatrix()[1][0] = zeichen;
        } else if (zug.getZeichenOpposite(zeichen) == zug.getMatrix()[2][0] || zug.getZeichenOpposite(zeichen) == zug.getMatrix()[0][0]) {
            zug.getMatrix()[1][2] = zeichen;
        } else {
            legeZufaellig(); //spieleLetzenZug();
        }
    }

    private boolean isGewinnzug(int x, int y, Zeichen zeichen) {
        if (zug.isLeer(x, y)) { // Da ich ein Feld brauchen würde, um zu gewinnen das besetzt werden muss.
            int countX = 0;
            int countY = 0;
            for (int i = 0; i <= 2; i++) {
                //Angenommen x = 1: Ist y = 0 und y = 2 belegt?
                if (zeichen == zug.getFeld(i, y)) {
                    countY++;
                }
                //Angenommen y = 2: Ist x = 0 und x = 1 belegt?
                if (zeichen == zug.getFeld(x, i)) {
                    countX++;
                }
            }
            if ((2 == countX) || (2 == countY)) { //Sind zwei Zeilen oder zwei Spalten mit meinem Zeichen belegt?
                return true;
            }
            // Diagonale prüfen
            if (x == y) {                    // Da ich ein Feld brauche, das ein potenziales Gefälle bildet.
                int count = 0;
                for (int i = 0; i <= 2; i++) {
                    if (zeichen == zug.getFeld(i, i)) { // Prüfe 00, 11 , 22 (Gefälle)
                        count++;
                    }
                }
                if (2 == count) {           //Sind zwei Ecken (Gefälle) mit meinem Zeichen belegt?
                    return true;
                }
            } else if (x + y == 2) {       //Da ich ein Feld brauche, das eine potentiale Steigung bildet. (11, 02, 20)
                // Diagonale 2
                int count = 0;
                for (int i = 0; i <= 2; i++) {
                    if (zeichen == zug.getFeld(i, 2 - i)) { //Prüfe 02, 11, 20 (Steigung)
                        count++;
                    }
                }
                if (2 == count) { //Sind zwei Ecken (Steigung) mit meinem Zeichen belegt?
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public Position gewinnZug() {
        // kann "ich" mit einem Zug gewinnen?
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {              //Iteriere von links nach rechts alle Zeilen und Spalten durch.
                if (isGewinnzug(x, y, zeichen)) {       //Sind zwei Felder belegt und eins leer?
                    return new Position(x, y);          //Merke mir die Koordinaten von der Klasse Position
                }
            }
        }

        // kann mein "Gegner" mit einem Zug gewinnen?
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {
                if (isGewinnzug(x, y, zug.getZeichenOpposite(zeichen))) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    public void legeZufaellig() {
        Position pos = gewinnZug();
        if (pos != null) {                              //Wenn dieser Zug gefunden wurde
            zug.getMatrix()[pos.x][pos.y] = zeichen;
            return;
        }
        int randX;
        int randY;
        do {
            randX = random.nextInt(3);
            randY = random.nextInt(3);
        } while (!zug.isLeer(randX, randY));
        zug.getMatrix()[randX][randY] = zeichen;
    }
}


