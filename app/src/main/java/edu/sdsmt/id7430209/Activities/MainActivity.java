package edu.sdsmt.id7430209.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import edu.sdsmt.id7430209.MapState.MapState;
import edu.sdsmt.id7430209.MapState.State;
import edu.sdsmt.id7430209.R;
import edu.sdsmt.id7430209.Views.GameView;


public class MainActivity extends AppCompatActivity {

    /// frames per second to invalidate view
    private static final double FRAME_RATE = 60;
    private static long interval = (long) (1000 / FRAME_RATE);

    /// debugging tag name
    private final static String TAG = "MainActivity";

    /// the game view widget
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = findViewById(R.id.gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Pausing MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming MainActivity");
    }

    public void startSettingsActivity(View view) {
        Intent intent = new Intent(this, PlayerSelectActivity.class);
        startActivity(intent);
    }

}
