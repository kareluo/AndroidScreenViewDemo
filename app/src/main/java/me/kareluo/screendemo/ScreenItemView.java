package me.kareluo.screendemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by felix on 16/3/7.
 */
public class ScreenItemView extends FrameLayout {
    private static final String TAG = "ScreenItemView";

    private TextView mNameText;
    private TextView mContentText;
    private TextView mLabelText;
    private TextView mNumberText;
    private TextView mTimeText;

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public ScreenItemView(Context context) {
        this(context, null, 0);
    }

    public ScreenItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.layout_screen_item, this);

        mNameText = (TextView) findViewById(R.id.tv_name);
        mContentText = (TextView) findViewById(R.id.tv_content);
        mLabelText = (TextView) findViewById(R.id.tv_label);
        mNumberText = (TextView) findViewById(R.id.tv_number);
        mTimeText = (TextView) findViewById(R.id.tv_time);
    }

    public void update(List<ChatMessage> messages) {
        if (messages == null || messages.size() <= 0) return;
        ChatMessage message = messages.get(messages.size() - 1);
        mNameText.setText(message.getName());
        mTimeText.setText(TIME_FORMAT.format(message.getTime()));
        mContentText.setText(message.getContent());
        mNumberText.setText(String.valueOf(messages.size()));
    }
}
