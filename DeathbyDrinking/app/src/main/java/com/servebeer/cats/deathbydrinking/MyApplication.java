package com.servebeer.cats.deathbydrinking;

import android.app.Application;

public class MyApplication extends Application {

    private String roomCode;
    private String name;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
