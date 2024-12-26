package model;

public class Key {
    private String touchName ;
    private int line ;
    private int column ;
    private String finger ;
    private boolean isShifted;
    private boolean isAltGr;

    public Key( String touchName , int line , int column , String finger ,boolean isAltGr ) {
        this.touchName = touchName ;
        this.line = line ;
        this.column = column ;
        this.finger = finger ;
        this.isShifted = Character.isUpperCase(touchName.charAt(0));
        this.isAltGr = isAltGr ;
    }

    @Override
    public String toString() {
        return  "Key(" + touchName + " , " + line + " , " + column + " , " + finger + " )";
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
