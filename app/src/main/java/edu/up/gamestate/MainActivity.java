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

        Button runButton = findViewById(R.id.runButton);
        runButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        EditText displayText = findViewById(R.id.displayText);
        displayText.setText("");

        GameState firstInstance = new GameState();
    }
}