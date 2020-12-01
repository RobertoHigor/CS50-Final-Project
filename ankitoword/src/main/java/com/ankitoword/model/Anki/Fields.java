package com.ankitoword.model.Anki;

public class Fields {
    private String front;
    private String back;

    public Fields(String front, String back) {
        this.front = front;
        this.back = back;
    }
    
    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
