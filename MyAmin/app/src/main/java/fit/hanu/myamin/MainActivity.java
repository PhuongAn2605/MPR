package fit.hanu.myamin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        character = findViewById(R.id.imgStar);

        Button btnSpin = findViewById(R.id.btnSpin);
        Button btnTurnArround = findViewById(R.id.btnTurnArround);
        Button btnJump = findViewById(R.id.btnJump);
        Button btnClap = findViewById(R.id.btnClap);


        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.animate().rotationBy(360*2).setDuration(2000);
            }
        });

        btnTurnArround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.animate().rotationYBy(180*2).setDuration(1000);
            }
        });

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.animate().translationY(-200).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        character.animate().translationY(200).setDuration(1000);
                    }
                });
            }
        });

        btnClap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer clapping = MediaPlayer.create(MainActivity.this, R.raw.clapping);
                if(clapping.isPlaying()){
                    clapping.reset();
                }else{
                    clapping.start();
                }
            }
        });

    }

    public void onCLickChangeImage(View view){
        if(view == findViewById(R.id.imgBtnStar)){
            character = findViewById(R.id.imgStar);
            findViewById(R.id.imgStar).animate().alpha(1).setDuration(2000);
            findViewById(R.id.imgCircle).animate().alpha(0).setDuration(2000);
        }else if(view == findViewById(R.id.imgBtnCircle)){
            character = findViewById(R.id.imgCircle);
            findViewById(R.id.imgStar).animate().alpha(0).setDuration(1000);
            findViewById(R.id.imgCircle).animate().alpha(1).setDuration(1000);
        }
    }
}