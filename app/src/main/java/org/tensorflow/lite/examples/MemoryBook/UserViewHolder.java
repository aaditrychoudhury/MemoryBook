package org.tensorflow.lite.examples.MemoryBook;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name, dob;


    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.cardName);
        dob = itemView.findViewById(R.id.cardDob);
        imageView = itemView.findViewById(R.id.cardImage);

    }
}
