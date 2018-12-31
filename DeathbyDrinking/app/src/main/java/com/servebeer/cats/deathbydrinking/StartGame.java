package com.servebeer.cats.deathbydrinking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StartGame extends AppCompatActivity {
    TextView roomCodeTextBox;
    TextView nameTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        roomCodeTextBox = (TextView) findViewById(R.id.roomCodeText);
        roomCodeTextBox.setText(((MyApplication) this.getApplication()).getRoomCode());
        nameTextBox = (TextView) findViewById(R.id.nameText);
        nameTextBox.setText(((MyApplication) this.getApplication()).getName());
    }

}
