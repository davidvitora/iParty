package com.iparty.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.iparty.database.DBOpenHelper;
import com.iparty.database.model.UserModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractDao<T> {

    protected SQLiteDatabase sqliteDatabase;
    protected DBOpenHelper dbOpenHelper;


    protected final void Open() throws SQLException {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
    }

    protected final void Close() throws SQLException {
        dbOpenHelper.close();
    }

    protected long insert(String tableName, ContentValues contentValues) {
        long returnValue = 0;
        
        try {
            Open();
            returnValue = sqliteDatabase.insert(tableName, null, contentValues);
        } finally {
            Close();
        }
        return returnValue;
    }

    protected void delete(){

    }

    protected  T getOne(String tableName, String[] columns, String where, String[] values){
        Cursor cursor = null;
        try {
            Open();
            cursor = sqliteDatabase.query(tableName, columns, where, values, null, null, null);

        } finally {
            cursor.close();
            Close();
        }
        return null;
    }

}