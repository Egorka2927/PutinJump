package javaCode.model;

public class Putin {
    private double x;
    private double y;
    private boolean alive;
    private boolean sitting;
    private boolean right;
    private boolean left;

    public Putin(int x, int y) {
        this.x = x;
        this.y = y;
        sitting = false;
        right = false;
        left = false;
        alive = true;
    }

    public double getX() {
        return x;
    }

//    ===========================================
//    PUTIN SHOULD HAVE AN ABILITY!!!!!!!!!!!!!!!
//    ===========================================

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isSitting() {
        return sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
