package br.com.trinopolo.appnotebook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import br.com.trinopolo.appnotebook.db.DatabaseHelper;
import br.com.trinopolo.appnotebook.notes.Note;
import br.com.trinopolo.appnotebook.notes.NoteService;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase database;
    private NoteService noteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Notebook");

        setup();
        setupActions();
        loadData();
    }

    private void setupActions() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int noteId = noteService.getAll().get(position).getId();
                startNoteActivity(noteId);
            }
        });
    }

    private void loadData() {

        List<Note> allNotes = noteService.getAll();

        ArrayAdapter<Note> noteArrayAdapter =
                new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, allNotes);

        listView.setAdapter(noteArrayAdapter);
    }

    private void setup() {

        database = new DatabaseHelper(this).getWritableDatabase();
        noteService = new NoteService(database);
        listView = (ListView) findViewById(R.id.listView);
    }

    public void btnAddClick(View view) {

        // Somente para teste

//        Note nota = new Note(0, "Title", "Body", new Date());
//
//        noteService.add(nota);
//
//        loadData();

        startNoteActivity(-1);
    }

    private void startNoteActivity(int id) {

        Intent i = new Intent(this, NoteActivity.class);

        if(id > -1) {
            i.putExtra("id", id);
        }

        startActivity(i);
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();

    }
}
