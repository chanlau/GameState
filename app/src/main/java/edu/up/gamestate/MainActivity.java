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
        displayText.setText("");

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

        CardAction p1attackp2 = new PlayAttackCard(Player1);
        if(firstInstance.makeMove(p1attackp2)){
            displayText.append(Player1.getPlayerName() + " attacked " + Player2.getPlayerName() + ". ");
        }

        CardAction p3Shuffle = new PlayShuffleCard(Player3);
        if(firstInstance.makeMove(p3Shuffle)) {
            displayText.append(Player3.getPlayerName() + " shuffled the deck. ");
        }

        CardAction p3draw = new DrawCard(Player3);
        if(firstInstance.makeMove(p3draw)) {
            displayText.append(Player3.getPlayerName() + " drew a card from the deck. ");
        }

        CardAction p4Favor1 = new PlayFavorCard(Player4,Player1,0);
        if(firstInstance.makeMove(p4Favor1)) {
            displayText.append(Player4.getPlayerName() + " took a favor from " + Player1.getPlayerName() + ". ");
        }






    }
}