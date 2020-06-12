package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdministradorMenuActivity extends Activity {   //cambiar activity correcto


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_menu);

    }
    public void lanzarMenuA(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_A1:
                i = new Intent(this, UsuariosMenuActivity.class);
                break;
            case R.id.button_A2:
                i = new Intent(this,EscuelaMenuActivity.class);
                break;
            case R.id.button_A3:
                i = new Intent(this,TipoLocalMenuActivity.class);
                break;
            case R.id.button_A4:
                i = new Intent(this, DocenteMenuActivity.class);
                break;
            case R.id.button_A5:
                i = new Intent(this,RolDocenteMenuActivity.class);
                break;
            case R.id.button_A6:
                i = new Intent(this, AsignaturaMenuActivity.class);
                break;
            case R.id.button_A7:
                i = new Intent(this,CicloMenuActivity.class);
                break;
            case R.id.button_A8:
                i = new Intent(this, DiasNoHabilesMenuActivity.class);
                break;
            case R.id.button_A9:
                i = new Intent(this, TipoEventoMenuActivity.class);
                break;
            case R.id.button_A10:
                i = new Intent(this, LocalMenuActivity.class);
                break;
            case R.id.button_A11:
                i = new Intent(this, ServiciosMenuActivity.class);
                break;
        }
        if (i != null) startActivity(i);
    }


}

