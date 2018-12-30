package com.servebeer.cats.deathbydrinking;

import android.app.Application;

public class MyApplication extends Application {

    private String roomCode;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}
