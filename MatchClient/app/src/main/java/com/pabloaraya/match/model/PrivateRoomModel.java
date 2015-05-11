package com.pabloaraya.match.model;

/**
 * Created by pablo on 3/17/15.
 */
public class PrivateRoomModel {
    private String user_id;
    private String user_name;
    private String user_last_message;
    private boolean is_read;

    public PrivateRoomModel(){}

    public PrivateRoomModel(String user_id, String user_name, String user_last_message, boolean is_read){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_last_message = user_last_message;
        this.is_read = is_read;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserid(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserLastMessage() {
        return user_last_message;
    }

    public void setUserLastMessage(String user_last_message) {
        this.user_last_message = user_last_message;
    }

    public boolean isRead() {
        return is_read;
    }

    public void setRead(boolean is_read) {
        this.is_read = is_read;
    }
}
