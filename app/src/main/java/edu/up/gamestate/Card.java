package edu.up.gamestate;

import android.util.Log;

public class Card {
    //create variables for the current player playing the card, the target player of the card
    //if there is one, and the cardType as an int
    private Player currPlayer;
    private Player targPlayer;
    private int cardType;


    Card(int cardType, Player currPlayer, Player targPlayer) {
        this.cardType = cardType;
        this.currPlayer = currPlayer;
        this.targPlayer = targPlayer;
    }

    public Card(Card orig){
        this(orig.getCardType(),orig.getCurrPlayer(), orig.getTargPlayer());
    }

    public Player getCurrPlayer(){
        return currPlayer;
    }

    public Player getTargPlayer(){
        return targPlayer;
    }

    public int getCardType(){
        return cardType;
    }
    
}
