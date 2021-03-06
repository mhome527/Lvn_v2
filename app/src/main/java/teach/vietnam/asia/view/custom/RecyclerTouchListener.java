package teach.vietnam.asia.view.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import teach.vietnam.asia.view.action.IClickListener;

/**
 * Created by HuynhTD on 10/18/2016.
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private IClickListener iClickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final IClickListener iClickListener) {
        this.iClickListener = iClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
//                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && iClickListener != null) {
//                    iClickListener.actionLongClick(child, recyclerView.getChildPosition(child));
//                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && iClickListener != null && gestureDetector.onTouchEvent(e)) {
            iClickListener.actionClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
