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

public class NewAccountFragment extends Fragment implements View.OnClickListener {
    private EditText edtUsername, edtPassword, edtConfirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);

        // refs
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        Button btnCreate = view.findViewById(R.id.btnCreate);
        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnExit = view.findViewById(R.id.btnExit);

        // events
        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        // landscape -> hide btnExit
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
//        int rotation = getActivity().getDisplay().getRotation(); // later version

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            btnExit.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                Toast.makeText(getActivity(), "Creating...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnClear:
                edtUsername.setText("");
                edtPassword.setText("");
                edtConfirmPassword.setText("");
                break;
            case R.id.btnExit:

                FragmentManager manager = getFragmentManager();
                manager.popBackStack();

                break;
        }
    }
}