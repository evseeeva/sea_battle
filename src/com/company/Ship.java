package com.company;

import java.util.List;

public class Ship {

    private shipRotation rotation;
    private List<Cell> decks;
    private int size;
    enum shipRotation {
       horizontal,
        vertical;
    }

    public Ship(List<Cell> decks,shipRotation rotation ) {
        this.decks = decks;
        this.size = this.decks.size();
        this.rotation = rotation;
    }

    public int getSize() {
        return size;
    }
  public boolean isKilled(){
        int count=0;
      for (Cell c: getDecks()) {
          if (c.getStatus().equals(Cell.Status.hit))count++;
      }
      if (count==getDecks().size()) return true;
      else
      return false;
  }

    public shipRotation getRotation() {
        return rotation;
    }

    public List<Cell> getDecks() {
        return decks;
    }

}

