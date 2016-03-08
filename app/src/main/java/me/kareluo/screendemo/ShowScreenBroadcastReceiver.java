package me.kareluo.screendemo;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by felix on 16/3/7.
 */
public class ShowScreenBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ShowScreenReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, intent.getAction());
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        Intent action = new Intent(context, ScreenActivity.class);
        action.putExtra(ScreenActivity.INTENT_KEY, intent.getParcelableExtra(ScreenActivity.INTENT_KEY));
        action.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(action);
    }
}
