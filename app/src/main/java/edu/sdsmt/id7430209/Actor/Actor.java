package edu.sdsmt.id7430209.Actor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import edu.sdsmt.id7430209.MapState.MapState;
import edu.sdsmt.id7430209.MapState.State;

/**
 * Actor
 * Base class for game objects
 * Contains position and velocity of actor
 */
public abstract class Actor {
    private final static String TAG = "Actor";
    private Context context;
    private Resources resources;
    private int vx;
    private int vy;
    private Rect rect;

    public Actor(Context contex) {
        this.context = contex;
        this.resources = contex.getResources();
    }

    /**
     * void draw(Canvas canvas)
     * @param canvas provided by the view to be drawn onto.
     *               Does what is necessary to display the actor to the user.
     */
    public abstract void draw(Canvas canvas, State state);

    /**
     * public abstract void collisionEvent(Actor a)
     * This method should be called after collisionCheck(Rect otherActor)\
     * has verified a collision.
     */
    public abstract void collisionEvent(Actor a, MapState state, Canvas canvas);

    /**
     * void tick()
     * Optional, tick() is called on all actors for collision detection.
     */
    public abstract void tick(MapState state, Canvas canvas);

    /**
     * public boolean collisionCheck(Rect rect)
     * @param otherActor the other objects Rect object
     * @return true if there is a collision, false otherwise
     */
    public boolean collisionCheck(Rect otherActor) {
        return rect.intersect(otherActor);
    }


    /**
     * void updatePosition()
     * Causes the actor to change location based on vx, vy
     */
    public void updatePosition() {
        rect.offset(vx, vy);
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        Log.d(TAG, "received: left: " + rect.left + ", right: " + rect.right + ", top: " + rect.top + ", bottom: " + rect.bottom);
        this.rect = rect;
        Log.d(TAG, "result: left: " + getRect().left + ", right: " + getRect().right + ", top: " + getRect().top + ", bottom: " + getRect().bottom);
    }

    /**
     * Resources getResources()
     * @return context resources for app
     */
    protected Resources getResources() {
        return this.resources;
    }
}
