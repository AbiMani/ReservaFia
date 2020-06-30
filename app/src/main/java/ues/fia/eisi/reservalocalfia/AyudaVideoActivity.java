package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.VideoView;
import android.widget.MediaController;

import java.io.File;

public class AyudaVideoActivity extends Activity {
    Button Play;
    Button Stop;
    VideoView video;
    MediaController mediacontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_video);
        video = (VideoView) findViewById(R.id.video);
        String videoPATH ="android.resource://" + getPackageName()+"/"+ R.raw.video_ayuda;
        Uri uri= Uri.parse(videoPATH);
        video.setVideoURI(uri);

        MediaController mediaController =new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

    }
}
