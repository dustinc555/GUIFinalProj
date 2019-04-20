package edu.sdsmt.id7430209.MapState;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import edu.sdsmt.id7430209.Actor.Actor;
import edu.sdsmt.id7430209.Actor.Player;
import edu.sdsmt.id7430209.Room.BlueRoom;
import edu.sdsmt.id7430209.Room.GrayRoom;
import edu.sdsmt.id7430209.Room.RedRoom;
import edu.sdsmt.id7430209.Room.Room;

public class MapState {

    private final static String TAG = "MapState";

    /// Activities context that contains the canvas's view
    private Context appContext;

    /// The current state of the machine
    private State state = State.idle;

    /// Special actor the user controls
    private Player player;

    /// List of Actors, does not contain player actor
    private ArrayList<Actor> actors = new ArrayList<>();

    /// Rooms container that also describes the room order
    private ArrayList<Room> rooms = new ArrayList<>();

    /// ID representing room location, ranging 0 to n - 1
    private int currentRoomID;

    /// generates basic room layout
    public MapState(Context context) {
        player = new Player(context);
        rooms.add( new BlueRoom() ); // 0
        rooms.add( new GrayRoom() ); // 1
        rooms.add( new RedRoom()  ); // 2
        currentRoomID = 1;
    }

    /**
     * setState(State state)
     * Causes action depending on the current and given states.
     * @param state - the new state envoked by an event
     */
    public boolean setState(State state) {
        // check actions for current state

        // switch to new states
        this.state = state;

        // perform action based on new state


        return true;
    }

    public State getState() {
        return this.state;
    }

    /**
     * void tick()
     * calls tick on every actor in ArrayList actors
     */
    public void tick(Canvas canvas) {
        Log.d(TAG, "Entered Tick");
        Log.d(TAG, "player: left " + player.getRect().left + ", right: " + player.getRect().right + ", top: " + player.getRect().top + ", bottom: " + player.getRect().bottom);
        if (!rooms.get(currentRoomID).entered) {
            Log.d(TAG, "Entering room id: " + currentRoomID);
            rooms.get(currentRoomID).onEnter(this, canvas);
        }
        for (Actor a : actors) {
            a.tick(this, canvas);
        }
    }

    /**
     * void draw(Canvas canvas)
     * calls all actors draw methods
     * @param canvas the views canvas
     */
    public void draw(Canvas canvas) {
        Log.d(TAG, "Entered Draw");
        Log.d(TAG, "player: left " + player.getRect().left + ", right: " + player.getRect().right + ", top: " + player.getRect().top + ", bottom: " + player.getRect().bottom);
        Log.d(TAG, "State: " + state.toString());
        rooms.get(currentRoomID).draw(canvas);
        for (Actor a : actors) {
            a.draw(canvas, state);
        }
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public ArrayList<Actor> getActors() {
        return this.actors;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomID);
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }
}
