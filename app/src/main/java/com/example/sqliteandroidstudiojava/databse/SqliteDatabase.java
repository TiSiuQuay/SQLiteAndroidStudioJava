package com.example.sqliteandroidstudiojava.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteandroidstudiojava.Anime;

import java.util.ArrayList;
import java.util.List;


public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION =	1;
    private static final String DATABASE_NAME = "ANIMEDB";
    private final static String TABLE_NAME ="ANIME";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_SCORE = "SCORE";
    private static final String COLUMN_EPISODE = "EPISODE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_URL = "URL";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE	TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME+ " TEXT," + COLUMN_SCORE+ " INTEGER," + COLUMN_EPISODE+ " INTEGER," + COLUMN_DESCRIPTION+ " TEXT," + COLUMN_URL+ " TEXT);";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Anime> listAnime(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Anime> listAnime = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int score = cursor.getInt(2);
                int episode = cursor.getInt(3);
                String desc = cursor.getString(4);
                String url = cursor.getString(5);
                listAnime.add(new Anime(id,name,score,episode,desc,url));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listAnime;
    }

    public  Anime getAnime(int id){
        String sql = "select * from " + TABLE_NAME+" where "+COLUMN_ID+"	= ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Anime mAnime = new Anime();

        Cursor cursor=db.rawQuery(sql, new String[] { String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                mAnime.setName(cursor.getString(1));
                mAnime.setScore(cursor.getInt(2));
                mAnime.setEpisode(cursor.getInt(3));
                mAnime.setDescription(cursor.getString(4));
                mAnime.setUrl( cursor.getString(5));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return mAnime;
    }

    public void newAnime(Anime mAnime){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mAnime.getName());
        values.put(COLUMN_SCORE, mAnime.getScore());
        values.put(COLUMN_EPISODE, mAnime.getEpisode());
        values.put(COLUMN_DESCRIPTION, mAnime.getDescription());
        values.put(COLUMN_URL, mAnime.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public  void updateAnime(Anime mAnime, int id){
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME, mAnime.getName());
        values.put(COLUMN_SCORE, mAnime.getScore());
        values.put(COLUMN_EPISODE, mAnime.getEpisode());
        values.put(COLUMN_DESCRIPTION, mAnime.getDescription());
        values.put(COLUMN_URL, mAnime.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_ID+"="+ id,null);
    }

    public void deleteAnime(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }


}
