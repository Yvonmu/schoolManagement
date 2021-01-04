package com.example.leilascst172s;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Userdetails";
    private static final String COL1 = "ID";
    private static final String COL2 = "realname";

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(" +
                "ID INTEGER PRIMARY KEY," +
                " realname TEXT NOT NULL UNIQUE, " +
                "sex TEXT, " +
                "cellphone TEXT, " +
                "dormitory TEXT, " +
                "address TEXT, " +
                "guardian TEXT, " +
                "G_phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
        onCreate(DB);
    }

    public Boolean insertuserdata(String ID, String realname, String sex, String cellphone, String dormitory, String address, String guardian, String G_phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("realname", realname);
        contentValues.put("sex", sex);
        contentValues.put("cellphone", cellphone);
        contentValues.put("dormitory", dormitory);
        contentValues.put("address", address);
        contentValues.put("guardian", guardian);
        contentValues.put("G_phone", G_phone);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdata(String ID, String realname, String sex, String cellphone, String dormitory, String address, String guardian, String G_phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("realname", realname);
        contentValues.put("sex", sex);
        contentValues.put("cellphone", cellphone);
        contentValues.put("dormitory", dormitory);
        contentValues.put("address", address);
        contentValues.put("guardian", guardian);
        contentValues.put("G_phone", G_phone);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where ID=?", new String[]{ID});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "ID=?", new String[]{ID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String realname) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where realname=?", new String[]{realname});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "realname=?", new String[]{realname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where realname=?", new String[]{name});
        return cursor;
    }

    public Cursor searchUser(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME + "WHERE " + COL2 + "Like '%" + text + "%'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
