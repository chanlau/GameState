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

        Player Player1 = new Player(1, "Tyson");
        Player Player2 = new Player(2, "Ka'ulu");
        Player Player3 = new Player(3, "Sam");
        Player Player4 = new Player(4, "Chandler");

        GameState firstInstance = new GameState();
        firstInstance.populateDeck();
        firstInstance.addPlayer(Player1);
        firstInstance.addPlayer(Player2);
        firstInstance.addPlayer(Player3);
        firstInstance.addPlayer(Player4);
        GameState secondInstance = new GameState(firstInstance);

        CardAction attack2 = new PlayAttackCard(Player1, Player2);
        firstInstance.makeMove(attack2);
        displayText.append(Player1.getPlayerName() + " attacked " + Player2.getPlayerName() + ".");
        CardAction

    }
}