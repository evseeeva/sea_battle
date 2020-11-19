package com.company;

import java.util.Objects;

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
    public String print() {
        if (this.getStatus()==Status.empty) return "0";
        else if (this.getStatus()==Status.alive) return "1";

        else return "-1";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }


    @Override
    public String toString() {
       if (this.getStatus()==Status.missed) return ".";
       if (this.getStatus()==Status.alive) return "0";
       if (this.getStatus()==Status.hit) return "x";
        else return "_";
    }

}

