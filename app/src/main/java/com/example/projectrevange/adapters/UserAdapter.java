package com.example.projectrevange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>  {

    public interface DeleteUserCallback {
        void deleteUser(int position);
    }

    List<User> users;
    Context context;
    DeleteUserCallback deleteUserCallback;
    public UserAdapter(Context context, List<User> users, @Nullable DeleteUserCallback deleteUserCallback) {
        this.users= users;
        this.context = context;
        this.deleteUserCallback = deleteUserCallback;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.email.setText(user.getEmail());
        holder.fName.setText(user.getfName());
        holder.LName.setText(user.getlName());
        holder.Password.setText(user.getPassword());

        final int pos = position;
        holder.itemView.setOnLongClickListener(v -> {
            if (deleteUserCallback != null) {
                deleteUserCallback.deleteUser(pos);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView fName,LName,email,Password;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.fName);
            LName = itemView.findViewById(R.id.LName);
            email = itemView.findViewById(R.id.email);
            Password = itemView.findViewById(R.id.Password);
        }
    }

}

