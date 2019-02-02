package eco.linc.room.model.db.contact;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contacts")
    List<User> getAll();

    @Insert
    void insertUser(User user);

}
