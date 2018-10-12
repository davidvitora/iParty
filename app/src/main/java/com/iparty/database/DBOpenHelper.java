package com.iparty.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iparty.database.model.UserModel;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOME = "iparty.db";
    private static final int DATABASE_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserModel.DROP_TABLE);
        db.execSQL(UserModel.CREATE_TABLE);
    }
}
