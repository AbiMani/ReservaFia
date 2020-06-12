package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TipoEventoInsertarActivity extends Activity {

    EditText editidTipoEvento;
    EditText editnombreEvento;
    ControlReserveLocal helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_insertar);

        helper = new ControlReserveLocal(this);
        editidTipoEvento=(EditText) findViewById(R.id.editidTipoEvento);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
    }
    public void insertarTipoEvento(View v) {

        String regInsertados;
        String idTipovento=editidTipoEvento.getText().toString();
        String nombreEvento=editnombreEvento.getText().toString();

        TipoEvento tipoEvento= new TipoEvento();
        if (idTipovento.equals("") || nombreEvento.equals(""))
        {Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
        else {
            tipoEvento.setIdTipoEvento((idTipovento));
            tipoEvento.setNomTipoEvento(nombreEvento);
            helper.abrir();
            regInsertados = helper.insertar(tipoEvento);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }


    public void limpiarTexto(View v) {
        editidTipoEvento.setText("");
        editnombreEvento.setText("");
    }
}
