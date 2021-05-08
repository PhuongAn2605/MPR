package edu.hanu.nfrs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.hanu.nfrs.asynctask.AsyncTaskFragment;
import edu.hanu.nfrs.login.LoginFragment;
import edu.hanu.nfrs.tictactoe.TicTacToeFragment;
import edu.hanu.nfrs.webview.WebViewFragment;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private Button btnTicTacToe, btnSQLInjection, btnAsyncTask, btnWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        btnTicTacToe = v.findViewById(R.id.btnTicTacToe);
        btnTicTacToe.setOnClickListener(this);

        btnSQLInjection = v.findViewById(R.id.btnSQLInjection);
        btnSQLInjection.setOnClickListener(this);

        btnAsyncTask = v.findViewById(R.id.btnAsyncTask);
        btnAsyncTask.setOnClickListener(this);

        btnWebView = v.findViewById(R.id.btnWebView);
        btnWebView.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.btnTicTacToe:
                fragment = new TicTacToeFragment();
                break;

            case R.id.btnSQLInjection:
                fragment = new LoginFragment();
                break;

            case R.id.btnAsyncTask:
                fragment = new AsyncTaskFragment();
                break;

            case R.id.btnWebView:
                fragment = new WebViewFragment();
                break;
        }

        transaction.replace(R.id.fragmentContainer, fragment)
                .addToBackStack("menu_fragment")
                .commit();
    }
}