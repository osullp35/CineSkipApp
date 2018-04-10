package com.example.admin.cineskip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class movieTrailers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailers);

        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("rtsp://r2---sn-5hne6nsy.googlevideo.com/Cj0LENy73wIaNAmIlEsgS_nEfhMYDSANFC1tksRaMOCoAUIASARgn5jyoJHztcFaigELX0xRNzJXTmVhYncM/AF707D317478D65349E2BE69ABE49BFD638B0C87.259734A44C0B62E86D7BE5B96AB6ECE1F3E5D56B/yt6/1/video.3gp");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}
