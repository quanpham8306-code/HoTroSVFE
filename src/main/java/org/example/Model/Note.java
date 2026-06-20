package org.example.Model;

import java.time.LocalDate;

public class Note {
    private String title;
    private LocalDate date;
    private String tag;
    private String note;

    public Note() {
    }

    public Note(String title, LocalDate date, String tag, String note) {
        this.title = title;
        this.date = date;
        this.tag = tag;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
