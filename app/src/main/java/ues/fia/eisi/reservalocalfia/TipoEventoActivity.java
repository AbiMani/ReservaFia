package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento);
    }
    public void TipoEventos(View v) {
        Intent i = null;
        String evento="";

        switch (v.getId()) {
            case R.id.button_E1:
                i = new Intent(this, ReservaEventoInsertarActivity.class);
                Bundle bundle = new Bundle();
                evento="Evaluacion";
                bundle.putString("evento", evento);
                i.putExtras(bundle);
                break;
            case R.id.button_E2:
                i = new Intent(getApplication(),ReservaEventoInsertarActivity.class);
                Bundle bundle1 = new Bundle();
                evento="Foros";
                bundle1.putString("evento", evento);
                i.putExtras(bundle1);
                break;
            case R.id.button_E3:
                i = new Intent(this,ReservaEventoInsertarActivity.class);
                Bundle bundle2 = new Bundle();
                evento="Charlas";
                bundle2.putString("evento", evento);
                i.putExtras(bundle2);
                break;
            case R.id.button_E4:
                i = new Intent(this, ReservaEventoInsertarActivity.class);
                Bundle bundle3 = new Bundle();
                evento="Clases Teoricas";
                bundle3.putString("evento", evento);
                i.putExtras(bundle3);
                break;
            case R.id.button_E5:
                i = new Intent(this,ReservaEventoInsertarActivity.class);
                Bundle bundle4 = new Bundle();
                evento="Clases de discusion";
                bundle4.putString("evento", evento);
                i.putExtras(bundle4);
        }
        if (i != null) startActivity(i);
    }

    public String obtenerTipoEvento(View v){
        Intent ints=new Intent(getApplication(), ReservaEventoInsertarActivity.class);
        Bundle bundle = new Bundle();
        String evento="";
        if (v.getId()==R.id.button_E1){
            evento="Evaluacion";
            bundle.putString("evento", evento);
            ints.putExtras(bundle);
        }else if (v.getId()==R.id.button_E2){
            evento="Foro";
        }else if (v.getId()==R.id.button_E3){
            evento="Charlas";
        }else if (v.getId()==R.id.button_E4){
            evento="Clase Teorica";
        }else {evento="Clase de discusion";}

        return evento;
    }

}
