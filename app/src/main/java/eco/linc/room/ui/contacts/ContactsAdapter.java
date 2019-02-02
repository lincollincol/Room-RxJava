package eco.linc.room.ui.contacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eco.linc.room.R;
import eco.linc.room.model.db.contact.User;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<User> contacts = new ArrayList<>();

    void setContacts(List<User> contacts){
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(this.contacts.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.contact_item);
            name = itemView.findViewById(R.id.contact_name);
        }
    }
}
