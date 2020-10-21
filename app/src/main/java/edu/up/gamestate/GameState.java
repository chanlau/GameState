package edu.up.gamestate;

import android.util.Log;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.sql.Types.NULL;

public class GameState {
    /**
     * External Citation
     * Date: 20 October 2020
     * Problem: General knowledge about array lists, used in several places
     * in the code but would be
     * redundant so just listed here once
     * <p>
     * Resources:
     * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
     * https://www.w3schools.com/java/java_arraylist.asp
     * Solution: Utilized both sources when performing actions related to
     * array lists
     */
    //instance variables
    ArrayList<Card> discardPile;
    ArrayList<Card> deck;
    ArrayList<Player> players;
    int whoseTurn;


    //constructor
    public GameState() {
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
        for (int b = 0; b < gamestate.deck.size(); b++) {
            this.deck.set(b, gamestate.deck.get(b));
        }
        //deep copy of the players array list
        for (int c = 0; c < gamestate.players.size(); c++) {
            this.players.set(c, gamestate.players.get(c));
        }
        //copy of whose turn it is
        this.whoseTurn = gamestate.whoseTurn;
    }


    //method to call when a card is played
    public boolean makeMove(CardAction action) {
        if (this.whoseTurn != action.getPlayer().getPlayerNum()) {
            return false;
        }

        //check which action is being taken
        if (action instanceof DrawCard) {
            return drawCard(action.getPlayer());
        } else if (action instanceof PlayNopeCard) {
            return Nope(action.getPlayer());
        } else if (action instanceof PlayFavorCard) {
            return Favor(action.getPlayer(),
                    ((PlayFavorCard) action).getTarget(),
                    ((PlayFavorCard) action).getChoice());
        } else if (action instanceof PlayAttackCard) {
            return Attack(action.getPlayer());
        } else if (action instanceof PlayShuffleCard) {
            return Shuffle(action.getPlayer());
        } else if (action instanceof PlaySkipCard) {
            return Skip(action.getPlayer());
        } else if (action instanceof Trade2) {
            return trade2(action.getPlayer(), ((Trade2) action).getTarget(),
                    ((Trade2) action).getPosC1(), ((Trade2) action).getPosC2());
        } else if (action instanceof Trade3) {
            return trade3(action.getPlayer(), ((Trade3) action).getTarget(),
                    ((Trade3) action).getPosC1(),
                    ((Trade3) action).getPosC2(),
                    ((Trade3) action).getPosC3(),
                    ((Trade3) action).getTargetValue());
        } else if (action instanceof Trade5) {
            return trade5(action.getPlayer(), ((Trade5) action).getPosC1(),
                    ((Trade5) action).getPosC2(),
                    ((Trade5) action).getPosC3(),
                    ((Trade5) action).getPosC4(), ((Trade5) action).getPosC5(),
                    ((Trade5) action).getTargetValue());
        }
        //error message
        else {
            Log.d("Invalid Action",
                    "Action provided was an invalid action");
        }
        return false;
    }

    //Attack card
    //current player ends their turn without drawing a card and forces the
    //next player to draw two cards before ending their turn
    public boolean Attack(Player p) {
        int card = checkHand(p, 6);
        //move the card into the discard pile
        discardPile.add(p.playerHand.get(card));
        p.playerHand.remove(6);
        //end the turn of the current player and force the next player to
        //draw 2 cards
        nextTurn();
        drawCard(players.get(whoseTurn));
        drawCard(players.get(whoseTurn));
        nextTurn();
        return true;
    }


    //Nope card
    public boolean Nope(Player p) {
        int card = checkHand(p, 11);
        //move the played nope card to the discard pile and remove it from
        //the players hand
        discardPile.add(p.playerHand.get(card));
        p.playerHand.remove(card);
        return false;
    }

    //Favor card
    //current player selects a target player and target player gives current
    //player a card of target players choosing
    public boolean Favor(Player p, Player t, int targCard) {
        int card = checkHand(p, 8);
        //copy selected card from target player to current player
        p.playerHand.add(t.playerHand.get(card));
        //move the played favor card to the discard pile and remove it from
        // the players hand
        discardPile.add(p.playerHand.get(targCard));
        t.playerHand.remove(targCard);
        return true;
    }

