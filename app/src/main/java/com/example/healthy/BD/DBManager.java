package com.example.healthy.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthy.Notes.Note;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public void DBOpen() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void DBInsert(String TITLE, String DISC) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.Title, TITLE);
        contentValues.put(Constants.Disc, DISC);
        sqLiteDatabase.insert(Constants.TableName, null, contentValues);
    }

    public List<String> DBGetTitle() {
        List<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(Constants.TableName, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String TITLE = cursor.getString(cursor.getColumnIndexOrThrow(Constants.Title));
            list.add(TITLE);
        }
        cursor.close();
        return list;
    }




    public List<Note> DBGetNote() {
        List<Note> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(Constants.TableName, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.Title)));
            note.setDiscription(cursor.getString(cursor.getColumnIndexOrThrow(Constants.Disc)));
            note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants._id)));
            list.add(note);
        }
        cursor.close();
        return list;
    }


    public void Delete(int id){
String selection = Constants._id + "=" + id;
        sqLiteDatabase.delete(Constants.TableName, selection, null);

    }

    public void DBClose() {
        dbHelper.close();
    }

}
