package com.servebeer.cats.deathbydrinking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class SetupGameActivity extends AppCompatActivity {
    Button createButton;
    Button joinButton;
    EditText roomCodeTextBox;
    TextView gameStartInfoTextBox;
    EditText nameTextBox;
    TextView nameInfoTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        roomCodeTextBox = (EditText) findViewById(R.id.RoomCode);
        gameStartInfoTextBox = (TextView) findViewById(R.id.GameStartInfo);
        nameTextBox = (EditText) findViewById(R.id.NameBox);
        nameInfoTextBox = (TextView) findViewById(R.id.NameInfoBox);

        createButton = (Button) findViewById(R.id.CreateGame);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = nameTextBox.getText().toString();
                Boolean startGame = Boolean.TRUE;
                if ((name.equals("")) || (name.length() > 20) || name.contains("Name")){
                    nameInfoTextBox.setText("Set a Valid name less then 20 chars");
                    nameInfoTextBox.setTextColor(Color.parseColor("red"));
                    startGame = false;
                }
                if (startGame) {
                    goToStartGame("", name);
                }
            }
        });

        joinButton = (Button) findViewById(R.id.JoinGame);
        joinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String roomCode = roomCodeTextBox.getText().toString();
                String name = nameTextBox.getText().toString();
                Boolean startGame = Boolean.TRUE;
                Log.d("LogSetupGameActivity", "roomcode is " + roomCode);
                if ((roomCode.equals("")) || (roomCode.length() != 4)){
                    gameStartInfoTextBox.setText("Set a Valid 4 Character Room Code");
                    gameStartInfoTextBox.setTextColor(Color.parseColor("red"));
                    startGame = false;
                }
                if ((name.equals("")) || (name.length() > 20) || name.contains("Name")){
                    nameInfoTextBox.setText("Set a Valid name less then 20 chars");
                    nameInfoTextBox.setTextColor(Color.parseColor("red"));
                    startGame = false;
                }
                if (startGame){
                    goToStartGame(roomCode.substring(0,4).toLowerCase(), name);
                }
            }
        });
    }
    private void goToStartGame(String roomCode, String name) {
        if(roomCode == ""){
            roomCode = GeneratingRandomString();
        }
        ((MyApplication) this.getApplication()).setRoomCode(roomCode);
        ((MyApplication) this.getApplication()).setName(name);
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
    }

    public String GeneratingRandomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
