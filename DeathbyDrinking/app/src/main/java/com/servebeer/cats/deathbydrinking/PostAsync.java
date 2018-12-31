package com.servebeer.cats.deathbydrinking;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

class PostAsync extends AsyncTask<String, String, JSONObject> {
    HttpJsonParser jsonParser = new HttpJsonParser();

    private ProgressDialog pDialog;

    private static final String LOGIN_URL = "http://cats.servebeer.com/DeathbyDrinkingWebserver/DeathbyDrinkingWebserver/start_new_game.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_PLAYER_NUMBER = "playernumber";


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected JSONObject doInBackground(String... args) {

        try {

            HashMap<String, String> params = new HashMap<>();
            params.put("roomcode", args[0]);
            params.put("name", args[1]);

            Log.d("request", "starting");

            JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);

            if (json != null) {
                Log.d("JSON result", json.toString());

                return json;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(JSONObject json) {

        int success = 0;
        int playernumber = 0;
        String message = "";

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }

        if (json != null) {

            try {
                success = json.getInt(TAG_SUCCESS);
                message = json.getString(TAG_MESSAGE);
                playernumber = json.getInt(TAG_PLAYER_NUMBER);
                Log.d("playerNumber", String.valueOf(playernumber));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (success == 1) {
            Log.d("Success!", message);
        }else{
            Log.d("Failure", message);
        }
    }

}