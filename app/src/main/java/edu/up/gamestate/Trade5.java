package edu.up.gamestate;

public class Trade5 extends CardAction{
    private Card card1, card2, card3, card4, card5, targetCard;

    public Trade5(Player p, Card c1, Card c2, Card c3, Card c4, Card c5) {
        super(p);
        this.card1 = c1;
        this.card2 = c2;
        this.card3 = c3;
        this.card4 = c4;
        this.card5 = c5;
    }

    public Card getCard1(){return card1;}
    public Card getCard2(){return card2;}
    public Card getCard3(){return card3;}
    public Card getCard4(){return card4;}
    public Card getCard5(){return card5;}
    public Card getTargetCard(){return targetCard;}
}
