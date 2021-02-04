package fit.hanu.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view){
        TextView firstName = findViewById(R.id.txtFirstName);
        TextView lastName = findViewById(R.id.txtLastName);
        TextView email = findViewById(R.id.txtEmail);

        EditText f_name = findViewById(R.id.edtFirstName);
        EditText l_name = findViewById(R.id.edtLastName);
        EditText e_mail = findViewById(R.id.edtEmail);

        firstName.setText(f_name.getText().toString());
        lastName.setText(l_name.getText().toString());
        email.setText(e_mail.getText().toString());
    }
}