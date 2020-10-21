package com.company;

public class Cell {
    private int x;
    private int y;
    private Status status;

    enum Status {
        hit,
        alive,
        empty,
        missed;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.empty;
    }

    public Cell(int x, int y, Status s) {
        this.x = x;
        this.y = y;
        this.status = s;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}

