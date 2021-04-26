package com.vaibhav.android.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_db")
                            .addCallback(sRoomDbCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDbCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(INSTANCE).execute();
        }
    };

    static class PopulateDb extends AsyncTask<Void, Void, Void> {

        private final WordRoomDatabase mWordDatabase;

        PopulateDb(WordRoomDatabase db) {
            mWordDatabase = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDatabase.wordDao().insert(new Word("Hello"));
            mWordDatabase.wordDao().insert(new Word("World"));
            mWordDatabase.wordDao().insert(new Word("Surbhi"));
            return null;
        }
    }

}
