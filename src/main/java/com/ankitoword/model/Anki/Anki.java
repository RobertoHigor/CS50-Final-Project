package com.ankitoword.model.Anki;

import java.util.List;

public class Anki {
    private List<String> result;
    private String error;    

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    
}
