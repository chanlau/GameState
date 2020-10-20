package edu.up.gamestate;

public class Trade3 extends CardAction{
    private Player targetPlayer;
    private Card card1, card2, card3, targetCard;

    public Trade3(Player p, Player t, Card c1, Card c2, Card c3, Card t_card){
        super(p);
        this.targetPlayer = t;
        this.card1 = c1;
        this.card2 = c2;
        this.card3 = c3;
        this.targetCard = t_card;
    }

    //Getter methods for all of the instance variables
    public Player getTarget(){
        return targetPlayer;
    }
    public Card getCard1(){return card1;}
    public Card getCard2(){return card2;}
    public Card getCard3(){return card3;}
    public Card getTargetCard(){return targetCard;}

}
