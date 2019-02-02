package eco.linc.room.ui.addUser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import eco.linc.room.R;
import eco.linc.room.model.db.AppDatabase;
import eco.linc.room.model.db.contact.User;
import eco.linc.room.model.interactor.Interactor;
import eco.linc.room.model.repository.Repository;
import eco.linc.room.presentation.newUser.NewUserPresenter;
import eco.linc.room.presentation.newUser.NewUserView;

public class NewContactFragment extends Fragment implements View.OnClickListener, NewUserView {

    private EditText userName;
    private View view;
    private static final String TAG = "NewContactFragment";

    private NewUserPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database init
        AppDatabase CONTACTS = AppDatabase.getDatabase(getActivity());

        //presenter init
        if(presenter == null){
            presenter = new NewUserPresenter(new Interactor(new Repository(CONTACTS.contactsDao())));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_user_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //add new contact widgets
        Button addNewUser = view.findViewById(R.id.add_user);
        userName = view.findViewById(R.id.contact_name);

        //add user
        addNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_user:
                Log.d(TAG, "onClick: " + userName.getText().toString());
                presenter.addUser(new User(userName.getText().toString()));

                //return to previous fragment
                getFragmentManager()
                        .popBackStack();
                showMessage();
                break;
        }
    }

    @Override
    public void showMessage() {
        Toast.makeText(getActivity(), "Created", Toast.LENGTH_SHORT).show();
    }
}
