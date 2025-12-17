package com.example.crudoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "UserDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(" +
                "username TEXT," +
                "email TEXT PRIMARY KEY," +
                "password TEXT," +
                "dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean insertUser(String username, String email, String pass, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", pass);
        cv.put("dob", dob);
        return db.insert("users", null, cv) != -1;
    }

    public Boolean checkUser(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, pass});
        return c.getCount() > 0;
    }

    public Boolean updatePassword(String email, String newPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPass);
        return db.update("users", cv, "email=?", new String[]{email}) > 0;
    }

    public Boolean deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users", "email=?", new String[]{email}) > 0;
    }
}
