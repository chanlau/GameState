package edu.up.gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Player thisIsntDoneRight = new Player(1);

        GameState firstInstance = new GameState();
        GameState secondInstance = new GameState(firstInstance);

        firstInstance.drawCard(thisIsntDoneRight);
        displayText.append("I drew a card");
    }
}