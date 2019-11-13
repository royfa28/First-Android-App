package com.example.game4sell.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.game4sell.Model.Products;
import com.example.game4sell.Model.User;

import androidx.annotation.Nullable;

public class ProductDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "product.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "products";

    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_CATEGORY = "CATEGORY";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_IMAGE = "IMAGE";
    public static final String COL_PRICE = "PRICE";

    public static final String GET_ALL_ST = "SELECT * FROM " +  TABLE_NAME;

    public static final String CREATE_TABLE_ST = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "TITLE TEXT , CATEGORY TEXT, DESCRIPTION TEXT, IMAGE INTEGER, PRICE DOUBLE)";

    public ProductDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProduct(Products products){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, products.Title);
        values.put(COL_CATEGORY, products.Category);
        values.put(COL_DESCRIPTION, products.Description);
        values.put(COL_IMAGE, products.Thumbnail);
        values.put(COL_PRICE, products.Price);

        return db.insert(TABLE_NAME, null, values) == -1? false: true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(GET_ALL_ST, null);
        return cursor;
    }
}
