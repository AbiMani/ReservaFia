package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TipoEventoActivity extends AppCompatActivity {

    Button TomarFoto;
    ImageView img;
    final int FOTOGRAFIA=100;
    private final int SELECT_PICTURE = 200;
    private String APP_DIRECTORY = "Pictures/";  //Direccion de la app donde se guardara la imagen
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    Uri file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento);
        TomarFoto=(Button) findViewById(R.id.buttonFoto);
        img=(ImageView) findViewById(R.id.image);
        TomarFoto.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                img.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }
         /*   @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(TipoEventoActivity.this);
                builder.setTitle("Elige una opcion ");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion]=="Tomar foto"){
                            openCamera();
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
            }
        });*/
    }
    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }
    View.OnClickListener onClick=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(TipoEventoActivity.this);
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

}
