package eco.linc.room.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import eco.linc.room.model.db.contact.ContactsDao;
import eco.linc.room.model.db.contact.User;

@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactsDao contactsDao();

    private static AppDatabase database;

    public static AppDatabase getDatabase(Context mContext){
        if(database == null){
            synchronized (AppDatabase.class) {
                if(database == null) {
                    database = Room.databaseBuilder(mContext, AppDatabase.class, "contacts_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return database;
    }
}

