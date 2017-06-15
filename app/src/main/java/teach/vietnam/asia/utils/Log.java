package teach.vietnam.asia.utils;


import teach.vietnam.asia.BuildConfig;

/**
 * Created by huynhtd on 9/29/2016.
 */

public class Log {
    private static String myTag = "htd-";

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(myTag + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(myTag + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(myTag + tag, msg);
        }
    }

    public static void trace(Exception e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    public static void e(Class<?> obj, String msg) {
        if (BuildConfig.DEBUG)
            Log.e(obj.getSimpleName() + myTag, msg);
    }

    public static void i(Class<?> obj, String msg) {
        if (BuildConfig.DEBUG)
            Log.i(obj.getSimpleName() + myTag, msg);
    }

    public static void e(Object obj, String msg) {
        if (BuildConfig.DEBUG)
            if (obj instanceof String)
                Log.e(obj, msg);
            else
                Log.e(obj.getClass().getSimpleName(), msg);
    }


}
