package com.example.comp8.iparty.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.comp8.iparty.database.DBOpenHelper;
import com.example.comp8.iparty.database.model.UserModel;

public abstract class AbstractDAO {

    protected SQLiteDatabase sqliteDatabase;
    protected DBOpenHelper dbOpenHelper;
    private String[] columns = {UserModel.ID, UserModel.USER_NAME, UserModel.EMAIL, UserModel.PASSWORD};

    protected final void Open() throws SQLException {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
    }

    protected final void Close() throws SQLException {
        dbOpenHelper.close();
    }

    public long Insert(String email, String password) {
        long returnValue = 0;
        try {
            Open();
            ContentValues values = new ContentValues();
            values.put(UserModel.EMAIL, email);
            values.put(UserModel.PASSWORD, password);
            returnValue = sqliteDatabase.insert(UserModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }
        return returnValue;
    }

    public boolean SelectUser(String email, String password) {
        boolean existsUser = false;
        Cursor cursor = null;
        try {
            Open();
            cursor = sqliteDatabase.query(UserModel.TABLE_NAME, columns,
                    UserModel.EMAIL + " = ? and " + UserModel.PASSWORD + " = ?",
                    new String[]{email, password},
                    null,
                    null,
                    null);
            existsUser = cursor.moveToFirst();
        } finally {
            cursor.close();
            Close();
        }
        return existsUser;
    }
}