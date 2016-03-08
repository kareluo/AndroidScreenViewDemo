package me.kareluo.screendemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by felix on 16/3/7.
 */
public class ChatMessage implements IMessage, Parcelable {
    private long mId;
    private int mType;
    private int mSource;
    private String mName;
    private String mContent;
    private long mTime;

    private ChatMessage(Builder builder) {
        this.mId = builder.id;
        this.mType = builder.type;
        this.mSource = builder.source;
        this.mName = builder.name;
        this.mContent = builder.content;
        this.mTime = builder.time;
    }

    private ChatMessage(Parcel source) {
        readFromParcel(source);
    }

    public static final Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel source) {
            return new ChatMessage(source);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public int getType() {
        return mType;
    }

    @Override
    public int getSource() {
        return mSource;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getContent() {
        return mContent;
    }

    @Override
    public long getTime() {
        return mTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mType);
        dest.writeInt(mSource);
        dest.writeString(mName);
        dest.writeString(mContent);
        dest.writeLong(mTime);
    }

    public void readFromParcel(Parcel src) {
        mId = src.readLong();
        mType = src.readInt();
        mSource = src.readInt();
        mName = src.readString();
        mContent = src.readString();
        mTime = src.readLong();
    }

    public static final class Builder {
        private long id;
        private int type;
        private int source;
        private String name;
        private String content;
        private long time;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setSource(int source) {
            this.source = source;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTime(long time) {
            this.time = time;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }
    }
}
