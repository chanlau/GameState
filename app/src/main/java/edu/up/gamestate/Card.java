package edu.up.gamestate;

public class Card {
    //create variables for the current player playing the card, the target player of the card
    //if there is one, and the cardType as an int
    Player currPlayer;
    Player tarPlayer;
    int cardType;

    Card(int cardType, Player currPlayer, Player tarPlayer) {
        this.cardType = cardType;
        this.currPlayer = currPlayer;
        this.tarPlayer = tarPlayer;
    }
    
}
