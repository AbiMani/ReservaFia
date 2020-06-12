package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UsuarioExternoActivity extends Activity {   //cambiar activity correcto



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_externo);

    }
    public void lanzarMenuU(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_1:
                i = new Intent(this, ReservaEventoMenuActivity.class);
                break;
            case R.id.button_2:
                i = new Intent(this,DetalleReservaMenuActivity.class);
                break;
            case R.id.button_3:
                i = new Intent(this,GrupoMenuActivity.class);
                break;
            case R.id.button_4:
                i = new Intent(this, DetalleGrupoReservaMenuActivity.class);
                break;
            case R.id.button_5:
                i = new Intent(this,HorarioMenuActivity.class);
                break;
            case R.id.button_6:
                i = new Intent(this,ServiciosMenuActivity.class);
                break;
        }
        if (i != null) startActivity(i);
    }


}

