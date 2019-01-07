package at.telvla.coffeemap;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseCreator {
    private static AppDatabase personDatabase;
    private static final Object LOCK = new Object();

    public synchronized static AppDatabase getPersonDatabase(Context context){
        if(personDatabase == null) {
            synchronized (LOCK) {
                if (personDatabase == null) {
                    personDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, "database4").build();
                }
            }
        }
        return personDatabase;
    }
}
