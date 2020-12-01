package com.ankitoword.model.Anki;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fields {
    @JsonProperty(value="Front")
    private String front;
    @JsonProperty(value="Back")
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

    @Override
    public String toString() {
        return "Fields [back=" + back + ", front=" + front + "]";
    }

    
}
