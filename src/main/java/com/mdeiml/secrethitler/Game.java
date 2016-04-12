package com.mdeiml.secrethitler;

import java.util.ArrayList;
import java.io.*;

public class Game implements Serializable {

    public static final int CHOOSE_CHANCELLOR = 0;
    public static final int ELECT_CHANCELLOR = 1;
    public static final int PRESIDENT_POLITICS = 2;
    public static final int CHANCELLOR_POLITICS = 3;
    public static final int SEARCH = 4;
    public static final int KILL = 5;
    public static final int NEXT_PRESIDENT = 6;

    private int numPlayers;
    private char[] playerRole;
    private String[] names;
    private int fascistPolitics;
    private int liberalPolitics;
    private ArrayList<Boolean> stack;
    private int president;
    private int chancellor;
    private int lastChancellor;
    private boolean[] playerDead;

    private int gameState;

    public Game(String[] players) {
        this.names = players;
        this.numPlayers = players.length;
        this.fascistPolitics = 0;
        this.liberalPolitics = 0;
        this.president = (int)(Math.random() * numPlayers);
        this.lastChancellor = -1;
        this.gameState = CHOOSE_CHANCELLOR;
        this.playerDead = new boolean[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
            playerDead[i] = false;
        }
        playerRole = new char[numPlayers];
        int f = 3; //TODO: right amout of fascists - 1 hitler
        int h = 1;
        for(int i = 0; i < numPlayers; i++) {
            double random = Math.random();
            if(random < (double)h/(double)(numPlayers-i)) {
                playerRole[i] = 'h';
                h--;
            }else if(random < (double)(f+h)/(double)(numPlayers-i)) {
                playerRole[i] = 'f';
                f--;
            }else {
                playerRole[i] = 'l';
            }
        }
        stack = new ArrayList<Boolean>();
        newStack();
    }

    public void newStack() {
        stack.clear();
        int fp = 11;
        int lp = 6;
        int totalP = fp+lp;
        for(int i = 0; i < totalP; i++) {
            if(Math.random() < (double)fp/(double)(totalP-i)) {
                stack.add(false);
                fp--;
            }else {
                stack.add(true);
                lp--;
            }
        }
    }

    public String[] getElectableChancellors() {
        int sum = 0;
        for(int i = 0; i < numPlayers; i++) {
            if(i != lastChancellor && !playerDead[i]) {
                sum++;
            }
        }
        String[] res = new String[sum];
        int index = 0;
        for(int i = 0; i < numPlayers; i++) {
            if(i != lastChancellor && !playerDead[i]) {
                res[index] = names[i];
                index++;
            }
        }
        return res;
    }

    public boolean setChancellor(String name) {
        if(gameState != CHOOSE_CHANCELLOR)
            return false;
        int index = -1;
        for(int i = 0; i < numPlayers; i++) {
            if(names[i].equals(name)) {
                index = i;
                break;
            }
        }
        if(lastChancellor == index || playerDead[index]) {
            return false;
        }
        chancellor = index;
        gameState = ELECT_CHANCELLOR;
        return true;
    }

    public boolean electChancellor(boolean elected) {
        if(gameState != ELECT_CHANCELLOR)
            return false;

        if(elected) {
            gameState = PRESIDENT_POLITICS;
        }else {
            nextPresident();
            gameState = CHOOSE_CHANCELLOR;
        }
        return true;
    }

    public boolean presidentPolitics(int index) {
        if(gameState != PRESIDENT_POLITICS || index < 0 || index >= 3)
            return false;

        stack.remove(index);
        gameState = CHANCELLOR_POLITICS;
        return true;
    }

    public boolean chancellorPolitics(int index) {
        if(gameState != CHANCELLOR_POLITICS || index < 0 || index >= 2)
            return false;

        boolean politic = stack.get(index);
        stack.remove(1);
        stack.remove(0);
        if(politic) {
            liberalPolitics++;
            nextPresident();
            gameState = CHOOSE_CHANCELLOR;
        }else {
            fascistPolitics++;
            nextPresident();
            gameState = CHOOSE_CHANCELLOR; //TODO: extra actions
        }
        return true;
    }

    public Boolean won() {
        if(gameState != ELECT_CHANCELLOR && playerRole[chancellor] == 'h') {
            return false;
        }
        if(fascistPolitics >= 6) {
            return false;
        }
        if(liberalPolitics >= 5) {
            return true;
        }
        boolean hAlive = false;
        for(int i = 0; i < numPlayers; i++) {
            if(playerRole[i] == 'h') {
                if(!playerDead[i]) {
                    hAlive = true;
                }
                break;
            }
        }
        if(!hAlive) {
            return true;
        }
        return null;
    }

    private void nextPresident() {
        int p = president;
        while(playerDead[p]) {
            p = (p + 1) % numPlayers;
        }
    }

    public String getPresident() {
        return names[president];
    }

    public String getChancellor() {
        return names[chancellor];
    }

    public boolean[] getTopStack(int numCards) {
        if(stack.size() < numCards)
            newStack(); //TODO

        boolean[] res = new boolean[numCards];
        for(int i = 0; i < numCards; i++) {
            res[i] = stack.get(i);
        }
        return res;
    }

    public int getGameState() {
        return gameState;
    }

}
