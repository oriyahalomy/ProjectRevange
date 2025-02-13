package com.example.projectrevange.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;

import java.util.List;

public class RevengeAdapter extends RecyclerView.Adapter<RevengeAdapter.RevengeViewHolder> {

    private final List<Revenge> revengeList;
    private Context context;

    public RevengeAdapter(List<Revenge> revengeList, Context context) {
        this.revengeList = revengeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RevengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_revenge, parent, false);
        return new RevengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevengeViewHolder holder, int position) {
        Revenge Revenge = revengeList.get(position);
        holder.revengeTitle.setText(Revenge.getTitle());
        holder.revengeUser.setText(Revenge.getUserIdTo());
        holder.revengeHow.setText(Revenge.getHowRevenge());
        holder.revengeReason.setText(Revenge.getReason());
    }

    @Override
    public int getItemCount() {
        return revengeList.size();
    }

    static class RevengeViewHolder extends RecyclerView.ViewHolder {
        TextView revengeTitle,revengeUser,revengeHow,revengeReason;

        public RevengeViewHolder(@NonNull View itemView) {
            super(itemView);
            revengeTitle = itemView.findViewById(R.id.revengeTitle);
            revengeUser = itemView.findViewById(R.id.revengeUser);
            revengeHow = itemView.findViewById(R.id.revengeHow);
            revengeReason = itemView.findViewById(R.id.revengeReason);
        }
    }

}
