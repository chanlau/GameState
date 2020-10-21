package edu.up.gamestate;

import android.util.Log;

import java.util.Collections;
import java.util.Random;

public class LocalGameState {

    private GameState officialState;

    public LocalGameState() {
        officialState  = new GameState();
    }

    //method to call when a card is played
    public boolean makeMove(CardAction action) {
        if(officialState.whoseTurn != action.getPlayer().getPlayerNum()){
            return false;
        }

        officialState.previous = officialState;

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
        else if(action instanceof PlaySkipCard){
            return Skip();
        }
        else if(action instanceof Trade2){
            return trade2(action.getPlayer(), ((Trade2) action).getTarget(),
                    ((Trade2) action).getPosC1(), ((Trade2) action).getPosC2());
        }
        else if(action instanceof Trade3){
            return trade3(action.getPlayer(), ((Trade3) action).getTarget(), ((Trade3) action).getPosC1(),
                    ((Trade3) action).getPosC2(), ((Trade3) action).getPosC3(), ((Trade3) action).getTargetValue());
        }
        else if(action instanceof Trade5){
            return trade5(action.getPlayer(), ((Trade5) action).getPosC1(), ((Trade5) action).getPosC2(),
                    ((Trade5) action).getPosC3(), ((Trade5) action).getPosC4(), ((Trade5) action).getPosC5(),
                    ((Trade5) action).getTargetValue());
        }
        else{
            Log.d("Invalid Action", "Action provided was an invalid action");
        }

        return false;
    }

    //Attack card action
    public boolean Attack() {
        nextTurn();
        drawCard(officialState.players.get(officialState.whoseTurn));
        return drawCard(officialState.players.get(officialState.whoseTurn));
    }


    //Nope card
    public boolean Nope() {
        return false;
    }

    //current player selects a target player and target player gives current player a card of target
    //players choosing
    public boolean Favor(Player p, Player t, int cardPos) {
        //copy card from target player to current player
        p.playerHand.add(t.playerHand.get(cardPos));
        //remove the card form the target player hand
        t.playerHand.remove(cardPos);
        return true;
    }

    //See the Future card, display the top 3 cards of the deck
    public boolean SeeTheFuture() {
        return true;
    }

    //Shuffle card, shuffle the deck randomly
    public boolean Shuffle() {
        /**
         * External Citation
         * Date: 19 October 2020
         * Problem: Was unsure if there was an easy way to shuffle and array list
         * <p>
         * Resource:
         * https://www.java2novice.com/java-collections-and-util/arraylist/shuffle/
         * Solution: Used the example code to shuffle the deck
         */
        //shuffle the deck
        Collections.shuffle(officialState.deck);
        return false;
    }

    //Skip card
    public boolean Skip() {
        //call the nextTurn method to move to the next player
        nextTurn();
        return true;
    }

    //draw a card and end the turn of the player
    public boolean drawCard(Player player){
        //add the card to the players hand
        for (int i = 0; i < player.playerHand.size(); i++) {
            if (player.playerHand.get(i) == null) {
                player.playerHand.set(i, officialState.deck.get(0));

                //copy the deck except shift every card left by 1 to remove the card that was drawn
                int a = 1;
                for (int b = 0; b < officialState.deck.size(); b++) {
                    officialState.deck.set(b, officialState.deck.get(a));
                    a++;
                }

                //return true if the card was drawn and removed from the deck
                return true;
            }
        }
        return false;
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

    public boolean trade3(Player play, Player targ, int a, int b, int c, int targCard) {
        //determine if the three cards are of the same type
        Card trade1 = play.playerHand.get(a);
        Card trade2 = play.playerHand.get(b);
        Card trade3 = play.playerHand.get(c);
        if (trade1.getCardType() == trade2.getCardType() && trade2.getCardType() == trade3.getCardType()) {
            //update the players hand
            play.playerHand.remove(c);
            play.playerHand.remove(b);
            play.playerHand.remove(a);
            //check to see if the target player has the desired card
            for (int i = 0; i < targ.playerHand.size(); i++) {
                if (targCard == targ.playerHand.get(i).getCardType()) {
                    //add the desired card to the player hand and remove it from the target player
                    //hand
                    play.playerHand.add(targ.playerHand.get(i));
                    targ.playerHand.remove(i);
                }
            }
            return true;
        }
        return false;
    }

    public boolean trade5(Player p, int cardPos1, int cardPos2, int cardPos3, int cardPos4, int cardPos5, int target){
        //determine if the 5 cards are unique
        int comp1 = p.playerHand.get(cardPos1).getCardType();
        int comp2 = p.playerHand.get(cardPos2).getCardType();
        int comp3 = p.playerHand.get(cardPos3).getCardType();
        int comp4 = p.playerHand.get(cardPos4).getCardType();
        int comp5 = p.playerHand.get(cardPos5).getCardType();
        if(comp1 == comp2 || comp1 == comp3 || comp1 == comp4 || comp1 == comp5 ||
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
            p.playerHand.add(officialState.discardPile.get(target));
            //remove the card from the discard pile
            officialState.discardPile.remove(target);
            return true;
        }
        return false;
    }

    //increments turn
    public void nextTurn(){
        officialState.whoseTurn++;
        while(officialState.players.get(officialState.whoseTurn).checkForExplodingKitten() == true){
            officialState.whoseTurn++;
        }
    }

}
