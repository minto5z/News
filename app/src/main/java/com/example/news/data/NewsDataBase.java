package com.example.news.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {News.class}, version = NewsDataBase.DATABASE_VERSION, exportSchema = false)
@TypeConverters({SourceConverter.class})
public abstract class NewsDataBase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsDB";
    private static NewsDataBase newsDataBase = null;

    public static synchronized NewsDataBase getInstance(Context context) {
        if (newsDataBase == null) {
            newsDataBase = Room.databaseBuilder(context.getApplicationContext(), NewsDataBase.class, DATABASE_NAME).build();

        }

        return newsDataBase;
    }

    public abstract NewsDao newsDao();

}