    //See the Future card
    //current player looks at the top three cards of the deck
    public boolean SeeTheFuture(Player p) {
        int card = checkHand(p, 10);
        discardPile.add(p.playerHand.get(card));
        p.playerHand.remove(card);
        return true;
    }

    //Shuffle card
    //shuffles the deck randomly
    public boolean Shuffle(Player p) {
        /**
         * External Citation
         * Date: 19 October 2020
         * Problem: Was unsure if there was an easy way to shuffle and array
         * list
         * <p>
         * Resource:
         * https://www.java2novice.com/java-collections-and-util/arraylist
         * /shuffle/
         * Solution: Used the example code to shuffle the deck
         */
        //find position of the shuffle card in players hand
        int position = checkHand(p, 7);
        //add the played shuffle card to the discard pile and remove it from
        //the players hand
        discardPile.add(p.playerHand.get(position));
        p.playerHand.remove(position);
        //shuffle the deck
        Collections.shuffle(deck);

        return true;
    }

    //Skip card
    //current players turn ends without drawing a card
    public boolean Skip(Player p) {
        int card = checkHand(p, 9);
        //finds skip in hand and removes it before incrementing the turn;
        discardPile.add(p.playerHand.get(card));
        players.get(p.getPlayerNum()).playerHand.remove(card);
        //call the nextTurn method to move to the next player
        nextTurn();
        return true;
    }

    //Exploding kitten card
    //if the current player does not have a defuse card, they lose the game,
    //if they do have a defuse card play the defuse card and reshuffle the
    //exploding kitten card back into the deck
    public boolean ExplodingKitten(Player p) {
        boolean trigger = false;
        //check if there is a defuse card in the hand
        for (int i = 0; i < p.playerHand.size(); i++) {
            //reshuffle the exploding kitten card back into the deck
            if (p.playerHand.get(i).getCardType() == 0) {
                deck.add(p.playerHand.get(i));
                Collections.shuffle(deck);
            }
            //if there is a defuse card, play it and move the defuse card to
            // the discard pile and remove it from the players hand, return
            // true to indicate that the bomb has been defused
            if (p.playerHand.get(i).getCardType() == 12) {
                discardPile.add(p.playerHand.get(i));
                p.playerHand.remove(i);
                trigger = true;
            }
        }
        return trigger;
    }


    //to string class
    //@Override
    public void ToString() {
        String discardString = discardPile.toString();
        Log.d("GameState", "Discard Pile: " + discardString);
        String deckString = deck.toString();
        Log.d("GameState", "Deck: " + deckString);
        String turnString = Integer.toString(whoseTurn);
        Log.d("GameState", "Whose Turn it is: " + turnString);
        String PlayerString = players.toString();
        String Player0String = players.get(0).playerHand.toString();
        String Player1String = players.get(1).playerHand.toString();
        String Player2String = players.get(2).playerHand.toString();
        String Player3String = players.get(3).playerHand.toString();

    }

    //methods for actions

    //draw a card and end the turn of the player
    public boolean drawCard(Player player) {
        //checks if deck is empty
        if (deck.get(0) == null) {
            return false;
        }
        //add top card of deck to hand and remove it from deck
        player.playerHand.add(this.deck.get(0));
        this.deck.remove(0);
        return true;
    }

    public boolean trade2(Player play, Player targ, int a, int b) {
        //determine if the two cards are of the same card type
        Card trade1 = play.playerHand.get(a);
        Card trade2 = play.playerHand.get(b);
        if (trade1.getCardType() == trade2.getCardType()) {
            //update the players hand
            play.playerHand.remove(b);
            play.playerHand.remove(a);
            //copy the new card from the target player into the player hand
            Random rand = new Random();
            int random = rand.nextInt(targ.playerHand.size() + 1);
            play.playerHand.add(targ.playerHand.get(random));
            //remove the target player card that was stolen
            targ.playerHand.remove(random);
            return true;
        }

        return false;
    }

