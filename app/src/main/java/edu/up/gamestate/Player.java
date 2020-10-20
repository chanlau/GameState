package edu.up.gamestate;

public class Player {
    //Player class will signify what
    int playerNum;
    Card[] playerHand = new Card[100];

    public Player(int num){
        this.playerNum = num;
    }

    public boolean checkForExplodingKitten(){
        for(int i = 0; i <= this.playerHand.length; i++){
            if(playerHand[i].getCardType() == 0){
                return true;
            }
        }
        return false;
    }
}
