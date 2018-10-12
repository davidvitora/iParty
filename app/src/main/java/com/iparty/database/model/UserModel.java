package com.iparty.database.model;

/**
 * User Model for database
 *
 * @author Maurício Generoso
 */
public class UserModel {

    public static final String TABLE_NAME = "users";

    public static final String
            ID = "_id",
            NAME = "name",
            EMAIL = "email",
            PASSWORD = "password";

    public static final String CREATE_TABLE =
            "create table " + TABLE_NAME
                    + "{" + ID + " integer primary key autoincrement,"
                    + NAME + " text not null,"
                    + EMAIL + " text not null,"
                    + PASSWORD + " text not null};";

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";
}