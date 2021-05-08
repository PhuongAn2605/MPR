package fit.hanu.mytube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        String link = intent.getStringExtra("LINK");

        Toast.makeText(this, "This is a link: " + link, Toast.LENGTH_SHORT).show();

//        VideoView videoView = findViewById(R.id.videoView);
//        videoView.setVideoPath(link);
//        videoView.setMediaController((new MediaController(this)));
//
//        videoView.start();
    }
}