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
}
