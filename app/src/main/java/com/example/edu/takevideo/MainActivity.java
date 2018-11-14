package com.example.edu.takevideo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonTakeCamera;
    VideoView videoView;
    final int REQUEST_CODE = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Design 연결
        buttonTakeCamera = findViewById(R.id.buttonTakeCamera);
        buttonTakeCamera.setOnClickListener(this);
        videoView = findViewById(R.id.videoView);
    }

    @Override
    public void onClick(View view) {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            // 카메라 실행
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            Uri videoUri = data.getData(); // 데이터가 많아서 Uri로 가져옴
            Toast.makeText(this, "" + videoUri, Toast.LENGTH_SHORT);
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }
}
