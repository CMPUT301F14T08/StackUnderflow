
package cs.ualberta.CMPUT301F14T08.stackunderflow.controllers;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;

/**
 * OnSwipeTouchListener - Does the math to see if the user has swiped so that users may swipe thru
 * all answers to a question.
 * 
 * @author Cmput301 Winter 2014 Group 8
 */
public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector mGestureDetector;

    public OnSwipeTouchListener(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int DISTANCE_THRESHOLD = 100;
        private static final int VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // Checks for swiping
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velX, float velY) {
            float disX = event2.getX() - event1.getX();
            float disY = event2.getY() - event1.getY();
            // If moving more left and right than up and down AND
            // if moving more than the minimum distance AND
            // if moving faster than the minimum velocity, we have a swipe
            if (Math.abs(disX) > Math.abs(disY) && Math.abs(disX) > DISTANCE_THRESHOLD
                    && Math.abs(velX) > VELOCITY_THRESHOLD) {
                if (disX > 0) // Postive distance is right, negative is left
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }
}
