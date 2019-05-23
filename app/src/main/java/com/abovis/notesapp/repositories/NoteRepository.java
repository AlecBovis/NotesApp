package com.abovis.notesapp.repositories;

import com.abovis.notesapp.models.Note;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteRepository {

    public static List<Note> list(){
        List<Note> notes = SugarRecord.listAll(Note.class);
        return notes;
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }

    public static void create(String asunto, String content){
        Note note = new Note(asunto, content);
        SugarRecord.save(note);
    }

    public static void update(String asunto, String content, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setAsunto(asunto);
        note.setContent(content);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }

}
