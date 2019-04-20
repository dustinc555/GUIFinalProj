package edu.sdsmt.id7430209.Actor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import edu.sdsmt.id7430209.MapState.MapState;
import edu.sdsmt.id7430209.MapState.State;
import edu.sdsmt.id7430209.Room.Room;

public class LeftDoor extends Actor {

    private static final String TAG = "LeftDoor";
    private Paint paint = new Paint(Color.MAGENTA);


    public LeftDoor(Context context, Canvas canvas) {
        super(context);
        int width = (int) (canvas.getWidth() * .15);
        int height = (int) (canvas.getHeight() * .2);
        int m = canvas.getHeight() / 2;
        int left = 0;
        int top = m - height / 2;
        int bottom = m + height / 2;
        int right = width;
        setRect(new Rect(left, top, right, bottom));
    }

    @Override
    public void draw(Canvas canvas, State state) {
        Log.d(TAG, "drawing with color: " + paint.getColor());
        Log.d(TAG, "left: " + getRect().left + ", right: " + getRect().right + ", top: " + getRect().top + ", bottom: " + getRect().bottom);
        canvas.drawRect(getRect(), paint);
    }

    @Override
    public void tick(MapState state, Canvas canvas) {

    }

    @Override
    public void collisionEvent(Actor a, MapState state, Canvas canvas) {
        if (a instanceof Player) {
            ArrayList<Room> rooms = state.getRooms();
            int roomID = state.getCurrentRoomID();

            switch (state.getState()) {
                case leavingLeft: // attempt to go left
                    if (roomID > 0) {
                        if (state.setState(State.enteringLeft)) {             // entering from the left
                            state.getCurrentRoom().onExit(state, canvas);     // exit current room
                            state.setCurrentRoomID(roomID - 1);               // update location
                            state.getCurrentRoom().onEnter(state, canvas);    // enter next room
                        }
                    }
                break;
            }
        }
    }
}
