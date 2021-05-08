package com.example.lect5video;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public static final int VIDEO_CAPTURED = 1;
    private VideoView videoView;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecord:
                Intent recordIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(recordIntent, VIDEO_CAPTURED);
                break;

            case R.id.btnPlay:
                videoView.setVideoURI(videoUri);
                videoView.start();
                break;

            case R.id.btnStop:
                videoView.stopPlayback();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == VIDEO_CAPTURED) {
            videoUri = data.getData();
        }
    }
}
