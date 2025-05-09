package com.example.projectrevange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRevengeAdapter extends RecyclerView.Adapter<UserRevengeAdapter.ViewHolder> {

    public interface UserRevengeCallback {
        void onClick(User user);
    }

    private final List<Revenge> revengeList;
    private final List<User> usersList;
    private final UserRevengeCallback userRevengeCallback;

    public UserRevengeAdapter(Context context, @Nullable UserRevengeCallback userRevengeCallback) {
        this.userRevengeCallback = userRevengeCallback;
        this.revengeList = new ArrayList<>();
        this.usersList = new ArrayList<>();
    }

    public void setRevengeList(List<Revenge> revengeList) {
        this.revengeList.clear();
        this.revengeList.addAll(revengeList);
        this.notifyDataSetChanged();
    }

    public void setUserList(List<User> userList) {
        this.usersList.clear();
        this.usersList.addAll(userList);
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserRevengeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_revenge, parent, false);
        return new UserRevengeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRevengeAdapter.ViewHolder holder, int position) {
        User user = this.usersList.get(position);
        if (user == null) return;
        holder.tvname.setText(user.getfName() + " " + user.getlName());
        int from = 0;
        int to = 0;
        for (Revenge revenge : this.revengeList) {
            if (revenge.getUserIdFrom().equals(user.getUid())) from++;
            if (revenge.getUserIdTo().equals(user.getUid())) to++;
        }
        holder.tvFrom.setText("from: "+ from);
        holder.tvTo.setText("to: "+ to);
        holder.itemView.setOnClickListener(v -> userRevengeCallback.onClick(user));
    }

    @Override
    public int getItemCount() {
        if (revengeList.isEmpty()) return 0;
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvFrom, tvTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_item_user_revenge_name);
            tvFrom = itemView.findViewById(R.id.tv_item_user_revenge_from);
            tvTo = itemView.findViewById(R.id.tv_item_user_revenge_to);
        }

    }
}
