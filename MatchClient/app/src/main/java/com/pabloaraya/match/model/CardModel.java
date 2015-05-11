package com.pabloaraya.match.model;

/**
 * Created by pablo on 2/25/15.
 */

public class CardModel {
    private String user_id;
    private String facebook_id;
    private String text;
    private String first_name;
    private int age;

    public CardModel(){}

    public CardModel(String user_id, String facebook_id, String text, String first_name, int age){
        this.user_id = user_id;
        this.facebook_id = facebook_id;
        this.text = text;
        this.first_name = first_name;
        this.age = age;
    }

    public String getUserId(){
        return this.user_id;
    }

    public void setUserId(String user_id){
        this.user_id = user_id;
    }

    public String getFacebookId() {
        return facebook_id;
    }

    public void setFacebookId(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
