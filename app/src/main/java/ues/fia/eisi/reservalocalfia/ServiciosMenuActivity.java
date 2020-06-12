package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServiciosMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_menu);
    }

    public void lanzarActividad(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_sw1:
                i = new Intent(this,InsertReservaActivity.class);
                break;
            case R.id.button_sw2:
                i = new Intent(this,ConsultarCargaAcademicaActivity.class);
                break;
            case R.id.button_sw3:
                i = new Intent(this,RolDocenteInsertarWsActivity.class);
                break;
            case R.id.button_sw4:
                i = new Intent(this, InsertarEscuelaActivity.class);
                break;
            case R.id.button_sw5:
                i = new Intent(this,DiaInsertActivity.class);
                break;
            case R.id.button_sw6:
                i = new Intent(this, ConsultarReservasActivity.class);
                break;
            case R.id.button_sw7:
                i = new Intent(this,CicloInsertWActivity.class);
                break;
            case R.id.button_sw8:
                i = new Intent();
                break;
        }
        if (i != null) startActivity(i);
    }

}
