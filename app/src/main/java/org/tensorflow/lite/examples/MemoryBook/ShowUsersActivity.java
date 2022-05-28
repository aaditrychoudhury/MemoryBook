package org.tensorflow.lite.examples.MemoryBook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShowUsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserD userD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        recyclerView = findViewById(R.id.userRecyclerView);

        userD = UserDB.getDBInstance(this).userD();
        UserRecycler userRecycler = new UserRecycler(userD.getAllUsers());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userRecycler);
    }
}