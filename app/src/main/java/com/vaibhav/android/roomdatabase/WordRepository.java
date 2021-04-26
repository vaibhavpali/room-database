package com.vaibhav.android.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(final Word word) {
        new InsertAsyncWord(mWordDao).execute(word);
    }

    private static class InsertAsyncWord extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncWordDao;

        InsertAsyncWord(WordDao wordDao) {
            this.mAsyncWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.insert(words[0]);
            return null;
        }
    }
}
