package com.company;

import java.util.List;

public class Ship {
    private shipStatus status;
    private List<Cell> decks;

    enum shipStatus {
        alive,
        injured,
        killed;
    }

    public Ship(List<Cell> decks) {
        this.decks = decks;
        this.status = shipStatus.alive;
    }

    public shipStatus getStatus() {
        return status;
    }

    public void setStatus(shipStatus status) {
        this.status = status;
    }

    public List<Cell> getDecks() {
        return decks;
    }

}

