package eco.linc.room.model.interactor;

import java.util.List;

import eco.linc.room.model.db.contact.User;
import eco.linc.room.model.repository.Repository;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class Interactor {

    private Repository repository;

    public Interactor(Repository repository) {
        this.repository = repository;
    }

    public Observable<List<User>> getAllUsers(){
        return repository.getAllUsers();
    }

    public Completable addUser(User user){
        return repository.insertUsers(user);
    }
}
