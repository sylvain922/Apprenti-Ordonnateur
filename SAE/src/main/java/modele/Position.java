package modele;

public class Position {
    private int chPosX, chPosY;
    private int chNbDepl = 0;

    public Position(int parX, int parY) {
        chPosX = parX;
        chPosY = parY;
    }

    public Position(Position parPos) {
        chPosX = parPos.chPosX;
        chPosY = parPos.chPosY;
    }

    public String toString() {
        return "[" + chPosX + ", " + chPosY + "]";
    }

    public int getPosX() {
        return chPosX;
    }

    public int getPosY() {
        return chPosY;
    }

    public int gerNbDepl() {
        return chNbDepl;
    }

    public void setNbDepl(int parNb) {
        chNbDepl = parNb;
    }

    public void deplUneCase(Position parPos) {
        chNbDepl++;
        if (chPosX < parPos.chPosX) {
            chPosX++;
            return;
        }
        if (chPosX > parPos.chPosX) {
            chPosX--;
            return;
        }
        if (chPosY < parPos.chPosY) {
            chPosY++;
            return;
        }
        if (chPosY > parPos.chPosY) {
            chPosY--;
            return;
        }
    }

    public void setPosition(Position parPos) {
        chPosX = parPos.chPosX;
        chPosY = parPos.chPosY;
    }

    public int distance(Position parPos) {
        return Math.abs(chPosX - parPos.chPosX) + Math.abs(chPosY - parPos.chPosY);
    }

    public boolean estMemePosition(Position parPos) {
        return (parPos.chPosX == chPosX && parPos.chPosY == chPosY);
    }
