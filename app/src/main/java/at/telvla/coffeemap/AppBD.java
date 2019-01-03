package at.telvla.coffeemap;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppBD extends Application {

    public static AppBD instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static AppBD getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}