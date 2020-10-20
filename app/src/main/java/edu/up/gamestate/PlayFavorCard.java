package edu.up.gamestate;

public class PlayFavorCard extends CardAction {
    private Player target;
    private int choice;

    public PlayFavorCard(Player p, Player t, int c){
        super(p);
        this.target = t;
        this.choice = c;
    }

    public Player getTarget(){
        return this.target;
    }
    public int getChoice() {return this.choice;}
}
