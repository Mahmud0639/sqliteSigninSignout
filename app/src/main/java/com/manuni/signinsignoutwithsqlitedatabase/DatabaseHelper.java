package com.manuni.signinsignoutwithsqlitedatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User_details.db";
    private static final int VERSION_NUMBER = 1;
    private static final String TABLE_NAME = "table_for_signin";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(30), " + EMAIL + " VARCHAR(30), " + USERNAME + " VARCHAR(30), " + PASSWORD + " VARCHAR(30));";
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called.", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to create a schema", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "onUpgrade is called.", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, "Cannot change the field.", Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(UserData data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, data.getName());
        contentValues.put(EMAIL, data.getEmail());
        contentValues.put(USERNAME, data.getUsername());
        contentValues.put(PASSWORD, data.getPassword());
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }
    public Boolean findPassword(String my_user_name, String my_password){
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        boolean isMatched = false;
        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                String username = cursor.getString(3);
                String password = cursor.getString(4);
                if (username.equals(my_user_name) && password.equals(my_password)){
                    isMatched = true;
                    break;
                }
            }
        }
        return isMatched;

    }
}
