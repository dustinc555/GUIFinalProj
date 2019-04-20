package edu.sdsmt.id7430209.Actor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import edu.sdsmt.id7430209.MapState.MapState;
import edu.sdsmt.id7430209.R;
import edu.sdsmt.id7430209.MapState.State;

public class Player extends Actor {

    private static final String TAG = "Player";
    private Drawable face_left_vsg = getResources().getDrawable(R.drawable.bloppy_left);
    private Drawable face_right_vsg = getResources().getDrawable(R.drawable.bloppy_right);
    private static int pCount = 0;

    private int height;
    private int width;

    /**
     * Player(Context contex)
     * Guesses how to size the players actor. Player(Context contex, Rect rec) is recommended.
     * @param contex
     */
    public Player(Context contex) {
        super(contex);

        // if no size is specified, give arbitrary size from screen dim
        height = getResources().getDisplayMetrics().heightPixels / 10;
        width = getResources().getDisplayMetrics().widthPixels / 10;

        int top = 0;
        int left = 0;
        int bottom = top + height;
        int right = left + width;

        Log.d(TAG, "Constructor");
        Log.d(TAG, "left: " + left + ", right: " + right + ", top: " + top + ", bottom: " + bottom);
        Log.d(TAG, "Player count: " + (pCount + 1));
        pCount += 1;

        setRect(new Rect(left, top, right, bottom));
    }

    /**
     * Player(Context context, Rect size)
     * Recommended constructor.
     * @param context Application context
     * @param rect describes location and dimension of the players actor to be drawn see:
     */
    public Player(Context context, Rect rect) {
        super(context);
        setRect(rect);
        height = rect.top - rect.bottom;
        width = rect.right - rect.left;
        Log.d(TAG, "Player count: " + (pCount + 1));
    }

    public Player(Context context, Canvas canvas) {
        super(context);
        height = (int) (canvas.getHeight() * .25);
        width = (int) (canvas.getWidth() * .25);
        int xmid = canvas.getWidth() / 2;
        int ymid = canvas.getHeight() / 2;
        int left = xmid - width / 2;
        int top = ymid - height / 2;
        int right = xmid + width / 2;
        int bottom = ymid + height / 2;
        setRect(new Rect(left, top, right, bottom)); // default to center of screen
        Log.d(TAG, "Player count: " + (pCount + 1));
    }

    @Override
    public void draw(Canvas canvas, State state) {

        Drawable toDraw = face_left_vsg;

        switch (state) {
            case idle:
            case enteringLeft:
            case leavingRight:
                toDraw = face_right_vsg;
                break;
            case enteringRight:
            case leavingLeft:
                toDraw = face_left_vsg;
                break;
        }

        Log.d(TAG, "Draw evevnt");
        Log.d(TAG, "left: " + getRect().left + ", right: " + getRect().right + ", top: " + getRect().top + ", bottom: " + getRect().bottom);
        Log.d(TAG, "Canvas dim: " + canvas.getHeight() + " x " + canvas.getWidth());
        Log.d(TAG, "State: " + state.toString());

        toDraw.setBounds(getRect());
        toDraw.draw(canvas);
    }

    @Override
    public void tick(MapState state, Canvas canvas) {
        updatePosition();
        for (Actor a : state.getActors()) {
            if (a.collisionCheck(getRect())) {
                a.collisionEvent(this, state, canvas);
            }
        }
    }

    @Override
    public void collisionEvent(Actor a, MapState state, Canvas canvas) {

    }

    @Override
    public void setRect(Rect rect) {
        Log.d(TAG, "Incoming set rect");
        Log.d(TAG, "left: " + rect.left + ", right: " + rect.right + ", top: " + rect.top + ", bottom: " + rect.bottom);
        super.setRect(rect);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
