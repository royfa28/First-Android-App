package com.example.game4sell.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.game4sell.MainActivity;
import com.example.game4sell.Model.User;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "game.db";
    private static final String TABLE_NAME = "user";

    public Integer passingID;

    public static final String COL_ID = "ID";
    public static final String COL_USERNAME = "USERNAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_NUMBER = "NUMBER";

    public static final String CREATE_TABLE_ST = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "EMAIL TEXT , PASSWORD TEXT, USERNAME TEXT, ADDRESS TEXT, NUMBER INTEGER)";
    public static final String DROP_TABLE_ST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String GET_ALL_ST = "SELECT * FROM " +  TABLE_NAME;

    //create the database every time this constructor gets called. Get rid of the all parameters but context
    public DatabaseHelper(@Nullable Context context) {
//        super(context, name, factory, version);
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ST);
        onCreate(db);
    }

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_EMAIL, user.email);
        values.put(COL_PASSWORD, user.password);
        values.put(COL_USERNAME, user.userName);

        return db.insert(TABLE_NAME, null, values) == -1? false: true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(GET_ALL_ST, null);
        return cursor;
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COL_ID, COL_EMAIL, COL_PASSWORD},//Selecting columns want to query
                COL_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0),
                    cursor.getString(1));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean checkUser(String email){
        String[] columns = {
                COL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COL_EMAIL + " = ? ";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COL_ID, COL_EMAIL, COL_PASSWORD},//Selecting columns want to query
                COL_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;

    }

    public int passUserID(User user) {

        MainActivity main = new MainActivity();
        Integer ID;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,               // name of the table to query
                new String[]{COL_ID},     // String array of columns to extract
                "email=?",                    // WHERE clause (? indicates an arg)
                new String[]{user.email},     // The list of args to replace the ? (or ?'s on a sequential basis)
                null,                         // GROUP BY clause
                null,                         // HAVING clause
                null,                  // ORDER clause
                "1"                           // LIMIT value as a String
        );
        if (cursor.moveToFirst()) {
            ID = cursor.getInt(cursor.getColumnIndex((COL_ID)));
        } else {
            ID = 0;
        }
        return ID;
    }
}
