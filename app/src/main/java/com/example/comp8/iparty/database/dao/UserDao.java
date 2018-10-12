package com.example.comp8.iparty.database.dao;

import android.content.Context;

import com.example.comp8.iparty.database.DBOpenHelper;
import com.example.comp8.iparty.database.model.UserModel;

import java.util.List;

public class UserDao extends AbstractDAO {

    private final String[] columns = {
            UserModel.ID,
            UserModel.USER_NAME,
            UserModel.EMAIL,
            UserModel.PASSWORD
    };

    public UserDao(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public int Delete(){
        return 0;
    }

    public int Update(){
        return 0;
    }

    public List<UserModel> Select(){
        return null;
    }
}
