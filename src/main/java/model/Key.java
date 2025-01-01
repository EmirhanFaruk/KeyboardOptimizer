package model;

public class Key {
    private String touchName ;
    private int line ;
    private int column ;
    private String finger ;
    private boolean rightHand;
    private boolean isShifted;
    private boolean isAltGr;

    public Key( String touchName , int line , int column , String finger , boolean rightHand , boolean isShifted , boolean isAltGr ) {
        this.touchName = touchName ;
        this.line = line ;
        this.column = column ;
        this.finger = finger ;
        this.rightHand = rightHand ;
        this.isShifted = isShifted ;
        this.isAltGr = isAltGr ;
    }

    @Override
    public String toString() {
        String res = "Key(" + touchName + " , " + line + " , " + column + " , " + finger + ", ";
        if ( rightHand )
        {
            res += "rightHand, ";
        }
        else
        {
            res += "leftHand, ";
        }

        if ( isShifted )
        {
            res += "isShifted, ";
        }
        else
        {
            res += "notShifted, ";
        }

        if (isAltGr)
        {
            res += "isAltGr )";
        }
        else
        {
            res += "notAltGr )";
        }

        return  res;
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


    // Getters et setters
    public String getTouchName() {
        return touchName ;
    }

    public int getRangee() {
        return line ;
    }

    public int getColumn() {
        return column ;
    }

    public String getFinger() {
        return finger ;
    }

    public boolean isShifted() {
        return isShifted;
    }

    public boolean isAltGr() {
        return isAltGr;
    }

    public boolean isRightHand() {
        return rightHand;
    }

    public void setRangee(int rangee) {
        this.line = rangee ;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
