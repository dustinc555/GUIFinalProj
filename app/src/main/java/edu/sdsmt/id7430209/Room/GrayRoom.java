package edu.sdsmt.id7430209.Room;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import edu.sdsmt.id7430209.Actor.Actor;
import edu.sdsmt.id7430209.Actor.LeftDoor;
import edu.sdsmt.id7430209.Actor.Player;
import edu.sdsmt.id7430209.MapState.MapState;

public class GrayRoom extends Room {

    private final static String TAG = "GrayRoom";

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
    }

    @Override
    public void onEnter(MapState state, Canvas canvas) {

        Log.d(TAG, "Entered GrayRoom");

        state.getActors().clear();

        Player p = state.getPlayer();

        LeftDoor ld = new LeftDoor(state.getAppContext(), canvas);


        // determine player location and velocity
        int left, right, top, bottom;
        int xmid = canvas.getWidth() / 2;
        int ymid = canvas.getHeight() / 2;
        p.setWidth((int) (canvas.getWidth() * .2));
        p.setHeight((int) (canvas.getHeight() * .2));

        final int STEPS = 20;

        Log.d(TAG, "State: " + state.getState().toString());

        switch (state.getState()) {
            case idle:
                Log.d(TAG, "Entered idle case");
                left = xmid - p.getWidth() / 2;
                top = ymid - p.getHeight() / 2;
                right = xmid + p.getWidth() / 2;
                bottom = ymid + p.getHeight() / 2;
                p.setRect(new Rect(left, top, right, bottom));
                p.setVx(0);
                p.setVy(0);

            break;
            case enteringLeft:
                left = 0;
                top = ymid - p.getHeight() / 2;
                right = p.getWidth();
                bottom = ymid + p.getHeight() / 2;
                p.setRect(new Rect(left, top, right, bottom));
                p.setVx(canvas.getWidth() / STEPS);
                p.setVy(0);
            break;
            case enteringRight:
                left = canvas.getWidth() - p.getWidth();
                top = ymid - p.getHeight() / 2;
                right = canvas.getWidth();
                bottom = ymid + p.getHeight() / 2;
                p.setRect(new Rect(left, top, right, bottom));
                p.setVx(-canvas.getWidth() / STEPS);
                p.setVy(0);
            break;
        }

        state.addActor(p);
        state.addActor(ld);

        p = state.getPlayer();
        Log.d(TAG, "Testing player: left: " + p.getRect().left + ", right: " + p.getRect().right + ", top: " + p.getRect().top + ", bottom: " + p.getRect().bottom);

        entered = true;
    }

    @Override
    public void onExit(MapState state, Canvas canvas) {

    }
}
