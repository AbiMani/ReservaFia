package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class CicloInsertWActivity extends AppCompatActivity {
    EditText codigocicloTxt;
    EditText fechainicioTxt;
    EditText fechafinTxt;
    private final String urlLocal = "http://192.168.1.6/ws_dia_insert.php";
    private final String urlHostingGratuito = "https://reservalocalfia04.000webhostapp.com/ws_ciclo_insert.php";
    private String urlPublicoUES = "https://eisi.fia.ues.edu.sv/eisi25/MQ25001/ws_dia_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insert_w);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        codigocicloTxt = (EditText) findViewById(R.id.editCodciclo);
        fechainicioTxt = (EditText) findViewById(R.id.editFechainicio);
        fechafinTxt = (EditText) findViewById((R.id.editFechafin));
    }

    public void insertarCiclo(View v) {
        String codigociclo = codigocicloTxt.getText().toString();
        String fechainicio = fechainicioTxt.getText().toString();
        String fechafin = fechafinTxt.getText().toString();
        String url = null;
        JSONObject datosCiclo = new JSONObject();
        JSONObject ciclo = new JSONObject();
        switch (v.getId()) {
            case R.id.btn_cicloLocal:
                url = urlLocal + "?codigociclo=" + codigociclo + "&fechainicio=" + fechainicio + "&fechafin=" + fechafin ;
                ControladorServicio.insertarCicloExterno(url, this);
                break;
            case R.id.btn_cicloPublicoUES:
                url = urlPublicoUES + "?codigociclo=" + codigociclo + "&fechainicio=" + fechainicio + "&fechafin=" + fechafin ;
                ControladorServicio.insertarCicloExterno(url, this);
                break;
            case R.id.btn_cicloExterno:
                url = urlHostingGratuito + "?codigociclo=" + codigociclo + "&fechainicio=" + fechainicio + "&fechafin=" + fechafin ;
                ControladorServicio.insertarCicloExterno(url, this);
                break;
        }
    }
    public void limpiarTexto(View v) {
        codigocicloTxt.setText("");
        fechainicioTxt.setText("");
        fechafinTxt.setText("");
    }
}
