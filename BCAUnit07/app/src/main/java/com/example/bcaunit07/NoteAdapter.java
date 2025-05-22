package com.example.bcaunit07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    Context context;
    List<Note> notes;
    LayoutInflater inflater;
    DBHelper dbHelper;
    OnNoteActionListener listener;

    public interface OnNoteActionListener {
        void onEdit(Note note);
        void onDelete(Note note);
    }

    public NoteAdapter(Context context, List<Note> notes, DBHelper dbHelper, OnNoteActionListener listener) {
        this.context = context;
        this.notes = notes;
        this.dbHelper = dbHelper;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return notes.size(); }

    @Override
    public Object getItem(int position) { return notes.get(position); }

    @Override
    public long getItemId(int position) { return notes.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.note_item, null);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvContent = convertView.findViewById(R.id.tvContent);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        Note note = notes.get(position);
        tvTitle.setText(note.getTitle());
        tvContent.setText(note.getContent());

        btnEdit.setOnClickListener(v -> listener.onEdit(note));
        btnDelete.setOnClickListener(v -> listener.onDelete(note));

        return convertView;
    }
}

