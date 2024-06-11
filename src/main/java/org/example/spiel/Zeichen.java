package org.example.spiel;

public enum Zeichen {
    X('X', 0),
    O('O', 1),
    I('-', -1);

    private final char darstellung;
    private final int wert;

    Zeichen(char darstellung, int wert) {
        this.darstellung = darstellung;
        this.wert = wert;
    }

    public char getDarstellung() {
        return darstellung;
    }

    public int getWert() {
        return wert;
    }

    public Zeichen getOppositeSpieler() {
        return switch (this) {
            case X -> Zeichen.O;
            case O -> Zeichen.X;
            default -> Zeichen.I;
        };
    }

    public static Zeichen getFromChar(char c) {
        for (Zeichen z : Zeichen.values()) {
            if (z.getDarstellung() == c) return z;
        }
        return null;
    }

}