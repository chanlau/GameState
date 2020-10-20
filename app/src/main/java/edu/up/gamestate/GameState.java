package edu.up.gamestate;

import android.util.Log;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameState {

    //instance variables
    GameState previous = null;
    ArrayList<Card> discardPile;
    ArrayList<Card> deck;
    ArrayList<Player> players;
    int whoseTurn;


    //constructor
    public GameState(){
        this.discardPile = new ArrayList<Card>();
        this.deck = new ArrayList<Card>();
        this.players = new ArrayList<Player>();
        this.whoseTurn = 1;
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
        for (int a = 0; a < gamestate.discardPile.size(); a++) {
            this.discardPile.set(a, gamestate.discardPile.get(a));
        }
        //deep copy of the gamestate deck
        for (int a = 0; a < gamestate.deck.size(); a++) {
            this.deck.set(a, gamestate.deck.get(a));
        }
        //copy of whose turn it is
        this.whoseTurn = gamestate.whoseTurn;
    }


    //method to call when a card is played




    //to string class
    //@Override
    public void ToString(){
        String discardString = discardPile.toString();
        Log.d("GameState", "Discard Pile: " + discardString );
        String deckString = deck.toString();
        Log.d("GameState", "Deck: " + deckString );
        String turnString = Integer.toString(whoseTurn);
        Log.d("GameState", "Whose Turn it is: " + turnString );

    }

    //methods for actions



    //restart the deck
    public void populateDeck(){

    }

}
