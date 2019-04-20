package edu.sdsmt.id7430209.Room;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import edu.sdsmt.id7430209.MapState.MapState;

public class BlueRoom extends Room {

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint(Color.BLUE);
        canvas.drawPaint(paint);
    }

    @Override
    public void onEnter(MapState state, Canvas canvas) {

    }

    @Override
    public void onExit(MapState state, Canvas canvas) {

    }
}