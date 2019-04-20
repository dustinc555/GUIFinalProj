package edu.sdsmt.id7430209.Room;

import android.graphics.Canvas;

import edu.sdsmt.id7430209.MapState.MapState;

/**
 * Room object interface
 * Even though this has a draw method,
 * it is more of a scene than an actor.
 */
public abstract class Room {

    /// flagged true after onEnter successfully called
    public boolean entered = false;

    /// Should be called before drawing actors
    public abstract void draw(Canvas canvas);

    /// Called before setting the current room to this room
    public abstract void onEnter(MapState state, Canvas canvas);

    /// Called before leaving the room
    public abstract void onExit(MapState state, Canvas canvas);
}
