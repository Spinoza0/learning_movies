package com.spinoza.movies.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.spinoza.movies.domain.MovieDao;
import com.spinoza.movies.domain.movies.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "movies.db";
    private static MovieDatabase instance = null;

    public static MovieDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            application,
                            MovieDatabase.class,
                            DATABASE_NAME)
                    .build();
        }
        return instance;
    }

    public abstract MovieDao movieDao();
}
