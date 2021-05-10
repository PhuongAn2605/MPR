package fit.hanu.myteams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import fit.hanu.myteams.ProfileFragment.ImageLoader;
import fit.hanu.myteams.ProfileFragment.RestLoader;

public class MainActivity extends AppCompatActivity{
    private Button btnIncrease;
    private Button btnDecrease;
    private int id = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new ProfileFragment();

        manager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();

//        btnIncrease = findViewById(R.id.btnIncrease);
//        btnDecrease = findViewById(R.id.btnDecrease);
//
//        btnIncrease.setOnClickListener(this);
//        btnDecrease.setOnClickListener(this);

//        this.id = id;


    }


//    @Override
//    public void onClick(View view) {
////        int id = 0;
////        this.id = id;
//        FragmentManager manager = getSupportFragmentManager();
//        Fragment fragment = new ProfileFragment();
//        String urlText, urlImage;
//        ImageLoader imageLoader;
//        RestLoader restLoader;
//
////        Bundle data = new Bundle();
//        switch (view.getId()){
//            case R.id.btnDecrease:
//                if(id >0 ){
//                    id --;
//                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
//                    restLoader = new RestLoader();
//                    restLoader.execute(urlText);
////
//                    urlImage = "https://robohash.org/1?set=set" + id;
//                    imageLoader = new ImageLoader();
//                    imageLoader.execute(urlImage);
//
////                    data.putString("URLTEXT", urlText);
//////                    data.putString("URLIMAGE", urlImage);
////                    fragment.setArguments(data);
//
//
//
//                    manager.beginTransaction()
//                            .replace(R.id.fragmentContainer, fragment)
//                            .addToBackStack(null)
//                            .commit();
//
//                    Log.i("ID", "" + id);
//                }
//                break;
//            case R.id.btnIncrease:
//                if(id < 5){
//                    id ++;
//
//                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
//                    restLoader = new RestLoader();
//                    restLoader.execute(urlText);
////
////
//                    urlImage = "https://robohash.org/1?set=set" + id;
//                    imageLoader = new ImageLoader();
//                    imageLoader.execute(urlImage);
//
////                    data.putString("URLTEXT", urlText);
//////                    data.putString("URLIMAGE", urlImage);
////                    fragment.setArguments(data);
//
//
//
//                    manager.beginTransaction()
//                            .replace(R.id.fragmentContainer, fragment)
//                            .addToBackStack(null)
//                            .commit();
//                }
//                break;
//
//        }
//    }
}