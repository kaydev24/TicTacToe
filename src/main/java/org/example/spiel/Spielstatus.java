package org.example.spiel;

public enum Spielstatus {
    MENSCH_GEWINNT("Du hast gewonnen."),
    COMPUTER_GEWINNT("Du hast verloren."),
    UNENTSCHIEDEN("Unentschieden."),
    SPIEL_NICHT_BEENDET("Spiel nicht beendet.");

    private final String spielStatus;

    Spielstatus(String spielStatus) {

        this.spielStatus = spielStatus;
    }

    @Override
    public String toString() {
        return spielStatus;
    }
}

