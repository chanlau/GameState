/**
 * Date: 10/20/2020
 * Authors: Chandler Lau, Ka'ulu Ng, Samuel Warrick
 * Version: Project #d Final
 */

package edu.up.gamestate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class


MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initalizing the runButton and setting it's click listener
        Button runButton = findViewById(R.id.runButton);
        runButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Clearing all current text in the text field

        EditText displayText = findViewById(R.id.displayText);
        displayText.append("BIg boats");

        //Added four players to the game
        Player Player1 = new Player(1, "The Ghost of Tyson");
        Player Player2 = new Player(2, "Ka'ulu");
        Player Player3 = new Player(3, "Sam");
        Player Player4 = new Player(4, "Chandler");

        //Started the first instance of the game
        GameState firstInstance = new GameState();
        firstInstance.populateDeck();
        firstInstance.addPlayer(Player1);
        firstInstance.addPlayer(Player2);
        firstInstance.addPlayer(Player3);
        firstInstance.addPlayer(Player4);
        firstInstance.makeTestHand();
        GameState secondInstance = new GameState(firstInstance);

        CardAction p1_trade2 = new Trade2(Player1, Player2,3, 4);
        if(firstInstance.makeMove(p1_trade2)){
            displayText.append(Player1.getPlayerName() + " traded-in two cards to take a card from " + Player2.getPlayerName());
        }

        CardAction p1_trade3 = new Trade3(Player1, Player3, 0, 1, 2, 12);
        if(firstInstance.makeMove(p1_trade3)){
            displayText.append(Player1 + " traded 3 cards to ask " + Player3.getPlayerName() + " for a defuse card. ");
        }

        CardAction p1_trade5 = new Trade5(Player1, 0, 1, 2, 3, 4, 6);
        if(firstInstance.makeMove(p1_trade5)){
            displayText.append(Player1 + " traded 5 cards to potentially get a defuse card from the discard pile");
        }

        CardAction p1shuffle = new PlayShuffleCard(Player1);
        if(firstInstance.makeMove(p1shuffle)){
            displayText.append(Player1.getPlayerName() + " shuffled the deck. ");
        }

        CardAction p1SeeFuture = new PlayFutureCard(Player1);
        if(firstInstance.makeMove(p1SeeFuture)){
            displayText.append(Player1.getPlayerName() + " saw the top three cards of the deck. ");
        }

        CardAction p1Favor4 = new PlayFavorCard(Player1,Player4,0);
        if(firstInstance.makeMove(p1Favor4)) {
            displayText.append(Player1.getPlayerName() + " took a favor from " + Player4.getPlayerName() + ". ");
        }

        CardAction p1Nope = new PlayNopeCard(Player1);
        if(firstInstance.makeMove(p1Nope)){
            displayText.append(Player1.getPlayerName() + " played a Nope Card. ");
        }

        CardAction p1attack2 = new PlayAttackCard(Player1);
        if(firstInstance.makeMove(p1attack2)){
            displayText.append(Player1.getPlayerName() + " attacked " + Player2.getPlayerName() + ". ");
        }

        CardAction p2skip = new PlaySkipCard(Player2);
        if(firstInstance.makeMove(p2skip)){
            displayText.append(Player2.getPlayerName() + "skipped one turn. ");
        }

        CardAction p2draw = new DrawCard(Player2);
        if(firstInstance.makeMove(p2draw)) {
            displayText.append(Player2.getPlayerName() + " drew a card from the deck. ");
        }

        GameState thirdInstance =  new GameState();
        GameState fourthInstance =  new GameState(thirdInstance);

        displayText.append(secondInstance.toString());
        displayText.append(fourthInstance.toString());












    }
}