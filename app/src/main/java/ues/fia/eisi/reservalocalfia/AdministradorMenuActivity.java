package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;

public class AdministradorMenuActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener {   //cambiar activity correcto
    private CardView usuario, escuela, tipolocal, docente, asignatura, roldoc, ciclo, diano;
    private GestureDetector gestureScanner;
    ImageButton asistente, stop;
    TextToSpeech tts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureScanner = new GestureDetector(this);
        setContentView(R.layout.activity_administrador_menu);
        usuario = (CardView) findViewById(R.id.usuario);
        escuela = (CardView) findViewById(R.id.escuela);
        tipolocal = (CardView) findViewById(R.id.tipoLocal);
        docente = (CardView) findViewById(R.id.docente);
        asignatura = (CardView) findViewById(R.id.asignatura);
        roldoc = (CardView) findViewById(R.id.rol);
        ciclo = (CardView) findViewById(R.id.ciclo);
        diano = (CardView) findViewById(R.id.dianohabil);

        usuario.setOnClickListener(this);
        escuela.setOnClickListener(this);
        tipolocal.setOnClickListener(this);
        diano.setOnClickListener(this);
        docente.setOnClickListener(this);
        asignatura.setOnClickListener(this);
        roldoc.setOnClickListener(this);
        ciclo.setOnClickListener(this);

        //TEXT TO SPEECH
        asistente=(ImageButton) findViewById(R.id.btn_asistente);
        stop=(ImageButton) findViewById(R.id.btn_stop);
        tts = new TextToSpeech(this,OnInit);
        asistente.setOnClickListener(asistant);
        stop.setOnClickListener(onClik);
    }


    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.usuario:
                i = new Intent(this, UsuariosMenuActivity.class);
                break;

            case R.id.escuela:
                i = new Intent(this, EscuelaMenuActivity.class);
                break;

            case R.id.tipoLocal:
                i = new Intent(this, TipoLocalMenuActivity.class);
                break;

            case R.id.docente:
                i = new Intent(this, DocenteMenuActivity.class);
                break;
            case R.id.asignatura:
                i = new Intent(this, AsignaturaMenuActivity.class);
                break;

            case R.id.rol:
                i = new Intent(this, RolDocenteMenuActivity.class);
                break;
            case R.id.ciclo:
                i = new Intent(this, CicloMenuActivity.class);
                break;
            case R.id.dianohabil:
                i = new Intent(this, DiasNoHabilesMenuActivity.class);
                break;
            default: break;

        }
        if (i != null) startActivity(i);

    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Intent intent= new Intent(this,TipoLocalInsertarActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Intent intent= new Intent(this,EscuelaInsertarActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Intent intent= new Intent(this,DocenteInsertarActivity.class);
        startActivity(intent);
        return true;
    }
    //CODIGO DE TEXT TO SPEECH
    View.OnClickListener asistant=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CountDownTimer countDownTimer = new CountDownTimer(5000, 1500) {

                @Override
                public void onTick(long millisUntilFinished) {
                    speakOut("Bienvenido, te dare una breve explicacion de esta aplicacion. Empecemos." +
                            "Realiza un toque en un espacio en blanco para insertar tipo de local. " +
                            "Haz una accion de arrastre para insertar un docente. " +
                            "Manten presionado un espacio en blanco para insertar una escuela. " );
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    };
    TextToSpeech.OnInitListener OnInit = new TextToSpeech.OnInitListener() {

        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if (TextToSpeech.SUCCESS==status){
                tts.setLanguage(new Locale("spa","ESP"));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "TTS no disponible", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void onDestroy(){
        tts.shutdown();
        super.onDestroy();
    }
    private void speakOut(String text){
        tts.speak(text, TextToSpeech.QUEUE_ADD, null);
    }

    View.OnClickListener onClik=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tts.stop();
        }
    };
}

