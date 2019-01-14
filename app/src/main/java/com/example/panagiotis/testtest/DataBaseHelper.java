package com.example.panagiotis.testtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.text.MessagePattern.ArgType.SELECT;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "newDB.db";
    private static final String TABLE_NAME = "Stocks";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SYMBOL";
    private static final String COL_3 = "PRICE";
    private static final String COL_4 = "NUM_OF_STOCKS";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SYMBOL TEXT UNIQUE ,PRICE INTEGER,NUM_OF_STOCKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public Integer deleteData(String symbol) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "SYMBOL = ?", new String[]{symbol});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + TABLE_NAME, null);
        return data;
    }

    public boolean insertData(String symbol, String price, String numOfStocks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, symbol);
        contentValues.put(COL_3, price);
        contentValues.put(COL_4, numOfStocks);

        long  result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String symbol, String price, String numOfStocks) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isUpdated =false;
        ContentValues contentValues = new ContentValues();

        Cursor data = this.getAllData();

        StringBuffer buffer = new StringBuffer();
        while (data.moveToNext()) {
            buffer.append("Id :" + data.getString(0) + "\n");
            buffer.append("Symbol :" + data.getString(1) + "\n");
            buffer.append("Price :" + data.getString(2) + "\n");
            buffer.append("NumofStocks :" + data.getString(3) + "\n\n");

            if (data.getString(1).equals(symbol)){
            contentValues.put(COL_2, symbol);
            contentValues.put(COL_3, price);
            contentValues.put(COL_4, numOfStocks);
            db.update(TABLE_NAME, contentValues, "SYMBOL = ?", new String[]{symbol});
            isUpdated=true;
            }



        }

        return isUpdated;
    }
}








