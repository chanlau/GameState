package edu.up.gamestate;

public class PlayAttackCard extends CardAction {
    private Player target;

    public PlayAttackCard(Player p, Player t) {
        super(p);
        this.target = t;
    }

    public Player getTarget(){
        return this.target;
    }
}
