package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PlayerGame {
    private ArrayList<Ship> ships;
    private Map<Integer, Map<Integer, Cell>> targetField;
    private Map<Cell, Ship> cellToShip;
    private Map<Integer, Character> coordinates;
    private final static int fieldSize = 10;
    private final static int maxDeck = 4;
    private Stack<Integer> validShipSize;
    public PlayerGame() {
        this.ships = new ArrayList<>();
        this.cellToShip = new HashMap<>();
        this.targetField = new HashMap<>();
        this.coordinates = new HashMap<>();
        this.validShipSize = new Stack<>();
    }

    public Stack<Integer> getValidShipSize() {
        return validShipSize;
    }

    public static int getFieldSize() {
        return fieldSize;
    }

    public static int getMaxDeck() {
        return maxDeck;
    }

    public Map<Integer, Character> getCoordinates() {
        return coordinates;
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
