package com.example.bcaunit07;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etContent;
    Button btnSave;
    ListView listView;
    DBHelper dbHelper;
    List<Note> noteList;
    ArrayAdapter<String> adapter;
    int selectedNoteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        listView = findViewById(R.id.listView);

        loadNotes();

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (!title.isEmpty() && !content.isEmpty()) {
                if (selectedNoteId == -1) {
                    dbHelper.insertNote(title, content);
                } else {
                    dbHelper.updateNote(selectedNoteId, title, content);
                    selectedNoteId = -1;
                }
                etTitle.setText("");
                etContent.setText("");
                loadNotes();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note note = noteList.get(position);
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            selectedNoteId = note.getId();
            btnSave.setText("Update Note");
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Note note = noteList.get(position);
            dbHelper.deleteNote(note.getId());
            loadNotes();
            return true;
        });
    }

    private void loadNotes() {
        noteList = dbHelper.getAllNotes();
        List<String> titles = new ArrayList<>();
        for (Note note : noteList) {
            titles.add(note.getTitle() + " - " + note.getContent());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);
        btnSave.setText("Save Note");
    }
}
