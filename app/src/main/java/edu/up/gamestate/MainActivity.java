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

        Player Player1 = new Player(1);
        Player Player2 = new Player(2);
        Player Player3 = new Player(3);
        Player Player4 = new Player(4);

        GameState firstInstance = new GameState();
        GameState secondInstance = new GameState(firstInstance);


        displayText.append("I drew a card");
    }
}