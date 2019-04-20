package edu.sdsmt.id7430209.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import edu.sdsmt.id7430209.Listeners.MyOnSwipeListener;
import edu.sdsmt.id7430209.MapState.MapState;



public class GameView extends View {

    /// touch event detector for swiping
    private GestureDetectorCompat detector;


    // State object
    private MapState state = new MapState(getContext());

    // Draw Utilities
    private Paint paint = new Paint(android.R.color.black);

    public GameView(Context context) {
        super(context);
        initialize();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void initialize() {
        detector = new GestureDetectorCompat(getContext(), new MyOnSwipeListener(state));
        state.setAppContext(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // perform collision detection and actor movement
        state.tick(canvas);
        // draw actors
        state.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        final String TAG = "GameView";
        Log.d(TAG, "Touched");
        detector.onTouchEvent(motionEvent);
        return true;
    }


    public MapState getState() {
        return state;
    }
}
