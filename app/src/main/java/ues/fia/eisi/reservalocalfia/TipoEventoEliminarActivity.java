package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TipoEventoEliminarActivity extends Activity {

    EditText editidTipoEvento;
    EditText editnombreEvento;
    ControlReserveLocal controlhelper;
    Spinner spinnerTipoEvento;
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<TipoEvento> list;
    TipoEvento tipoEvento=new TipoEvento();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_eliminar);
        controlhelper=new ControlReserveLocal(this);

        list = controlhelper.ListTipoEventos();
        items.add("Seleccione...");;
        for (int i=0;i<list.size();i++){
            items.add(list.get(i).getIdTipoEvento());
        }
        spinnerTipoEvento=(Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTipoEvento.setAdapter(adapter);
        //editidTipoEvento=(EditText) findViewById(R.id.editidTipoEvento);
    }

    public void eliminarTipoEvento(View v){
        String regEliminadas;
        TipoEvento tipoEvento=new TipoEvento();
        String tipoEvento_id=spinnerTipoEvento.getSelectedItem().toString();
        if (spinnerTipoEvento.getSelectedItem().toString().equals("Seleccione..."))
        {Toast.makeText(this, "Debe seleccionar un tipo de evento", Toast.LENGTH_SHORT).show();}
        else {
            tipoEvento.setIdTipoEvento(tipoEvento_id);
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(tipoEvento);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        spinnerTipoEvento.setSelection(0);
    }
}
