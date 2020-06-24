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
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/", "video_login.3gp");
        if (f.exists()) {
            Uri uri = Uri.fromFile(f);
            video.setVideoURI(uri);
            mediacontrol = new MediaController(this);
            video.setMediaController(mediacontrol);
            mediacontrol.show();
        }
    }
}
