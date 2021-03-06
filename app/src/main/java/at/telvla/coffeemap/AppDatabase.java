package at.telvla.coffeemap;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Coffee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoffeeDao coffeeDao();
}