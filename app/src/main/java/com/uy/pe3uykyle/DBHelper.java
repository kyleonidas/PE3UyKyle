package com.uy.pe3uykyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "student.db";
    final static int ver = 1;
    final static String table = "grade";

    public DBHelper(Context context) {
        super(context, DBName, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable = "CREATE TABLE grade (ID INTEGER PRIMARY KEY AUTOINCREMENT, CodeName TEXT, VersionNos TEXT, ReleaseDate TEXT, APILevel TEXT)";
        db.execSQL(cTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dTable = "DROP TABLE IF EXISTS grade";
        db.execSQL(dTable);
        onCreate(db);
    }

    public boolean insert(String CodeName, String VersionNos, String ReleaseDate, String APILevel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Code Name", CodeName);
        cv.put("Version Nos", VersionNos);
        cv.put("Release Date", ReleaseDate);
        cv.put("API Level", APILevel);
        long inserted = db.insert(table, null, cv);
        if (inserted == -1){
            return false;
        } else return true;
    }

    public Cursor selectRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM grade", null);
    }


    public boolean update(String id, String CodeName, String VersionNos, String ReleaseDate, String APILevel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Code Name", CodeName);
        cv.put("Version Nos", VersionNos);
        cv.put("Release Date", ReleaseDate);
        cv.put("API Level", APILevel);
        db.update(table,cv,"ID=?", new String[]{id});
        return true;
    }

    public boolean delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table,"ID=?", new String[]{id});
        return true;
    }
}
