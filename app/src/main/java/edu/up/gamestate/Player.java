package edu.up.gamestate;

import java.util.ArrayList;

public class Player {
    //Player class will signify what
    int playerNum;
    String playerName;
    ArrayList<Card> playerHand;

    public Player(int num, String name){
        this.playerNum = num;
        this.playerName = name;
        this.playerHand = new ArrayList<Card>();
    }

    public boolean checkForExplodingKitten(){
        for(int i = 0; i <= this.playerHand.size(); i++){
            if(playerHand.get(i).getCardType() == 0){
                return true;
            }
        }
        return false;
    }

    public int getPlayerNum(){
        return this.playerNum;
    }
    public String getPlayerName() {return this.playerName;}
    public ArrayList<Card> getPlayerHand() {return this.playerHand;}
}
