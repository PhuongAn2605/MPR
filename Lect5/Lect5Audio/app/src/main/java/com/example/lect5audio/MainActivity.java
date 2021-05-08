package com.example.lect5audio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final int AUDIO_CAPTURED = 1;
    private Uri audioUri;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecord:
                Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(recordIntent, AUDIO_CAPTURED);

                break;

            case R.id.btnPlay:
                if (audioUri != null) {
                    mediaPlayer = MediaPlayer.create(this, audioUri);
                    mediaPlayer.start();
                }
                break;

            case R.id.btnStop:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("HERE", requestCode+"-"+resultCode + "-" + data.getData().toString());

        if (resultCode == RESULT_OK && requestCode == AUDIO_CAPTURED) {
            audioUri = data.getData();

        }
    }
}
