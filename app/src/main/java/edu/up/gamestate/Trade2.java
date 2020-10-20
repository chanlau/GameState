package edu.up.gamestate;

public class Trade2 extends CardAction{
    private Player targetPlayer;
    private Card card1, card2;

    public Trade2(Player p, Player t, Card c1, Card c2){
        super(p);
        this.targetPlayer = t;
        this.card1 = c1;
        this.card2 = c2;
    }

    //Getter methods for all of the instance variables
    public Player getTarget(){
        return targetPlayer;
    }
    public Card getCard1(){return card1;}
    public Card getCard2(){return card2;}
}
