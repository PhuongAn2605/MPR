package fit.hanu.logmein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtName;
    private EditText txtPassword;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);

    }

    public void onClickButton(View view){
        name = txtName.getText().toString();
        password = txtPassword.getText().toString();
        Log.i("Infor", "name: " + name);

        if(name.equals("admin") && password.equals("admin")){
            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Incorrect username or password!", Toast.LENGTH_SHORT).show();
        }
    }
}