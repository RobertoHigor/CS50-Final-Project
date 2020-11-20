package com.ankitoword.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    private String id;
    
    @JsonProperty("app-shortdef")
    private AppShortdef appShortdef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppShortdef getAppShortdef() {
        return appShortdef;
    }

    public void setAppShortdef(AppShortdef appShortdef) {
        this.appShortdef = appShortdef;
    }

    
    
}
