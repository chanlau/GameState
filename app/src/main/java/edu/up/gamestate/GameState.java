package edu.up.gamestate;

import android.util.Log;

import java.util.Arrays;

public class GameState {
    //instance variables
    GameState previous = null;
    Card[] discardPile;
    Card[] deck;
    Player[] players;
    int whoseTurn;


    //constructor
    public GameState(){
        this.discardPile = new Card[100];
        this.deck = new Card[100];
        this.players = new Player[5];
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


    //method to call when a card is played
    public boolean makeMove(CardAction action) {
        if(this.whoseTurn != action.getPlayer().getPlayerNum()){
            return false;
        }

        this.previous = this;

        //check which action is being taken
        if(action instanceof DrawCard){
            return drawCard(action.getPlayer());
        }
        else if(action instanceof PlayNopeCard){
            return Nope();
        }
        else if(action instanceof PlayFavorCard){
            return Favor();
        }
        else if(action instanceof PlayAttackCard) {
            return Attack();
        }
        else if(action instanceof PlayShuffleCard){
            return Shuffle();
        }
        else if(action instanceof Trade2){
            return trade2();
        }
        else if(action instanceof Trade3){
            return trade3();
        }
        else if(action instanceof Trade5){
            return trade5();
        }
        else{
            Log.d("Invalid Action", "Action provided was an invalid action");
        }

        return false;
    }

    //Attack card action
    public boolean Attack() {
        nextTurn();
        drawCard(players[whoseTurn]);
        return drawCard(players[whoseTurn]);
    }


    //Nope card
    public boolean Nope() {
        return false;
    }

    public boolean Favor(){
        return false;
    }

    //See the Future card
    public boolean SeeTheFuture() {
        return false;
    }

    //Shuffle card
    public boolean Shuffle() {
        return false;
    }

    //Skip card
    public boolean Skip() {
        nextTurn();
        return true;
    }


    //to string class
    //@Override
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
        //add the card to the players hand
        for (int i = 0; i < player.playerHand.length; i++) {
            if (player.playerHand[i] == null) {
                player.playerHand[i] = this.deck[0];

                //copy the deck except shift every card left by 1 to remove the card that was drawn
                int a = 1;
                for (int b = 0; b < this.deck.length; b++) {
                    this.deck[b] = this.deck[a];
                    a++;
                }

                //return true if the card was drawn and removed from the deck
                return true;
            }
        }
        return false;
    }

    public boolean trade2(){return false;}

    public boolean trade3(){return false;}

    public boolean trade5(){return false;}

    //increments turn
    public void nextTurn(){
        this.whoseTurn++;
        while(players[whoseTurn].checkForExplodingKitten() == true){
            this.whoseTurn++;
        }
    }

    //restart the deck
    public void populateDeck(){

    }

}
