package br.com.trinopolo.appnotebook.notes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marconvcm on 14/07/2017.
 */

public class NoteService {

    private SQLiteDatabase database;

    public NoteService(SQLiteDatabase database) {

        this.database = database;
    }

    public boolean add(Note note) {
        try {

            ContentValues values = new ContentValues();

            values.put("title", note.getTitle());
            values.put("body", note.getBody());
            values.put("created_at", note.getCreateAt().getTime());
            values.put("status", note.getStatus());

            return database.insert("Notes", "", values) > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Note> getAll() {

        try {

            List<Note> notes = new ArrayList<>();

            String sql = "SELECT * FROM Notes";
            Cursor cursor = database.rawQuery(sql, new String[] {});

            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {

                Note note = getNote(cursor);

                notes.add(note);

                cursor.moveToNext();
            }

            return notes;

        } catch (Exception ex) {
            ex.printStackTrace();
            return  null;
        }
    }


    public boolean update(Note note) {

        try {

            ContentValues values = new ContentValues();

            values.put("title", note.getTitle());
            values.put("body", note.getBody());
            values.put("created_at", note.getCreateAt().getTime());

            String where = "id = " + note.getId();

            return database.update("Notes", values, where, new  String[]{}) > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean delete(int noteId) {

        try {
            String where = "id = " + noteId;
            return database.delete("Notes", where, new  String[]{}) > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public Note get(int id) {
        try {

            String sql = "SELECT * FROM Notes WHERE id = " + id;

            Cursor cursor = database.rawQuery(sql, new String[] {});
            cursor.moveToFirst();

            return getNote(cursor);

        } catch (Exception ex) {
            return null;
        }
    }

    private Note getNote(Cursor cursor) {
        int id = cursor.getInt(0);
        String title = cursor.getString(1);
        String body = cursor.getString(2);
        Date date = new Date(cursor.getLong(3));

        return new Note(id, title, body, date);
    }


}
