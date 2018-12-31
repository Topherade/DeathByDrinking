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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText roomCodeTextBox = (EditText) findViewById(R.id.RoomCode);
        final TextView gameStartInfoTextBox = (TextView) findViewById(R.id.GameStartInfo);

        Button createButton = (Button) findViewById(R.id.CreateGame);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToStartGame("");
            }
        });

        Button joinButton = (Button) findViewById(R.id.JoinGame);
        joinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String roomCode = roomCodeTextBox.getText().toString();
                Log.d("LogSetupGameActivity", "roomcode is " + roomCode);
                if ((roomCode.equals("")) || (roomCode.length() != 4)){
                    gameStartInfoTextBox.setText("Set a Valid 4 Character Room Code");
                    gameStartInfoTextBox.setTextColor(Color.parseColor("red"));
                }else{
                    goToStartGame(roomCode.substring(0,4).toLowerCase());
                }
            }
        });
    }
    private void goToStartGame(String roomCode) {
        if(roomCode == ""){
            roomCode = GeneratingRandomString();
        }
        ((MyApplication) this.getApplication()).setRoomCode(roomCode);
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
