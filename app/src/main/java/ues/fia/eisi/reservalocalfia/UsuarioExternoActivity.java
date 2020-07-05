package ues.fia.eisi.reservalocalfia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.util.Calendar;

public class UsuarioExternoActivity extends AppCompatActivity implements View.OnClickListener {   //cambiar activity correcto
    private CardView reservalocal, consultarReservas, ubicacion, lectorQR;
    CardView TomarFoto;
    ImageView img;
    final int FOTOGRAFIA=100;
    private final int SELECT_PICTURE = 200;
    Uri file;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_externo);
        reservalocal = (CardView) findViewById(R.id.reservalocal);
        consultarReservas=(CardView) findViewById(R.id.buscar);
        TomarFoto=(CardView) findViewById(R.id.buttonFoto);
        img=(ImageView) findViewById(R.id.image);
        ubicacion=(CardView) findViewById(R.id.ubicacionFia);
        lectorQR=(CardView) findViewById(R.id.lectorQR);

        //ingresar a la ubicacion via GPS
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsuarioExternoActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        reservalocal.setOnClickListener((View.OnClickListener) this);
        consultarReservas.setOnClickListener(this);
        TomarFoto.setOnClickListener(Click);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                img.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }
    }
    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }
    View.OnClickListener Click=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(UsuarioExternoActivity.this);
            builder.setTitle("Elige una opcion ");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int seleccion) {
                    if (options[seleccion]=="Tomar foto"){
                        abrirCamara();
                    }else if (options[seleccion]=="Elegir de galeria"){
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent,"Selecciona app"), SELECT_PICTURE);
                    }else if (options[seleccion]=="Cancelar"){
                        dialog.dismiss();
                    }

                }
            });
            builder.show();
            // TODO Auto-generated method stub

        }
    };
    public void abrirCamara(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo =new File(Environment.getExternalStorageDirectory(),String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg");
        file=Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent,FOTOGRAFIA);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        super.onActivityResult(RequestCode, ResultCode,intent);
        IntentResult result= IntentIntegrator.parseActivityResult(RequestCode,ResultCode,intent);
        if(result != null)
            if(result.getContents()!=null){

                Uri uri= Uri.parse(result.getContents());
                Intent inten= new Intent(Intent.ACTION_VIEW, uri);
                startActivity(inten);
            }else{

            }
        switch (RequestCode){

            case FOTOGRAFIA:
                if(ResultCode == RESULT_OK){
                    img.setImageURI(file);
                }
                break;

            case SELECT_PICTURE:
                Uri path  = intent.getData();
                img.setImageURI(path);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.reservalocal:
                i = new Intent(this, TipoEventoActivity.class);
                break;
            case R.id.buscar:
                i=new Intent(this, ListaReservasActivity.class);
                break;
            case R.id.lectorQR:
                new IntentIntegrator(UsuarioExternoActivity.this).initiateScan();
                break;
            default: break;

        }
        if (i != null) startActivity(i);
    }
}

