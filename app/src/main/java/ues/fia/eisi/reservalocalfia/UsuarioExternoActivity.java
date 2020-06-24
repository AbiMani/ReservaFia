package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UsuarioExternoActivity extends AppCompatActivity implements View.OnClickListener {   //cambiar activity correcto
    private CardView reservalocal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_externo);
        reservalocal = (CardView) findViewById(R.id.reservalocal);

        reservalocal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.reservalocal:
                i = new Intent(this, ReservaEventoMenuActivity.class);
                break;
            default: break;

        }
        if (i != null) startActivity(i);
    }
}

