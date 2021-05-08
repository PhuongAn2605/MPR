package fit.hanu.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    public static final String P_LANG = "LANG";
    private SharedPreferences sharedPreferences;
    private TextView tvLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //check for language reference
        tvLanguage = findViewById(R.id.tvLanguage);
        //check for language reference
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String language = sharedPreferences.getString(P_LANG, null);
        //if not set yet
            //display dialog for selecting language
        //else display previously selected language

        if(language != null){
            tvLanguage.setText(language);
        }else{
            selectLanguage();
        }
    }

    public void selectLanguage() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("Vietnamese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //set preferences
                        //update textview
                        setLanguage("Vietnamese");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //set preferences
                        //update textview
                        setLanguage("English");
                    }
                }).show();
    }

    public void setLanguage(String language){
        sharedPreferences.edit().putString(P_LANG, language).apply();

        //update textview
        tvLanguage.setText(language);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.vn:
                setLanguage("Vietnamese");
//                Toast.makeText(this, "Clicked: Vietnamese", Toast.LENGTH_SHORT).show();
                break;
            case R.id.en:
                setLanguage("English");
//                Toast.makeText(this, "Clicked: English", Toast.LENGTH_SHORT).show();
                break;
                //clear the reference
            case R.id.clear:
//            sharedPreferences.edit().putString(P_LANG, null).apply();
            sharedPreferences.edit().clear().apply();
        }

        return super.onOptionsItemSelected(item);
    }
}