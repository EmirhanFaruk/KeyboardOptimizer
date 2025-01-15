package model.keyboard;

public record Key(String touchName,
                  int line, int column,
                  String finger,
                  boolean rightHand,
                  boolean isShifted,
                  boolean isAltGr) {

    @Override
    public String toString() {
        String res = "Key(" + touchName + " , " + line + " , " + column + " , " + finger + ", ";
        if (rightHand) {
            res += "rightHand, ";
        } else {
            res += "leftHand, ";
        }

        if (isShifted) {
            res += "isShifted, ";
        } else {
            res += "notShifted, ";
        }

        if (isAltGr) {
            res += "isAltGr )";
        } else {
            res += "notAltGr )";
        }

        return res;
    }

    public double getEffort() {
        double effort = 1.0; // Effort de base

        // Augmenter l'effort pour les rangées supérieures ou inférieures
        if (line == 0 || line == 3) {
            effort += 1.0;
        }

        // Augmenter l'effort pour les colonnes éloignées du centre
        if (column < 2 || column > 7) {
            effort += 0.5;
        }

        // Augmenter l'effort si la touche nécessite Shift ou AltGr
        if (isShifted || isAltGr) {
            effort += 0.8;
        }

        return effort;
    }

    public int ordinal() {
        switch (finger) {
            case "Pinky" :
                return 0 ;
            case "Ring Finger" :
                return 1 ;
            case "Middle Finger" :
                return 2 ;
            case "Index Finger" :
                return 3 ;
            case "Thumb":
                return 4 ;
            default:
                throw new IllegalArgumentException("Finger non reconnu : " + finger);
        }
    }

    public int getRangee() {
        return line;
    }
}
