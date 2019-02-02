package eco.linc.room.model.repository;

import android.util.Log;

import java.util.List;

import eco.linc.room.model.db.contact.ContactsDao;
import eco.linc.room.model.db.contact.User;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//SQL repository
public class Repository {

    private ContactsDao contactsDao;
    private static final String TAG = "Repository";

    public Repository(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    //get from room db
    public Observable<List<User>> getAllUsers() {
        return Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            try{
                emitter.onNext(contactsDao.getAll());
                emitter.onComplete();
            }catch (Exception e){
                emitter.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //insert new user into room database
    public Completable insertUsers(User user){
        return Completable.fromAction(
                () -> contactsDao.insertUser(user)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
