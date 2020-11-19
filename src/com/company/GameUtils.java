package com.company;

public class GameUtils {
    public void printGameMove(PlayerGame fielderPlayer,Cell target){
        char coord = fielderPlayer.getCoordinates().get(target.getX());
        System.out.print(coord +"  "+target.getY()+" --> ");
    }

    public void getTarget(PlayerGame player, Cell c) {
        for (Cell cell:player.getCellToShip().get(c).getDecks()) {
            if (cell.equals(c)) {
                cell.setStatus(Cell.Status.hit);
                break;
            }
        }
        if(player.getCellToShip().get(c).isKilled()) {
            player.getShips().remove(player.getCellToShip().get(c));
            System.out.print(" убит!!");
        }

    }

    public void setHitTarget(PlayerGame player, Cell c) {
        int x=c.getX();
        int y=c.getY();
        player.getTargetField().get(y).get(x).setStatus(Cell.Status.hit);
        System.out.print("ранен");
    }
    public void setMissTarget(PlayerGame player, Cell c) {
        int x=c.getX();
        int y=c.getY();
        player.getTargetField().get(y).get(x).setStatus(Cell.Status.missed);
        System.out.print("мимо");
    }

    public void showField(PlayerGame player){
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < player.getFieldSize(); i++){
            System.out.print( player.getCoordinates().get(i) + "  " );
        }
        System.out.println();
        for (int i = 0; i < player.getFieldSize(); i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < player.getFieldSize(); j++) {
                System.out.print( player.getTargetField().get(i).get(j).toString() + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void clearField(PlayerGame player){
        for (int i = 0; i < player.getFieldSize(); i++) {
            for (int j = 0; j < player.getFieldSize(); j++) {
                player.getTargetField().get(i).put(j, new Cell(j,i));
            }
        }
    }
}
