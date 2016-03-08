package me.kareluo.screendemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_SHOW = 1;
    private static final int MSG_MULTI = 2;

    private MainHandler mHandler;

    private static final Random RANDOM = new Random();

    private static final long[] IDS = {23l, 24l, 25l, 26l, 27l, 28l};
    private static final String ACTION = "me.kareluo.screendemo.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(this);

        mHandler = new MainHandler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                mHandler.sendEmptyMessageDelayed(MSG_MULTI, 5000);
                break;
        }
    }

    private static class MainHandler extends Handler {
        WeakReference<MainActivity> mActivity;

        private MainHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;
            switch (msg.what) {
                case MSG_SHOW:
                    Intent intent = new Intent(ACTION);
                    activity.sendBroadcast(intent);
                    sendEmptyMessageDelayed(MSG_SHOW, 5000);
                    break;
                case MSG_MULTI:
                    intent = new Intent(ACTION);
                    intent.putExtra(ScreenActivity.INTENT_KEY, randomMessage());
                    activity.sendBroadcast(intent);
                    sendEmptyMessageDelayed(MSG_MULTI, 5000);
                    break;
            }
        }

    }

    private static ChatMessage randomMessage() {
        int index = RANDOM.nextInt(IDS.length);

        return new ChatMessage.Builder().setId(IDS[index]).setName("阿宝").setContent("你好呀！")
                .setSource(IMessage.SOURCE_FRIEND).setTime(SystemClock.currentThreadTimeMillis())
                .setType(IMessage.TYPE_RECE).build();
    }
}
