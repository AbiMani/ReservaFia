package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class DiaInsertActivity extends AppCompatActivity {
    EditText ididaTxt;
    EditText nomdiaTxt;
    private final String urlLocal = "http://192.168.1.6/ws_dia_insert.php";
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_dia_insert.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_dia_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_insert);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ididaTxt = (EditText) findViewById(R.id.editText_iddia);
        nomdiaTxt = (EditText) findViewById(R.id.editText_nomdia);
    }

    public void insertarDia(View v) {
        String iddia = ididaTxt.getText().toString();
        String nomdia = nomdiaTxt.getText().toString();
        String url = null;
        JSONObject datosDia = new JSONObject();
        JSONObject dia = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_diaLocal:
                url = urlLocal + "?iddia=" + iddia + "&nomdia=" + nomdia ;
                ControladorServicio.insertarDiaExterno(url, this);
                break;
            case R.id.btn_diaPublicoUES:
                url = urlPublicoUES + "?iddia=" + iddia + "&nomdia=" + nomdia ;
                ControladorServicio.insertarDiaExterno(url, this);
                break;
            case R.id.btn_diaExterno:
                url = urlHostingGratuito + "?iddia=" + iddia + "&nomdia=" + nomdia ;
                ControladorServicio.insertarDiaExterno(url, this);
                break;
        }
    }
    public void limpiarTexto(View v) {
        ididaTxt.setText("");
        nomdiaTxt.setText("");
    }
}
