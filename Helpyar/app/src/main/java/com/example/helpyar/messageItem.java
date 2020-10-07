package com.example.helpyar;

import android.content.Context;

public class messageItem {

    String chatUser;
    String text;
    String message_date;


    public messageItem(String chatUser, String text, String message_date) {
        this.chatUser = chatUser;
        this.text = text;
        this.message_date = message_date;

    }


    public String getChatUser() {
        return chatUser;
    }

    public String getText() {
        return text;
    }

    public String getMessage_date() {
        return message_date;
    }



}
