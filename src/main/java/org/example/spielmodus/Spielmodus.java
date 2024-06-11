package org.example.spielmodus;

import java.util.List;

public enum Spielmodus {
    GEGNER_VS_SPIELER("Gegner vs Spieler"),
    SPIELER_VS_SPIELER("Spieler vs Spieler"),
    GEGNER_VS_GEGNER("Gegner vs Gegner");

    private final String beschreibung;
    private static List<Spielmodus> liste = List.of(new Spielmodus[]{GEGNER_VS_GEGNER, SPIELER_VS_SPIELER, GEGNER_VS_SPIELER});

    Spielmodus(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String toString() {
        return beschreibung;
    }
}