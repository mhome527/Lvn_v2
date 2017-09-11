package teach.vietnam.asia.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.BaseActivity;

public class Utility {
    private static final String TAG = "Utility";

    public static int randomPos(int lenght, int... params) {
        Random r;
        int i;
        boolean b = false;

        for (int j = 0; j < 100; j++) {
            r = new Random();
            i = r.nextInt(lenght);
            for (int value : params) {
                if (i == value) {
                    b = false;
                    break;
                } else
                    b = true;
            }
            if (b)
                return i;
        }
        return 0;
    }

    public static boolean isSpeechRecognition(Context context) {
        try {
            // Check to see if a recognition activity is present
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
            if (activities == null || activities.size() == 0) {
                Log.e(Utility.class, "checkSpeed NOT SUPORT!!!");
                return false;
            } else
                return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Asking the permission for installing Google Voice Search. If permission granted – sent user to Google Play
     *
     * @param context – Activity, that initialized installing
     */
    public static void installGoogleVoiceSearch(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_recognition_voice))
                .setTitle(context.getString(R.string.msg_title_recognition))
                .setPositiveButton(context.getString(R.string.install), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            // Voice Search package name: com.google.android.voicesearch
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_VOICE));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }


    /**
     * Showing google speech input dialog
     */
    public static void promptSpeechInput(Activity activity, int result) {
        Log.i(Utility.class, "input locate: " + Locale.getDefault());
        if (!Utility.isSpeechRecognition(activity)) {
            Utility.installGoogleVoiceSearch(activity);
            return;
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, activity.getString(R.string.speech_prompt));
        try {
            activity.startActivityForResult(intent, result);
        } catch (ActivityNotFoundException a) {
            Log.e(TAG, "input error:" + a.getMessage());
            a.printStackTrace();
            Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    public static String stripNonDigits(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static long convertToLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getResourcesID(Context context, String name) {
        try {
            if (name.equals(""))
                return -1;
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        } catch (Exception e) {
            Log.e("Utility", "get resource error:" + e.getMessage());
            return -1;
        }
    }

    public static void installVnApp(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_content_ljp))
                .setTitle(context.getString(R.string.msg_title_ljp))
                .setPositiveButton(context.getString(R.string.install), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_LJP));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setIcon(R.drawable.ic_ljp).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }


    public static void setLanguage(BaseActivity activity) {
        Locale locale = new Locale(activity.lang);
        Locale.setDefault(locale);

        Configuration config = activity.getResources().getConfiguration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
    }


}
