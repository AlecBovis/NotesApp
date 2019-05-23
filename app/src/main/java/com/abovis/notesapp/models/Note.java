package com.abovis.notesapp.models;

import com.orm.dsl.Table;

@Table
public class Note {

    private Long id;
    private String asunto;
    private String content;

    public Note() {
    }

    public Note(String asunto, String content) {
        this.asunto = asunto;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
