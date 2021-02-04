package fit.hanu.currencyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtText;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        btnConvert = findViewById(R.id.btnConvert);


    }

    public void onClickConvert(View view) {
        int usd = Integer.parseInt(edtText.getText().toString());
        double vnd = usd * 23000;
        Toast.makeText(this, "Convert to VND: $" + usd + " = " + vnd + " VND", Toast.LENGTH_LONG).show();
    }
}