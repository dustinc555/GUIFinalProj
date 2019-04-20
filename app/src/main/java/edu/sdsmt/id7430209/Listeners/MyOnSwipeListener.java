package edu.sdsmt.id7430209.Listeners;

import android.util.Log;

import edu.sdsmt.id7430209.MapState.MapState;
import edu.sdsmt.id7430209.MapState.State;
import edu.sdsmt.id7430209.OnSwipeListener;

public class MyOnSwipeListener extends OnSwipeListener {

    private MapState state;

    public MyOnSwipeListener(MapState state) {
        this.state = state;
    }

    @Override
    public boolean onSwipe(Direction direction) {

        final String TAG = "GameView";
        switch (direction) {
            case up:
                Log.d(TAG, "Up");
                break;
            case down:
                Log.d(TAG, "Down");
                break;
            case left:
                Log.d(TAG, "Left");
                state.setState(State.leavingLeft);
                break;
            case right:
                Log.d(TAG, "Right");
                state.setState(State.leavingRight);
                break;
        }

        return false;
    }
}
