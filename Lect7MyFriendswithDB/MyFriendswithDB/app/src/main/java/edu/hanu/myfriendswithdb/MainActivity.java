package edu.hanu.myfriendswithdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.myfriendswithdb.adapters.FriendAdapter;
import edu.hanu.myfriendswithdb.db.FriendManager;
import edu.hanu.myfriendswithdb.models.Friend;

public class MainActivity extends AppCompatActivity {
    public static final int FRIEND_ADDED = 1;

    private RecyclerView rvFriends;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;
    private FriendManager friendManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dataset
        friendManager = FriendManager.getInstance(this);
        friends = friendManager.all();

        // adapter
        friendAdapter = new FriendAdapter(friends);

        // recycler view
        rvFriends = findViewById(R.id.rvFriends);
        rvFriends.setAdapter(friendAdapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        ImageButton btnAdd = findViewById(R.id.btnOK);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADDED);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == FRIEND_ADDED) {
            friends.clear();
            friends.addAll(friendManager.all());

            friendAdapter.notifyDataSetChanged();
        }
    }
}
