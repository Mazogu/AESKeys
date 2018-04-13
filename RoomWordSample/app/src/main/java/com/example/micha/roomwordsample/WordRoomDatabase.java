package com.example.micha.roomwordsample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by micha on 4/12/2018.
 */

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;
    static WordRoomDatabase getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateAsync(INSTANCE).execute();
        }
    };

    private static class PopulateAsync extends AsyncTask<Void,Void,Void>{

        private final WordDao dao;

        PopulateAsync(WordRoomDatabase db){
            this.dao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            dao.insert(new Word("Hello"));
            dao.insert(new Word("World"));
            return null;
        }
    }
}
