package me.kareluo.screendemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by felix on 16/3/7.
 */
public class ScreenActivity extends Activity {
    private static final String TAG = "ScreenActivity";

    private HashMap<Long, List<ChatMessage>> mMessages;

    public static final String INTENT_KEY = "MESSAGE";

    private ViewSwitcher mViewSwitcher;

    private TextView mSingleNameText;

    private ListView mMultiListView;

    private MultiAdapter mMultiAdapter;

    private List<Long> mIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_screen);

        mIds = new ArrayList<>();
        mMessages = new HashMap<>();

        mSingleNameText = (TextView) findViewById(R.id.tv_single_name);
        mViewSwitcher = (ViewSwitcher) findViewById(R.id.vs_views);
        Log.d(TAG, "onCreate");

        mMultiListView = (ListView) findViewById(R.id.lv_multi);
        mMultiAdapter = new MultiAdapter();
        mMultiListView.setAdapter(mMultiAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
        ChatMessage message = intent.getParcelableExtra(INTENT_KEY);
        if (message != null) {
            List<ChatMessage> messages = mMessages.get(message.getId());
            if (messages == null) {
                messages = new ArrayList<>();
                mMessages.put(message.getId(), messages);
            }
            messages.add(message);
        }
        update();
    }

    private void update() {
        mViewSwitcher.setDisplayedChild(mMessages.size() < 2 ? 0 : 1);
        Iterator<Long> iterator = mMessages.keySet().iterator();
        mIds.clear();
        for (; iterator.hasNext(); ) {
            mIds.add(iterator.next());
        }
        mMultiAdapter.notifyDataSetChanged();
    }

    private class MultiAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mIds.size();
        }

        @Override
        public List<ChatMessage> getItem(int position) {
            return mMessages.get(mIds.get(position));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new ScreenItemView(getBaseContext());
            }
            ScreenItemView screenItemView = (ScreenItemView) convertView;
            screenItemView.update(getItem(position));
            return convertView;
        }
    }
}
