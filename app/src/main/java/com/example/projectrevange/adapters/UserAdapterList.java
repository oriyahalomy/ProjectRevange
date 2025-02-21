package com.example.projectrevange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectrevange.models.User;

public class UserAdapterList  extends ArrayAdapter<User> {

    public UserAdapterList(Context context, List<User> users) {
        super(context, android.R.layout.simple_spinner_item, users); // You can customize the layout
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Inflate the layout for a simple spinner item
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        User user = getItem(position); // Get the current user object

        TextView textView = convertView.findViewById(android.R.id.text1); // Default text view in simple_spinner_item
        if (user != null) {
            textView.setText(user.getfName() + " " + user.getlName()); // Set the user’s name to the TextView
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Use the same layout for the dropdown view
        return getView(position, convertView, parent);
    }
}
