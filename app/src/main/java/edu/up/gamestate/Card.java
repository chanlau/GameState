package edu.up.gamestate;

public class Card {
    //create variables for the current player playing the card, the target player of the card
    //if there is one, and the cardType as an int
    private int cardType;

    public Card(int cardType) {
        this.cardType = cardType;
    }

    public Card(Card orig) {
        this(orig.getCardType());
    }

    public int getCardType() {
        return cardType;
    }
}

