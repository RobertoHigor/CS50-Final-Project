package com.ankitoword.model.Anki;

public class Params {
    private Note note;

    public Params(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Params [note=" + note + "]";
    }

    
}
