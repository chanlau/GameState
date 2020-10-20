package edu.up.gamestate;

import android.util.Log;

import java.util.Arrays;
import java.util.ArrayList;

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
            this.discardPile.get(a) = gamestate.discardPile.get(a);
        }
        //deep copy of the gamestate deck
        for (int a = 0; a < gamestate.deck.size(); a++) {
            this.deck.get(a) = gamestate.deck.get(a);
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
            Nope();
        }
        else if(action instanceof PlayFavorCard){
            Favor();
        }
        else if(action instanceof PlayAttackCard) {
            return Attack();
        }
        else if(action instanceof PlayShuffleCard){
            Shuffle();
        }

        return false;
    }

    //Attack card action
    public boolean Attack() {
        nextTurn();
        drawCard(players.get(whoseTurn));
        return drawCard(players.get(whoseTurn));
    }


    //Nope card
    public void Nope() {

    }

    public void Favor(){

    }

    //See the Future card
    public void SeeTheFuture() {

    }

    //Shuffle card
    public void Shuffle() {

    }

    //Skip card
    public void Skip() {
        nextTurn();
    }


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

    //draw a card and end the turn of the player
    public boolean drawCard(Player player){
        //add the card to the players hand
        for (int i = 0; i < player.playerHand.length; i++) {
            if (player.playerHand[i] == null) {
                player.playerHand[i] = this.deck.get(0);

                int a = 1;
                for (int b = 0; b < this.deck.size(); b++) {
                    this.deck.get(b) = this.deck.get(a);
                    a++;
                }

                //return true if the card was drawn and removed from the deck
                return true;
            }
        }
        return false;
        //copy the deck except shift every card left by 1 to remove the card that was drawn

    }

    //play a selected card
    public boolean play(Player player, int a){
        return false;
    }

    public boolean trade2(){return false;}

    public boolean trade3(){return false;}

    public boolean trade5(){return false;}

    //increments turn
    public void nextTurn(){
        this.whoseTurn++;
        while(players.get(whoseTurn).checkForExplodingKitten() == true){
            this.whoseTurn++;
        }
    }

    //restart the deck
    public void populateDeck(){

    }

}
