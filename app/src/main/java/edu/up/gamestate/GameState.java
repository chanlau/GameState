package edu.up.gamestate;

import java.util.Arrays;

public class GameState {
    //instance variables
    Card[] discardPile = new Card[100];
    Card[] deck = new Card[100];
    int whoseTurn = 1;

    //to string class
    public void ToString(){
        String discardString = Arrays.toString(discardPile);
        String deckString = Arrays.toString(deck);
        String turnString = Integer.toString(whoseTurn);

    }

    //methods for actions
    public boolean drawCard(){return false;}

    public boolean play(){return false;}

    public boolean trade2(){return false;}

    public boolean trade3(){return false;}

    public boolean trade5(){return false;}

}
