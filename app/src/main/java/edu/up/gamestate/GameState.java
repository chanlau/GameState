package edu.up.gamestate;

import android.util.Log;

import java.util.Arrays;

public class GameState {
    //instance variables
    Card[] discardPile;
    Card[] deck;
    int whoseTurn;

    //constructor
    public GameState(){
        Card[] discardPile = new Card[100];
        Card[] deck = new Card[100];
        int whoseTurn = 1;
    }

    //constructor to copy the given gamestate
    public GameState(GameState gamestate) {
        /**
         * External Citation
         * Date: 8 October 2020
         * Problem: Was not certain on the easiest way to copy an array
         * <p>
         * Resource:
         * https://www.geeksforgeeks.org/array-copy-in-java/
         * Solution: Reaffirmed that the best way is to use a for loop
         */
        //deep copy of the gamestate discardPile
        for (int a = 0; a < gamestate.discardPile.length; a++) {
            this.discardPile[a] = gamestate.discardPile[a];
        }
        //deep copy of the gamestate deck
        for (int a = 0; a < gamestate.deck.length; a++) {
            this.deck[a] = gamestate.deck[a];
        }
        //copy of whose turn it is
        this.whoseTurn = gamestate.whoseTurn;
    }

    //to string class
    @Override
    public void ToString(){
        String discardString = Arrays.toString(discardPile);
        Log.d("GameState", "Discard Pile: " + discardString );
        String deckString = Arrays.toString(deck);
        Log.d("GameState", "Deck: " + deckString );
        String turnString = Integer.toString(whoseTurn);
        Log.d("GameState", "Whose Turn it is: " + turnString );

    }

    //methods for actions

    //draw a card and end the turn of the player
    public boolean drawCard(Player player){
        boolean addCard = false;
        //add the card to the players hand
        for (int i = 0; i < player.playerHand.length; i++) {
            if (player.playerHand[i] != null) {
                player.playerHand[i] = this.deck[0];
                addCard = true;
                this.whoseTurn++;
            }
        }

        //copy the deck except shift every card left by 1 to remove the card that was drawn
        if (addCard) {
            int a = 1;
            for (int b = 0; b < this.deck.length; b++) {
                this.deck[b] = this.deck[a];
                a++;
            }
        }
        return addCard;
    }

    //play a selected card
    public boolean play(Player player, int a){
        player.playerHand[a]
        return false;
    }

    public boolean trade2(){return false;}

    public boolean trade3(){return false;}

    public boolean trade5(){return false;}

}
