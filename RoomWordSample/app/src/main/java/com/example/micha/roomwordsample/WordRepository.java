package com.example.micha.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by micha on 4/12/2018.
 */

public class WordRepository {
    private WordDao dao;
    private LiveData<List<Word>> allWords;

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getINSTANCE(application);
        dao = db.wordDao();
        allWords = dao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word word){
        new insertAsyncTask(dao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>{

        private WordDao dao;

        insertAsyncTask(WordDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            dao.insert(words[0]);
            return null;
        }
    }
}
