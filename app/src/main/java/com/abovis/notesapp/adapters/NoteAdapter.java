package com.abovis.notesapp.adapters;

import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.abovis.notesapp.R;
import com.abovis.notesapp.models.Note;
import com.abovis.notesapp.repositories.NoteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>  {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView asunto;
        public TextView content;
        public ImageButton button;

        public ViewHolder(View itemView) {
            super(itemView);
            asunto = (TextView) itemView.findViewById(R.id.asunto_text);
            content = (TextView) itemView.findViewById(R.id.conteny_text);
            button = (ImageButton) itemView.findViewById(R.id.delete_button);
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder,final  int position) {
        final  Note note = this.notes.get(position);
        viewHolder.asunto.setText(note.getAsunto());
        viewHolder.content.setText(note.getContent());

        viewHolder.button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteRepository.delete(note.getId());
                notes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}
