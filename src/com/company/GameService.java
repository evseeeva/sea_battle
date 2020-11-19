package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameService {
private GameUtils gameUtils= new GameUtils();

    public void init(PlayerGame player) {
        int n =  player.getFieldSize();
        for (int i = 0; i < n; i++) {
            player.getTargetField().put(i, new HashMap<Integer, Cell>());
            for (int j = 0; j < n; j++) {
                player.getTargetField().get(i).put(j, new Cell(i,j));
            }
        }
        char coord ='A';
     for(int i = 0; i < player.getFieldSize(); i++){
         player.getCoordinates().put(i, coord);
         coord++;
     }
     for(int k=0; k<player.getMaxDeck(); k++){
         for (int i=0; i<player.getMaxDeck()-k; i++)
             player.getValidShipSize().push(k+1);
     }
        genShips(player);
        gameUtils.showField(player);
        gameUtils.clearField(player);
    }

    public void genShips(PlayerGame pg) {
        int n = pg.getFieldSize();
        int i=0;
        Random r = new Random();
        while (i < n) {
            int decks=pg.getValidShipSize().peek();
            Ship ship = buildShip(r.nextInt(n), r.nextInt(n), decks, r.nextInt(2));
           if (checkAddShip(pg, ship)) {
               pg.getShips().add(ship);
               pg.getValidShipSize().pop();
               i++;
                for (Cell c: ship.getDecks()) {
                    int x = c.getX();
                    int y = c.getY();
                    pg.getTargetField().get(y).put(x,c);
                    pg.getCellToShip().put(c, ship);
               }
            }
        }
    }

    public boolean checkCell(PlayerGame pg, Cell c) {
        int x = c.getX();
        int y = c.getY();
        if (!pg.getTargetField().get(y).get(x).getStatus().equals(Cell.Status.empty)) {
            return false;
        }
        return true;
    }


    public boolean checkAddShip(PlayerGame pg, Ship s) {
        Cell firstCell = s.getDecks().get(0);
        if (s.getRotation().equals(Ship.shipRotation.horizontal) &&(firstCell.getX()+s.getSize()-1<0 || firstCell.getX()+s.getSize()-1>9)){
            return false;
        }
        if (s.getRotation().equals(Ship.shipRotation.vertical) && firstCell.getY()+s.getSize()-1>9 || firstCell.getY()+s.getSize()-1<0 ){
            return false;
        }
        for (Cell c : s.getDecks()) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                        if(y + i < 0 || y + i > 9 || x+j<0 ||  x+j>9 ) continue;
                        if (!checkCell(pg, new Cell(x+j, y+i))) {
                            return false;
                        }

                }
            }
        }
        return true;
    }


    public Ship buildShip(int x, int y, int size, int rotation) {
        List<Cell> decks = new ArrayList<>();
        Cell c;
        for (int i = 0; i < size; i++) {
            if (rotation == 0) {
                c = new Cell(x + i, y, Cell.Status.alive);
            } else {
                c = new Cell(x, y + i, Cell.Status.alive);
            }
            decks.add(c);

        }
        if(rotation==0) return new Ship(decks, Ship.shipRotation.horizontal);
         else return new Ship(decks, Ship.shipRotation.vertical);

    }

    public boolean randomTarget(PlayerGame attackPlayer,PlayerGame fielderPlayer) {
        Random r = new Random();
        int x, y;
        int n = fielderPlayer.getFieldSize();
        boolean hit = false;
        while(true){
        x = r.nextInt(n);
        y = r.nextInt(n);
        if(checkCell(attackPlayer,new Cell(x,y))) break;
        }
        Cell targetCell = new Cell(x, y);
            gameUtils.printGameMove(fielderPlayer, targetCell);
            for (Cell c : fielderPlayer.getCellToShip().keySet()) {
                if (c.equals(targetCell)) {
                   gameUtils.setHitTarget(attackPlayer, c);
                   gameUtils.getTarget(fielderPlayer, c);
                    if(!fielderPlayer.getShips().contains(fielderPlayer.getCellToShip().get(c))){
                        setBannedCells(attackPlayer, fielderPlayer.getCellToShip().get(c));
                    }
                    hit = true;
                    break;
                }
            }
            if (!hit) gameUtils.setMissTarget(attackPlayer, targetCell);
            gameUtils.showField(attackPlayer);
            return hit;
    }

    public Cell nearTarget(PlayerGame attackPlayer, PlayerGame fielderPlayer, Cell hitCell) {
        int x = hitCell.getX();
        int y = hitCell.getY();
        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                if (y + i < 0 || y + i > 9 || x + j < 0 || x + j > 9) continue;
                if (checkCell(attackPlayer, new Cell(x+j, y+i))) return new Cell(x+j, y+i);
                else continue;
            }
        }
         return null;
    }
    public void setBannedCells(PlayerGame player, Ship s) {
        for (Cell c : s.getDecks()) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (y + i < 0 || y + i > 9 || x + j < 0 || x + j > 9) continue;
                    if(checkCell(player, new Cell(x+j, y+i)))  player.getTargetField().get(y+i).get(x+j).setStatus(Cell.Status.missed);
                }
            }
        }
    }
}

