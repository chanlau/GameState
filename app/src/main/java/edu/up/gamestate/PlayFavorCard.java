package edu.up.gamestate;

public class PlayFavorCard extends CardAction {
    private Player target;

    public PlayFavorCard(Player p, Player t){
        super(p);
        this.target = t;
    }

    public Player getTarget(){
        return this.target;
    }
}
