package edu.up.gamestate;

public class Trade3 extends CardAction{
    private Player target;

    public Trade3(Player p, Player t){
        super(p);
        this.target = t;
    }

    public Player getTarget(){
        return target;
    }
}
