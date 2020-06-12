package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class InsertReservaActivity extends AppCompatActivity {

    EditText editCodigoEscuela;
    EditText editIdTipoEvento;
    EditText editNombreEvento;
    EditText editCapacidadEvento;
    EditText editFechaEvento;

    private final String urlLocal = "http://192.168.1.6/ws_reserva_insert.php";
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_reserva_insert.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi04/BV12004/ws_reserva_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_reserva);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        editCodigoEscuela = (EditText) findViewById(R.id.editCodigoEscuela);
        editIdTipoEvento=(EditText) findViewById(R.id.editidTipoEvento);
        editNombreEvento=(EditText) findViewById(R.id.editnombreevento);
        editCapacidadEvento=(EditText) findViewById(R.id.editCapacidadEvento);
        editFechaEvento=(EditText) findViewById(R.id.editFechaEvento);

    }
    public void insertarR(View v) {
        String codigoescuela= editCodigoEscuela.getText().toString();
        String tipoevento = editIdTipoEvento.getText().toString();
        String nombreevento = editNombreEvento.getText().toString();
        String capacitadevento= editCapacidadEvento.getText().toString();
        String fechaevento= editFechaEvento.getText().toString();
        String url = null;
        JSONObject datosReser = new JSONObject();
        JSONObject reser = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_cicloLocal:
                url = urlLocal + "?codigoescuela=" + codigoescuela + "&idtipoevento=" + tipoevento + "&nombreevento=" + nombreevento
                        + "&capacidadevento=" + capacitadevento + "&fechaevento=" + fechaevento;
                ControladorServicio.insertarReservaExterno(url, this);
                break;
            case R.id.btn_cicloPublicoUES:
                url = urlPublicoUES + "?codigoescuela=" + codigoescuela + "&idtipoevento=" + tipoevento + "&nombreevento=" + nombreevento
                        + "&capacidadevento=" + capacitadevento + "&fechaevento=" + fechaevento;
                ControladorServicio.insertarReservaExterno(url, this);
                break;
            case R.id.btn_cicloExterno:
                url = urlHostingGratuito + "?codigoescuela=" + codigoescuela + "&idtipoevento=" + tipoevento + "&nombreevento=" + nombreevento
                        + "&capacidadevento=" + capacitadevento + "&fechaevento=" + fechaevento;
                ControladorServicio.insertarReservaExterno(url, this);
                break;
        }
    }


    public void limpiarTexto(View v) {
        editCodigoEscuela.setText("");
        editIdTipoEvento.setText("");
        editNombreEvento.setText("");
        editFechaEvento.setText("");
        editCapacidadEvento.setText("");


    }
}
