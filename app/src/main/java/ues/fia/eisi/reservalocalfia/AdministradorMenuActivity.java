package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdministradorMenuActivity extends AppCompatActivity implements View.OnClickListener {   //cambiar activity correcto
    private CardView usuario, escuela, tipolocal, docente, asignatura, roldoc, ciclo, diano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}

