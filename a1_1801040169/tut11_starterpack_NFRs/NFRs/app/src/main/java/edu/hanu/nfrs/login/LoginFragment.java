package edu.hanu.nfrs.login;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.hanu.nfrs.R;
import edu.hanu.nfrs.login.db.DbHelper;
import edu.hanu.nfrs.login.db.UserManager;
import edu.hanu.nfrs.login.models.User;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        // refs
        EditText edtUsername = v.findViewById(R.id.edtUsername);
        EditText edtPassword = v.findViewById(R.id.edtPassword);

        // login
        Button btnLogin = v.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                UserManager userManager = UserManager.getInstance(getActivity());
                User user = userManager.getByUsernameAndPassword(username, password);

                if (user == null) {
                    Toast.makeText(getActivity(), "Invalid username or password!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Welcome " + user.getName() + "!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // exit
        Button btnExit = v.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        return v;
    }
}