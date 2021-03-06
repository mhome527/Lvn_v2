package teach.vietnam.asia.view.custom;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import teach.vietnam.asia.utils.Log;


public class TouchableWrapper extends FrameLayout {

	private float oldX;
	private float oldY;
	private float newX;
	private float newY;
	FragmentActivity context;
	// boolean isTouchProgress = true;
	private Runnable updateTimerMethod = new Runnable() {

		public void run() {
//			FragmentManager fm1 = context.getSupportFragmentManager();
			// for (String fragment : SCREEN_BLUR) {
			// if ( getVisibleFragment().getTag().equals(fragment) && !getVisibleFragment().getTag().equals(Run16Fragment.class.getName())
			// && !getVisibleFragment().getTag().equals(Run8Fragment.class.getName())) {
			// ((BaseFragment) fm1.findFragmentByTag(fragment)).captureMapScreen();
			// // isTouchProgress = false;
			// break;
			// }
			// }
			Log.i(TouchableWrapper.class, "updateTimer");
		}
	};

	// Create static inner class for hanlder
	private static class MyHandler extends Handler {
	}

	private MyHandler myHandler = new MyHandler();

	public TouchableWrapper(FragmentActivity context) {
		super(context);
		this.context = context;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TouchableWrapper.class,"Touch down");
			oldX = event.getX();
			oldY = event.getY();
			myHandler.removeCallbacks(updateTimerMethod);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TouchableWrapper.class,"Touch move");
			newX = event.getX();
			newY = event.getY();
//			float distance = (float) Math.sqrt((newX - oldX) * (newX - oldX) + (newY - oldY) * (newY - oldY));
//			if (distance > 15) {
//				FragmentManager fm = context.getSupportFragmentManager();
				// for (String fragment : SCREEN_BLUR) {
				// if (getVisibleFragment().getTag().equals(fragment)) {
				// ((BaseFragment) fm.findFragmentByTag(fragment)).invisibleViewBlur();
				// // isTouchProgress = false;
				// break;
				// }
				// }
//			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			Log.i(TouchableWrapper.class,"Touch cancel or up");
			myHandler.postDelayed(updateTimerMethod, 1000);
			break;
		default:
			Log.i(TouchableWrapper.class,"Touch default");
			// if (isTouchProgress) {

			// } else {
			// isTouchProgress = true;
			// }
			break;

		}

		return super.dispatchTouchEvent(event);
	}

	public Fragment getVisibleFragment() {
		FragmentManager fragmentManager = context.getSupportFragmentManager();
		// List<Fragment> fragments = fragmentManager.getFragments();
		Fragment f = null;
		// for (Fragment fragment : fragments) {
		// if(fragment != null && fragment.getUserVisibleHint()){
		// f = fragment;
		// }
		// }
		return f;
	}
}
