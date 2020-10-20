package edu.up.gamestate;

public class Trade2 extends CardAction{
    private Player targetPlayer;
    private int PosC1, PosC2;

    public Trade2(Player p, Player t, int c1, int c2){
        super(p);
        this.targetPlayer = t;
        this.PosC1 = c1;
        this.PosC1 = c2;
    }

    //Getter methods for all of the instance variables
    public Player getTarget(){
        return targetPlayer;
    }
    public int getPosC1(){return PosC1;}
    public int getPosC2(){return PosC2;}
}
