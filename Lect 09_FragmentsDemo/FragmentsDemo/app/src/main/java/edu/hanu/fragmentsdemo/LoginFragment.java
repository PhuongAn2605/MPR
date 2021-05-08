package edu.hanu.fragmentsdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
//        int rotation = getActivity().getDisplay().getRotation(); // later version
        View view;

        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            view = inflater.inflate(R.layout.fragment_login, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_login_landscape, container, false);
        }

        // refs
        EditText edtUsername = view.findViewById(R.id.edtUsername);
        EditText edtPassword = view.findViewById(R.id.edtPassword);

        Button btnLogin = view.findViewById(R.id.btnLogin);
        Button btnNewAccount = view.findViewById(R.id.btnNewAccount);
        Button btnExit = view.findViewById(R.id.btnExit);

        // events
        btnLogin.setOnClickListener(this);
        btnNewAccount.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        // landscape -> hide btnNewAccount
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            btnNewAccount.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Toast.makeText(getActivity(), "Logging in...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNewAccount:
                FragmentManager manager = getFragmentManager();
                Fragment fragment = new NewAccountFragment();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("fragment_login")
                        .commit();
                break;
            case R.id.btnExit:
                getActivity().finish();
                break;
        }
    }
}