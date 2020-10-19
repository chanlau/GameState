package edu.up.gamestate;

public class Trade2 extends CardAction{
    private Player target;

    public Trade2(Player p, Player t){
        super(p);
        this.target = t;
    }

    public Player getTarget(){
        return target;
    }
}
