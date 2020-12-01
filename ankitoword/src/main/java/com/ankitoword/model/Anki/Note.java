package com.ankitoword.model.Anki;

import java.util.List;

public class Note {
    private String deckName;
    private String modelName;
    private Fields fields;
    private List<String> tags;

    public Note(String deckName, String modelName, Fields fields, List<String> tags) {
        this.deckName = deckName;
        this.modelName = modelName;
        this.fields = fields;
        this.tags = tags;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }    
}
