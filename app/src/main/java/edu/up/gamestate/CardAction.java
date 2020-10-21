/**
 * Date: 10/20/2020
 * Authors: Chandler Lau, Ka'ulu Ng, Samuel Warrick
 * Version: Project #d Final
 */

package edu.up.gamestate;

public class CardAction {
    private Player player;

    public CardAction(Player p){
        this.player = p;
    }

    public Player getPlayer() {
        return this.player;
    }

}
