package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class InsertarEscuelaActivity extends AppCompatActivity {

    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/escuela_insert.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_nota_insert.php";

    EditText editCodigoEscuela;
    EditText editNombreEsc;
    ControlReserveLocal helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_escuela);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        helper = new ControlReserveLocal(this);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        editNombreEsc = (EditText) findViewById(R.id.editNombreEscuela);
    }
    public void insertarEscuelaWS(View v) {
        String codigoEsc=editCodigoEscuela.getText().toString();
        String nombreEsc1=editNombreEsc.getText().toString();
        String nombreEsc=nombreEsc1.replace(" ", "%20");
        String url = null;
        JSONObject datosReserva = new JSONObject();
        JSONObject reserva = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_reservaPhp:
                if (codigoEsc.equals("") || nombreEsc1.equals(""))
                {
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
                else {
                    url = urlHostingGratuito + "?codigoEscuela=" + codigoEsc + "&nombreEscuela=" + nombreEsc;
                    ControladorServicio.insertarEscuelaExterno(url, this);
                }
                break;
            case R.id.btn_reservaLocalUES:
                if (codigoEsc.equals("") || nombreEsc1.equals(""))
                {
                    Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();}
                else {
                    url = urlPublicoUES + "?codigoEscuela=" + codigoEsc + "&nombreEscuela=" + nombreEsc;
                    ControladorServicio.insertarEscuelaExterno(url, this);
                }
                break;
        }
    }
    public void limpiarTexto(View v) {
        editCodigoEscuela.setText("");
        editNombreEsc.setText("");
    }
}
