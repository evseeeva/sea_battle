package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerGame {
    private ArrayList<Ship> ships;
    private Map<Integer, Map<Integer, Cell>> targetField;
    private Map<Cell, Ship> cellToShip;
    private Map<Integer, Character> coords;

    public PlayerGame() {
        this.ships = new ArrayList<>();
        this.cellToShip = new HashMap<>();
        this.targetField = new HashMap<Integer, Map<Integer, Cell>>();
        this.coords = new HashMap<>();

    }

    public Map<Integer, Character> getCoords() {
        return coords;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Map<Integer, Map<Integer, Cell>> getTargetField() {
        return targetField;
    }

    public Map<Cell, Ship> getCellToShip() {
        return cellToShip;
    }

}
