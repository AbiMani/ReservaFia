package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DocenteNuevoMenuActivity  extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener {
    private CardView reserva, detallereser, grupo, horario, carga, pdf;
    private GestureDetector gestureScanner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_nuevo_menu);
        gestureScanner = new GestureDetector(this);

        reserva = (CardView) findViewById(R.id.res);
        detallereser = (CardView) findViewById(R.id.deres);
        grupo = (CardView) findViewById(R.id.gr);
        horario = (CardView) findViewById(R.id.hor);
        carga = (CardView) findViewById(R.id.rcar);
        pdf = (CardView) findViewById(R.id.pdf);

        reserva.setOnClickListener(this);
        detallereser.setOnClickListener(this);
        grupo.setOnClickListener(this);
        horario.setOnClickListener(this);
        carga.setOnClickListener(this);
        pdf.setOnClickListener(this);


    }
    public void lanzarMenuD(View v) {

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

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.res:
                i = new Intent(this, ReservaEventoMenuActivity.class);
                break;
            case R.id.deres:
                i = new Intent(this,DetalleReservaMenuActivity.class);
                break;
            case R.id.gr:
                i = new Intent(this,GrupoMenuActivity.class);
                break;

            case R.id.hor:
                i = new Intent(this,HorarioMenuActivity.class);
                break;
            case R.id.rcar:
                i = new Intent(this,CargaAcademicaMenuActivity.class);
                break;
            case R.id.pdf:
                i = new Intent(this,ExportarPdfActivity.class);
                break;
        }
        if (i != null) startActivity(i);
    }
}
