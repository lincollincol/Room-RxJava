package eco.linc.room.ui.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import eco.linc.room.R;
import eco.linc.room.model.db.AppDatabase;
import eco.linc.room.model.db.contact.User;
import eco.linc.room.model.interactor.Interactor;
import eco.linc.room.model.repository.Repository;
import eco.linc.room.presentation.contacts.ContactsPresenter;
import eco.linc.room.presentation.contacts.ContactsView;
import eco.linc.room.ui.addUser.NewContactFragment;

public class ContactsFragment extends Fragment implements ContactsView, View.OnClickListener {

    private View view;
    private ContactsAdapter adapter;

    private ContactsPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inital database
        AppDatabase CONTACTS = AppDatabase.getDatabase(getActivity());

        //initial presenter
        if(presenter == null){
            presenter = new ContactsPresenter(this, new Interactor(new Repository(CONTACTS.contactsDao())));
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contacts_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //adapter
        RecyclerView recyclerView = view.findViewById(R.id.contacts_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ContactsAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(adapter);

        FloatingActionButton newUser = view.findViewById(R.id.add_user);
        newUser.setOnClickListener(this);

        presenter.getContacts();

    }

    @Override
    public void setContacts(List<User> users) {
        adapter.setContacts(users);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new NewContactFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
