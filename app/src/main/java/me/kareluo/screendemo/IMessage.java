package me.kareluo.screendemo;

/**
 * Created by felix on 16/3/7.
 */
public interface IMessage {
    int TYPE_SEND = 0;
    int TYPE_RECE = 1;

    int SOURCE_FRIEND = 0;
    int SOURCE_GROUP = 1;
    int SOURCE_STRANGER = 2;

    long getId();

    int getType();

    int getSource();

    String getName();

    String getContent();

    long getTime();
}
