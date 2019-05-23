package com.abovis.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abovis.notesapp.R;
import com.abovis.notesapp.models.Note;
import com.abovis.notesapp.repositories.NoteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterNoteActivity extends AppCompatActivity {

    private EditText tituloInput;
    private EditText conentInput;
    private Button registernoteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);

        setTitle("Registro de notas");

        tituloInput = findViewById(R.id.titulo_input);
        conentInput = findViewById(R.id.content_input);
        registernoteButton = findViewById(R.id.registernote_button);

        registernoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisteNote();
            }
        });

    }
    public void callRegisteNote(){


        String titulo = tituloInput.getText().toString();
        String content = conentInput.getText().toString();


        if(titulo.isEmpty() || content.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        NoteRepository.create(titulo,content);

        finish();
    }
}
