package edu.sdsmt.id7430209.Room;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import edu.sdsmt.id7430209.MapState.MapState;

public class RedRoom extends Room {

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint(Color.RED);
        canvas.drawPaint(paint);
    }

    @Override
    public void onEnter(MapState state, Canvas canvas) {

    }

    @Override
    public void onExit(MapState state, Canvas canvas) {

    }
}
