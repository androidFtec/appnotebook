package br.com.trinopolo.appnotebook;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import br.com.trinopolo.appnotebook.db.DatabaseHelper;
import br.com.trinopolo.appnotebook.notes.Note;
import br.com.trinopolo.appnotebook.notes.NoteService;

public class NoteActivity extends AppCompatActivity {

    private int noteId;
    private TextView editTextBody;
    private TextView editTextTitle;
    private SQLiteDatabase database;
    private NoteService noteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteId = getIntent().getIntExtra("id", -1);

        if(noteId == -1) {
            setTitle("Nova anotacao");
        } else {
            setTitle("Anotacao " + noteId);
        }

        setup();
        loadData();
    }

    private void loadData() {
        if(noteId == -1) { return; }

        Note note = noteService.get(noteId);

        editTextTitle.setText(note.getTitle());
        editTextBody.setText(note.getBody());
    }

    private void setup() {

        database = new DatabaseHelper(this).getWritableDatabase();
        noteService = new NoteService(database);

        editTextTitle = (TextView) findViewById(R.id.editTextTitle);
        editTextBody = (TextView) findViewById(R.id.editTextBody);
    }

    public void btnSalvarClick(View view) {

        if(editTextTitle.getText().length() == 0) {
            return;
        }

        salvar();
        finish();
    }

    public void btnDeletarClick(View view) {

        if(noteId == -1) {
            finish();
            return;
        }

        noteService.delete(noteId);

        finish();
    }

    public void salvar() {

        Note note = new Note(noteId,
                editTextTitle.getText().toString(),
                editTextBody.getText().toString(),
                new Date());

        if(noteId == -1) {
            noteService.add(note);
        } else {
            noteService.update(note);
        }
    }
}
