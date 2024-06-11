package org.example.akteure;

import org.example.spiel.TicTacToe;
import org.example.spiel.zugInterface;

public class Computer implements zugInterface {
    private int runden = 0;
    private Strategie strategie;
    private TicTacToe spiel = new TicTacToe();


    public Computer(Strategie strategie) {
        this.strategie = strategie;
    }

    public TicTacToe getZug() {
        return strategie.getZug();
    }


    @Override
    public void setzeZeichen(int x, int y) {
        System.out.println("Der Gegner macht seinen Zug...");
        if (runden == 0) {
            strategie.spieleInDieEcke();
        } else if (strategie.SpielerHatInDieMitteGelegt() && runden == 1) {
            strategie.spieleZweitesZeichen();
        } else if (strategie.diagonaleGelegt() && runden == 2) {
            strategie.spieleInDieEcke();
        } else if (runden == 3) {
            strategie.spieleLetztenZug();
        } else {
            strategie.legeZufaellig();
        }
        runden++;
    }
}