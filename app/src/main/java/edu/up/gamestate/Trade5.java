package edu.up.gamestate;

public class Trade5 extends CardAction{
    private int PosC1, PosC2, PosC3, PosC4, PosC5, targetCardValue;

    public Trade5(Player p, int c1, int c2, int c3, int c4, int c5) {
        super(p);
        this.PosC1 = c1;
        this.PosC2 = c2;
        this.PosC3 = c3;
        this.PosC4 = c4;
        this.PosC5 = c5;
    }

    public int getPosC1(){return PosC1;}
    public int getPosC2(){return PosC2;}
    public int getPosC3(){return PosC3;}
    public int getPosC4(){return PosC4;}
    public int getPosC5(){return PosC5;}
    public int getTargetValue(){return targetCardValue;}
}
