package org.example.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Note {
    private int id;
    private String title;
    private LocalDate date;
    private String tag;
    private String note;

    public Note() {
    }

    public Note(int id, String title, LocalDate date, String tag, String note) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.tag = tag;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
