package fit.hanu.mytube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textLink = findViewById(R.id.link);
        ImageButton btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = textLink.getText().toString();
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("LINK", link + "");
                startActivity(intent);
            }
        });
    }
}