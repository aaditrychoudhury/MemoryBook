package org.tensorflow.lite.examples.MemoryBook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserRecycler extends RecyclerView.Adapter<UserViewHolder> {

    List<User> data;

    public UserRecycler (List<User> users){
        data = users;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item_layout,
                parent,
                false
        );
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        //convert byte array to image and show
        holder.imageView.setImageBitmap(DataConverter.convertByteArray2Image(user.getImage()));
        holder.name.setText(user.getFullName());
        holder.dob.setText(String.valueOf(user.getDob()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

