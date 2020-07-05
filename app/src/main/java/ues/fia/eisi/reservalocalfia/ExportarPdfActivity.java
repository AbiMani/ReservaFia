package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportarPdfActivity extends AppCompatActivity {

    ControlReserveLocal helper;
    String NOMBRE_DIRECTORIO = "MisPDFs";
    String NOMBRE_DOCUMENTO = "MiPDF.pdf";
    String carnet,nombres, apellido, rol, nombreEs, codigoAsig, codigoCic, numero2;
    Integer numero1;

    EditText etTexto,nombre;
    Button btnGenerar;
    EditText editCarnetDocente;
    //audio
    MediaPlayer Media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar_pdf);
        helper = new ControlReserveLocal(this);

        editCarnetDocente = (EditText) findViewById(R.id.editCarnetDocente);
        btnGenerar = findViewById(R.id.btnGenerar);
        //audio
        Media=MediaPlayer.create(getApplicationContext(), R.raw.tono);

        // Permisos
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);
        }

        // Genera el documento
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCarnetDocente.getText().toString().isEmpty()) {
                    Toast.makeText(ExportarPdfActivity.this, "Ingrese el Carnet del Docente",Toast.LENGTH_SHORT).show();
                }else {

                    helper.abrir();
                    Docente docente = helper.consultarDocentes(editCarnetDocente.getText().toString());

                    helper.cerrar();

                    if(docente == null){
                        Toast.makeText(ExportarPdfActivity.this, "Docente con el carnet:" + editCarnetDocente.getText().toString()
                                + " no encontrado", Toast.LENGTH_LONG).show();
                    }else{

                        //crearPDF();
                        helper.abrir();
                        Grupo grupo = helper.consultarGrupos(editCarnetDocente.getText().toString());

                        helper.cerrar();
                        if(grupo == null){
                            Toast.makeText(ExportarPdfActivity.this, "Docente con el carnet:" + editCarnetDocente.getText().toString()
                                    + " no posee grupo", Toast.LENGTH_LONG).show();
                        }else {
                            carnet = docente.getCarnetDocente();
                            nombres = docente.getNombreDocente();
                            apellido = docente.getApellido();
                            rol = docente.getRol();
                            nombreEs = docente.getNomEscuela();
                            codigoAsig = grupo.getCodigoAsignatura();
                            codigoCic = grupo.getCodigoCiclo();
                            //numero1 = grupo.getNumMaximoEstudiantes();
                            numero2 = String.valueOf(grupo.getNumMaximoEstudiantes());
                            crearPDF();
                            Toast.makeText(ExportarPdfActivity.this, "SE CREO EL PDF", Toast.LENGTH_LONG).show();
                            Media.start();
                        }

                    }

                }
            }
        });
    }

    public void crearPDF() {
        Document documento = new Document();

        try {
            File file = crearFichero(NOMBRE_DOCUMENTO);
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();

            documento.add(new Paragraph("Datos del Docente \n"));
            documento.add(new Paragraph("Carnet: "+carnet+"\n"));
            documento.add(new Paragraph("Nombre: "+nombres+"\n"));
            documento.add(new Paragraph("Apellido: "+apellido+"\n"));
            documento.add(new Paragraph("Rol: "+rol+"\n"));
            documento.add(new Paragraph("Escuela: "+nombreEs+"\n\n"));
            documento.add(new Paragraph("Datos de la Asignatura "+"\n"));
            documento.add(new Paragraph("Codigo de la Asignatura: "+codigoAsig+"\n"));
            documento.add(new Paragraph("Codigo de la Asignatura: "+codigoCic+"\n"));
            documento.add(new Paragraph("Maximo Estudiante: "+numero2+"\n"));


        } catch(DocumentException e) {
        } catch(IOException e) {
        } finally {
            documento.close();
        }
    }

    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();

        File fichero = null;
        if(ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    public File getRuta() {
        File ruta = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if(ruta != null) {
                if(!ruta.mkdirs()) {
                    if(!ruta.exists()) {
                        return null;
                    }
                }
            }

        }
        return ruta;
    }
}
