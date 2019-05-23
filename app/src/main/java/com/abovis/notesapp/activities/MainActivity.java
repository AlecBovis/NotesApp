package com.abovis.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abovis.notesapp.R;
import com.abovis.notesapp.adapters.NoteAdapter;
import com.abovis.notesapp.models.Note;
import com.abovis.notesapp.models.User;
import com.abovis.notesapp.repositories.NoteRepository;
import com.abovis.notesapp.repositories.UserRepository;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Notas");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Long id = sp.getLong("id", 0);
        User user = UserRepository.load(id);

        if(user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Configure ReciclerView
        notelist = (RecyclerView) findViewById(R.id.note_list);
        notelist.setLayoutManager(new LinearLayoutManager(this));

        // Set Data Adapter to ReciclerView
        List<Note> notes = NoteRepository.list();
        notelist.setAdapter(new NoteAdapter(notes));
    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterNoteActivity.class), REGISTER_FORM_REQUEST);
    }

    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        NoteAdapter adapter = (NoteAdapter) notelist.getAdapter();

        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }
}
