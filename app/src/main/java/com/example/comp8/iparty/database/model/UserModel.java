package com.example.comp8.iparty.database.model;

public class UserModel {

    public static final String TABLE_NAME = "users";

    public static final String
            ID ="_id",
            USER_NAME ="user_name",
            EMAIL ="email",
            PASSWORD ="password";

    public static final String CREATE_TABLE =
            "create table " + TABLE_NAME
                    + "{" + ID + " integer primary key autoincrement,"
                    + USER_NAME + " text not null,"
                    + EMAIL + " text not null,"
                    + PASSWORD + " text not null};";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";
}