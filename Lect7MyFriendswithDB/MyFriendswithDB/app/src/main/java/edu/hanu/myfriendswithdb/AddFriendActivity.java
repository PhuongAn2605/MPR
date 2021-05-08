package edu.hanu.myfriendswithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.hanu.myfriendswithdb.models.Friend;
import edu.hanu.myfriendswithdb.db.FriendManager;

public class AddFriendActivity extends AppCompatActivity {

    private EditText edtName, edtPhoneNo, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        edtName = findViewById(R.id.edtName);
        edtPhoneNo = findViewById(R.id.edtPhoneNo);
        edtEmail = findViewById(R.id.edtEmail);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                String name = edtName.getText().toString();
                String phoneNo = edtPhoneNo.getText().toString();
                String email = edtEmail.getText().toString();

                if (name.equals("") || phoneNo.equals("") || email.equals("")) {
                    Toast.makeText(this, "Missing entry", Toast.LENGTH_SHORT).show();
                } else {
                    FriendManager friendManager = FriendManager.getInstance(this);

                    Friend friend = new Friend(name, phoneNo, email);
                    friendManager.add(friend);
                    Toast.makeText(this, "New record inserted", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK);
                    finish();
                }
                break;
            case R.id.btnCancel:
                finish();

                break;
        }
    }
}
