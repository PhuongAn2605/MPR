package fit.hanu.myteams;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    private static TextView txtName;
    private static TextView txtEmail;
    private static ImageView imageAvatar;
    private ImageButton btnIncrease, btnDecrease;

    private int id=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);

        btnIncrease = view.findViewById(R.id.btnIncrease);
        btnDecrease = view.findViewById(R.id.btnDecrease);



        imageAvatar = view.findViewById(R.id.imgAvatar);

        String urlText = "https://jsonplaceholder.typicode.com/users/1";
        RestLoader restLoader = new RestLoader();
        restLoader.execute(urlText);

        String urlImage = "https://robohash.org/1?set=set3";
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.execute(urlImage);

//        String urlText = getArguments().getString("URLTEXT");
//        Log.i("URLTEXT", urlText);
//        RestLoader restLoader = new RestLoader();
//        restLoader.execute(urlText);

//        String urlImage = getArguments().getString("URLIMAGE");
//        Log.i("URLTEXT", urlText);
//
//        ImageLoader imageLoader = new ImageLoader();
//        imageLoader.execute(urlImage);

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);

        return view;
    }

        @Override
    public void onClick(View view) {
//        int id = 0;
//        this.id = id;
//        FragmentManager manager = getFragmentManager();
//        Fragment fragment = new ProfileFragment();
        String urlText, urlImage;
        ImageLoader imageLoader;
        RestLoader restLoader;

//        Bundle data = new Bundle();
        switch (view.getId()){
            case R.id.btnDecrease:
                if(id >0 ){
                    id --;
                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
                    restLoader = new RestLoader();
                    restLoader.execute(urlText);
//
                    urlImage = "https://robohash.org/"+ id + "?set=set=2";
                    imageLoader = new ImageLoader();
                    imageLoader.execute(urlImage);

//                    data.putString("URLTEXT", urlText);
////                    data.putString("URLIMAGE", urlImage);
//                    fragment.setArguments(data);


//
//                    manager.beginTransaction()
//                            .replace(R.id.fragmentContainer, fragment)
//                            .addToBackStack(null)
//                            .commit();

                    Log.i("ID-DECREASE", "" + id);
                }
                break;
            case R.id.btnIncrease:
                if(id < 5){
                    id ++;
                    Log.i("ID-INCREASE", "" + id);


                    urlText = "https://jsonplaceholder.typicode.com/users/" + id;
                    restLoader = new RestLoader();
                    restLoader.execute(urlText);
//
//
                    urlImage = "https://robohash.org/"+ id + "?set=set=2";
                    imageLoader = new ImageLoader();
                    imageLoader.execute(urlImage);

//                    data.putString("URLTEXT", urlText);
////                    data.putString("URLIMAGE", urlImage);
//                    fragment.setArguments(data);



//                    manager.beginTransaction()
//                            .replace(R.id.fragmentContainer, fragment)
//                            .addToBackStack(null)
//                            .commit();
                }
                break;

        }
    }

    public static class RestLoader extends AsyncTask<String, Void, String> {
//        private TextView txtName, txtEmail;


        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                }
                Log.i("RESULT", "" + result);
                return result.toString();
            }catch (MalformedURLException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
//            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                JSONObject obj = new JSONObject(result);
                String name = obj.getString("name");

                String email = obj.getString("email");
                txtName.setText(name);
                txtEmail.setText(email);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public static class ImageLoader extends AsyncTask<String, Void, Bitmap> {



        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageAvatar.setImageBitmap(bitmap);
        }
    }
}