package eco.linc.room.presentation.newUser;

import android.util.Log;

import eco.linc.room.model.db.contact.User;
import eco.linc.room.model.interactor.Interactor;
import io.reactivex.disposables.Disposable;

public class NewUserPresenter {

    private Interactor interactor;
    private static final String TAG = "NewUserPresenter";

    public NewUserPresenter(Interactor interactor) {
        this.interactor = interactor;
    }

    public void addUser(User user){
        Disposable d = interactor.addUser(user)
            .subscribe(
                    () -> Log.d(TAG, "user added "),
                    (e) -> e.printStackTrace()
            );
    }

}
