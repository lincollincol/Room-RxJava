package eco.linc.room.presentation.contacts;

import java.util.List;
import eco.linc.room.model.db.contact.User;

public interface ContactsView {

    void setContacts(List<User> users);

}
