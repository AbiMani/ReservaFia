package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DocenteNuevoMenuActivity extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_nuevo_menu);

    }
    public void lanzarMenuD(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_D1:
                i = new Intent(this, ReservaEventoMenuActivity.class);
                break;
            case R.id.button_D2:
                i = new Intent(this,DetalleReservaMenuActivity.class);
                break;
            case R.id.button_D3:
                i = new Intent(this,GrupoMenuActivity.class);
                break;
            case R.id.button_D4:
                i = new Intent(this, DetalleGrupoReservaMenuActivity.class);
                break;
            case R.id.button_D5:
                i = new Intent(this,HorarioMenuActivity.class);
                break;
            case R.id.button_D6:
                i = new Intent(this,CargaAcademicaMenuActivity.class);
                break;
            case R.id.button_D7:
                i = new Intent(this,ServiciosMenuActivity.class);
                break;
        }
        if (i != null) startActivity(i);
    }


}
