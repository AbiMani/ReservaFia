package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReservaEventoConsultarActivity extends AppCompatActivity {
    ControlReserveLocal helper;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    EditText editidReserva;
    Spinner spinnerReserva;
    TextView txtConsultar;
    ArrayList<ReservaEvento> list;
    ArrayList<TipoEvento> listTipoEventos;
    ArrayList<Integer> itemsRE=new ArrayList<Integer>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_consultar);
        helper = new ControlReserveLocal(this);
        /*list=helper.consultarReservas();

        itemsRE.add(0);
        for (int i=0; i<list.size();i++){
            itemsRE.add(list.get(i).getIdReservaEvento());
        }*/
        // spinnerReserva=(Spinner) findViewById(R.id.spinnerreservaeventoConsultar);
        // ArrayAdapter<Integer> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsRE);
        //spinnerReserva.setAdapter(adapter);
        //listTipoEventos=helper.ListTipoEventos();
        // editidTipoEvento=(EditText) findViewById(R.id.tipoEvento);
        //editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        //editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        //editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        //editFechaEvento=(EditText) findViewById(R.id.editFecha);
        txtConsultar=(TextView) findViewById(R.id.txtConsultar);
        editidReserva=(EditText) findViewById(R.id.editConsultar);

        //OBTENIENDO EL IDENTIFICADOR
        Bundle bundle=getIntent().getExtras();
        int identificador;
        identificador=bundle.getInt("evento");
        int id=identificador;
        editidReserva.setText(String.valueOf(id));
    }

    public void consultarReservaEvento(View v) {
        String reserva_id=(editidReserva.getText().toString());

        if(reserva_id.equals(""))
        {
            Toast.makeText(this, "Error. Esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            helper.abrir();
            ReservaEvento reservaEvento=helper.consultarReserva(Integer.valueOf(reserva_id));
            helper.cerrar();
            if (reservaEvento == null){
                Toast.makeText(this, "Reservacion no registrada", Toast.LENGTH_LONG).show();
            }
            else {
                txtConsultar.setText(String.format("Evento: %s\nEscuela: %s\nDescripcion: %s\nCapacidad : %s\nFecha: %s\nHorario: %s\nLocal: %s\nCiclo: %s\nEstado: %s", reservaEvento.getNombreTipoEvento(), reservaEvento.getCodigoEscuela(), reservaEvento.getNombreEvento(), String.valueOf(reservaEvento.getCapacidadTotalEvento()), reservaEvento.getFechaReservaEvento(), reservaEvento.getHorario(), reservaEvento.getLocal(), reservaEvento.getCodigoCiclo(), reservaEvento.getEstado()));
               /* txtConsultar.setText(String.format("%s\nEscuela: ", reservaEvento.getCodigoEscuela()));
                txtConsultar.setText(String.format("%s\nDescripcion: ", reservaEvento.getNombreEvento()));
                txtConsultar.setText(String.format("%s\nPersonas que asistiran: ", String.valueOf(reservaEvento.getCapacidadTotalEvento())));
                txtConsultar.setText(String.format("%s\nFecha: ", reservaEvento.getFechaReservaEvento()));
                txtConsultar.setText(String.format("%s\n", reservaEvento.getHorario()));
                txtConsultar.setText(String.format("%s\n", reservaEvento.getLocal()));
                txtConsultar.setText(String.format("%s\n", String.format("%s\n%s", reservaEvento.getCodigoCiclo(), reservaEvento.getEstado())));
                //txtConsultar.setText( reservaEvento.getEstado()+"");*/
            }
        }
    }
    public void eliminarReservaEvento(View v){
        String regEliminadas;
        ReservaEvento reservaEvento=new ReservaEvento();
        String reserva_id=(editidReserva.getText().toString());

        if (reserva_id.equals("")){
            Toast.makeText(this, "Debe completar todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else {
            reservaEvento.setIdReservaEvento(Integer.valueOf(reserva_id));
            helper.abrir();
            regEliminadas = helper.eliminar(reservaEvento);
            helper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
            limpiarTexto(v);
        }
    }

    public void limpiarTexto(View v) {
        // spinnerReserva.setSelection(0);
        // editidTipoEvento.setText("");
        //editnombreEvento.setText("");
        //editCapacidad.setText("");
        //editCodigoEscuela.setText("");
        //editFechaEvento.setText("");
        editidReserva.setText("");
        txtConsultar.setText("");
    }
}
