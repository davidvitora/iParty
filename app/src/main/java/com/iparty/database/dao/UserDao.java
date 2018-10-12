package com.iparty.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.iparty.database.DBOpenHelper;
import com.iparty.database.model.UserModel;
import com.iparty.model.User;

import java.util.List;

public class UserDao extends AbstractDao<User> {

    private final String[] columns = {
            UserModel.ID,
            UserModel.NAME,
            UserModel.EMAIL,
            UserModel.PASSWORD
    };
    private String[] columnsArray = {UserModel.ID, UserModel.NAME, UserModel.EMAIL, UserModel.PASSWORD};

    public UserDao(Context context) { dbOpenHelper = new DBOpenHelper(context); }

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put(UserModel.NAME, user.getName());
        values.put(UserModel.EMAIL, user.getEmail());
        values.put(UserModel.PASSWORD, user.getPassword());
        return super.insert(UserModel.TABLE_NAME, values);
    }

    public boolean existUser(User user) {
        boolean existsUser = false;
        Cursor cursor = null;
        try {
            Open();
            cursor = sqliteDatabase.query(UserModel.TABLE_NAME, columns,
                    UserModel.EMAIL + " = ? and " + UserModel.PASSWORD + " = ?",
                    new String[]{user.getEmail(), user.getPassword()},
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