package eco.linc.room.presentation.contacts;

import android.util.Log;

import eco.linc.room.model.interactor.Interactor;
import io.reactivex.disposables.Disposable;

public class ContactsPresenter {
    private ContactsView view;
    private Interactor interactor;
    private static final String TAG = "ContactsPresenter";

    public ContactsPresenter(ContactsView view, Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getContacts(){
        Disposable d = interactor.getAllUsers()
                .subscribe(
                        (userList) -> {
                            view.setContacts(userList);
                            Log.d(TAG, "getContacts: " + userList.get(0).getName());
                        },
                        (e) -> e.printStackTrace()
                );
    }
}