    public boolean trade3(Player play, Player targ, int a, int b, int c,
                          int targCard) {
        //determine if the three cards are of the same type
        Card trade1 = play.playerHand.get(a);
        Card trade2 = play.playerHand.get(b);
        Card trade3 = play.playerHand.get(c);
        if (trade1.getCardType() == trade2.getCardType() &&
                trade2.getCardType() == trade3.getCardType()) {
            //update the players hand
            play.playerHand.remove(c);
            play.playerHand.remove(b);
            play.playerHand.remove(a);
            //check to see if the target player has the desired card
            for (int i = 0; i < targ.playerHand.size(); i++) {
                if (targCard == targ.playerHand.get(i).getCardType()) {
                    //add the desired card to the player hand and remove it
                    // from the target player
                    //hand
                    play.playerHand.add(targ.playerHand.get(i));
                    targ.playerHand.remove(i);
                }
            }
            return true;
        }
        return false;
    }

    //Trade 5 cards
    //current player selects 5 different cards and trades them for a card
    //from the discard pile
    public boolean trade5(Player p, int cardPos1, int cardPos2, int cardPos3,
                          int cardPos4, int cardPos5, int target) {
        //determine if the 5 cards are unique
        int comp1 = p.playerHand.get(cardPos1).getCardType();
        int comp2 = p.playerHand.get(cardPos2).getCardType();
        int comp3 = p.playerHand.get(cardPos3).getCardType();
        int comp4 = p.playerHand.get(cardPos4).getCardType();
        int comp5 = p.playerHand.get(cardPos5).getCardType();
        if (comp1 == comp2 || comp1 == comp3 || comp1 == comp4 || comp1 == comp5 ||
                comp2 == comp3 || comp2 == comp4 || comp2 == comp5 ||
                comp3 == comp4 || comp3 == comp5 ||
                comp4 == comp5) {
            //update the players hand
            p.playerHand.remove(cardPos5);
            p.playerHand.remove(cardPos4);
            p.playerHand.remove(cardPos3);
            p.playerHand.remove(cardPos2);
            p.playerHand.remove(cardPos1);
            //copy the desired card to the players hand
            p.playerHand.add(discardPile.get(target));
            //remove the card from the discard pile
            discardPile.remove(target);
        }
        return false;
    }

    //increments turn
    public void nextTurn() {
        this.whoseTurn++;
        while (players.get(whoseTurn).checkForExplodingKitten()) {
            this.whoseTurn++;
        }
    }


    //check for the card
    public int checkHand(Player p, int card) {
        //check to see if the card type exists in the players hand, if it
        // does return the position of the card
        for (int i = 0; i < p.playerHand.size(); i++) {
            if (p.playerHand.get(i).getCardType() == card) {
                return i;
            }
        }
        return NULL;
    }

    //restart the deck
    public void populateDeck() {
        int i;
        int j;
        //puts 4 of each cat card, attack, shuffle, favor, skip cards
        for (i = 1; i <= 9; i++) {
            for (j = 0; j < 4; j++) {
                this.deck.add(new Card(i));
            }
        }
        // puts 5 See the Future and Nope Cards into deck
        for (i = 10; i <= 11; i++) {
            for (j = 0; j < 5; j++) {
                this.deck.add(new Card(i));
            }
        }

    }

    //adds defuse and explode cards to deck
    public void populateDefuseExplode() {
        int i;
        int j;
        //puts 3 Exploding Kittens into deck
        for (i = 0; i < 3; i++) {
            this.deck.add(new Card(0));
        }

        //Puts 2 defuse into deck
        for (i = 0; i < 2; i++) {
            this.deck.add(new Card(12));
        }
    }

    //adds appropriate amt. of cards to all players hands
    public void populateHands() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 7; j++) {
                drawCard(players.get(i));
            }
            players.get(i).playerHand.add(new Card(12));
        }
    }

//sets all players hands to be able to do each action once

    public void makeTestHand() {
        int i, j;
        for (i = 0; i < players.size(); i++) {
            //puts 3 tacocats in hand
            for (j = 0; i < 3; i++) {
                players.get(i).playerHand.add(new Card(1));
            }
            //puts 2 beardcats in hand
            for (j = 0; j < 2; i++) {
                players.get(i).playerHand.add(new Card(2));
            }
            //puts one of every card in hand
            for (j = 1; j <= 12; j++) {
                players.get(i).playerHand.add(new Card(j));
            }
        }

    }

    public void addPlayer(Player p) {
        players.add(p);
    }

}
