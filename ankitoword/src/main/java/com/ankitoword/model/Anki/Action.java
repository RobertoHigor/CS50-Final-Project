package com.ankitoword.model.Anki;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@JsonInclude(Include.NON_NULL)
public class Action {
    private String action;
    private int version;
    private Params params;

    public Action(String action, int version) {
        this.action = action;
        this.version = version;
    }

    public Action(String action, int version, Params params) {
        this.action = action;
        this.version = version;
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }    
}
