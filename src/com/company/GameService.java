package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameService {
    public void init(PlayerGame pg) {
        char coord = 'A';
        for (int i = 0; i < 10; i++) {
            pg.getCoords().put(i, (char) (coord + i));
        }
        for (int i = 0; i < 10; i++) {
            pg.getTargetField().put(i, new HashMap<Integer, Cell>());
            for (int j = 0; j < 10; j++) {
                pg.getTargetField().get(i).put(j, new Cell(j, i));
            }
            genShips(pg);
        }
    }

    public void genShips(PlayerGame pg) {
        int n = 0;
        Random r = new Random();
        List<Cell> decks = new ArrayList<>();
        while (n < 10) {
            Ship ship = buildShip(r.nextInt(10), r.nextInt(10), r.nextInt(4) + 1, r.nextInt(2));
            if (checkAddShip(pg, ship)) {
                for (int i = 0; i < ship.getDecks().size(); i++) {
                    int x = ship.getDecks().get(i).getX();
                    int y = ship.getDecks().get(i).getY();
                    pg.getTargetField().get(x).put(y, ship.getDecks().get(i));
                    pg.getTargetField().get(x).get(y).setStatus(Cell.Status.alive);
                    pg.getCellToShip().put(ship.getDecks().get(i), ship);
                }
                pg.getShips().add(ship);
                n++;
            }
        }
    }

    public boolean checkCell(PlayerGame pg, int x, int y, Ship s) {
        if (!pg.getTargetField().get(x).get(y).getStatus().equals(Cell.Status.empty)) {
            return false;
        }
        return true;
    }

    public boolean checkAddShip(PlayerGame pg, Ship s) {
        for (Cell c : s.getDecks()) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!checkCell(pg, x + i, y + j, s)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Ship buildShip(int x, int y, int deck, int rotation) {
        List<Cell> decks = new ArrayList<>();
        Cell c;
        for (int i = 0; i < deck; i++) {
            if (rotation == 0) {
                c = new Cell(x + i, y);
            } else {
                c = new Cell(x, y + i);
            }
            decks.add(c);
        }
        return new Ship(decks);
    }

}

