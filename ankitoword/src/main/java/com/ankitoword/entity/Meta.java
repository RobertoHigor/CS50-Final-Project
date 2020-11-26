package com.ankitoword.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    private String id;
    
    @JsonProperty("app-shortdef")
    private AppShortDef appShortdef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppShortDef getAppShortdef() {
        return appShortdef;
    }

    public void setAppShortdef(AppShortDef appShortdef) {
        this.appShortdef = appShortdef;
    }

    
    
}
