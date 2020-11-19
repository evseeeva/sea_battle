package com.company;


import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Play {

    public static void main(String[] args) {
        GameService gs = new GameService();
        GameUtils u = new GameUtils();
        PlayerGame Player1 = new PlayerGame();
        System.out.println("\t\tКОРАБЛИ ИГРОКА 1");
        gs.init(Player1);
        PlayerGame Player2 = new PlayerGame();
        System.out.println("\t\tКОРАБЛИ ИГРОКА 2");
        gs.init(Player2);
        int count1 = 1;
        int count2 = 1;
        boolean doHit;
        while (true) {
            doHit = true;
           while(doHit){
               System.out.println("Ход № " + count1+  " игрока 1:");
               doHit=gs.randomTarget(Player1, Player2);
               if (Player2.getShips().isEmpty()) {
                   System.out.print("Выйграл игрок 1");
                   return;
               }
               count1++;
           }
           doHit=true;
            while(doHit){
                System.out.println("Ход № " + count2+  " игрока 2:");
                doHit=gs.randomTarget(Player2, Player1);
                if (Player1.getShips().isEmpty()) {
                    System.out.print("Выйграл игрок 2");
                    return;
                }
                count2++;
            }
        }

    }
}

