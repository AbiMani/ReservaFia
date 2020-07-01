package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.Rotate3dAnimation;
public class ReservaEventoInsertarActivity extends AppCompatActivity {

    Spinner spinnerHorario, spinnerCiclo;
    EditText editCodigoEscuela;
    EditText editnombreEvento;
    EditText editCapacidad;
    EditText editFechaEvento;
    EditText codigoLocal;
    TextView tipoEvento;
    ControlReserveLocal helper;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> itemsC=new ArrayList<>();
    ArrayList<Horario> list;
    ArrayList<Ciclo> listaC;
    Button btnInsertar, btnVerificar;

    Calendar calendar, calendarMax, calendarMin;
    DatePickerDialog dataPicker;

    Boolean verify=false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_evento_insertar);

        helper = new ControlReserveLocal(this);
        list = helper.ListHorario();
        listaC=helper.listaCiclos();
        items.add("Seleccione Horario...");
        itemsC.add("Seleccione...");
        for (int i=0;i<list.size();i++) {
            items.add(list.get(i).gethoraInicio());
        }
        for (int i=0;i<listaC.size();i++) {
            itemsC.add(listaC.get(i).getCodigoCiclo());
        }
        editFechaEvento=(EditText) findViewById(R.id.editFecha);
        editCodigoEscuela=(EditText) findViewById(R.id.editCodigoEscuela);
        spinnerHorario= (Spinner) findViewById(R.id.spinnerHora);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerHorario.setAdapter(adapter);
        spinnerCiclo=(Spinner) findViewById(R.id.spinnerCiclo);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsC);
        spinnerCiclo.setAdapter(adapter1);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editCapacidad=(EditText) findViewById(R.id.editCapacidad);
        codigoLocal=(EditText) findViewById(R.id.editLocal);
        tipoEvento=(TextView) findViewById(R.id.editTipoEvento);
        btnVerificar= (Button) findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(Click);

            editFechaEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calendar = Calendar.getInstance();
                    calendarMax=Calendar.getInstance();
                    calendarMax.set(2025, 12, 31);
                    calendarMin=Calendar.getInstance();
                    calendarMin.set(2019, 12, 01);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);

                    dataPicker = new DatePickerDialog(ReservaEventoInsertarActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            editFechaEvento.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                        }
                    },day, month, year);
                    dataPicker.getDatePicker().setMaxDate(calendarMax.getTimeInMillis());
                    dataPicker.getDatePicker().setMinDate(calendarMin.getTimeInMillis());
                    dataPicker.setTitle("Seleccione una fecha");
                    dataPicker.setIcon(R.mipmap.ic_app_ico);
                    dataPicker.show();
                }
            });

      Bundle bundle=getIntent().getExtras();
        String evento="";
          evento=bundle.getString("evento");
      String eventoT=evento;
        tipoEvento.setText(eventoT);
    }

    public void insertarReservaEvento(View v) {

        String regInsertados;
        String codigoEscuela=editCodigoEscuela.getText().toString();
        String nombreEvento=editnombreEvento.getText().toString();
        String capacidadEvento=(editCapacidad.getText().toString());
        String fechaeEvento=editFechaEvento.getText().toString();
        String codLocal=codigoLocal.getText().toString();
        String ciclo=spinnerCiclo.getSelectedItem().toString();
        String horario=spinnerHorario.getSelectedItem().toString();

        ReservaEvento reservaEvento= new ReservaEvento();
        if (codigoEscuela.equals("") || fechaeEvento.equals("")  ||
                editCapacidad.getText().toString().equals("") || codLocal.equals("") || ciclo.equals("Seleccione...")){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT ).show();
        }
        else {
            reservaEvento.setCodigoEscuela(codigoEscuela);
            reservaEvento.setNombreTipoEvento(tipoEvento.getText().toString());
            reservaEvento.setNombreEvento(nombreEvento);
            reservaEvento.setCapacidadTotalEvento(Integer.valueOf(capacidadEvento));
            reservaEvento.setFechaReservaEvento(fechaeEvento);
            reservaEvento.setHorario(horario);
            reservaEvento.setLocal(codLocal);
            reservaEvento.setCodigoCiclo(ciclo);

            helper.abrir();
            regInsertados = helper.insertar(reservaEvento);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

            limpiarTexto(v);
            if ((regInsertados.equals("Registro Insertado con exito."))) {
                mostrarNotificacion();
            }
        }
    }

    public void limpiarTexto(View v) {
        spinnerHorario.setSelection(0);
        spinnerCiclo.setSelection(0);
        editCapacidad.setText("");
        editCodigoEscuela.setText("");
        editFechaEvento.setText("");
        editnombreEvento.setText("");
        codigoLocal.setText("");
    }
    //CODIGO DE NOTIFICACION PUSH
    public void mostrarNotificacion(){
        String nombreEvento=editnombreEvento.getText().toString();
        Intent intent = new Intent(this, ListaReservasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        final Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle("Reserva de Locales FIA").setContentText(nombreEvento).
                setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_app_ico).setAutoCancel(true).setPriority(Notification.PRIORITY_HIGH).
                setVibrate(new long[100]).setSound(soundUri).setSubText("Consulta tu lista").build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        int mNotificationid=001;
        notificationManager.notify(mNotificationid, notify);
    }

    //SE HA IMPLEMENTADO SWEETALERT PARA MOSTRAR MENSAJES
    View.OnClickListener Click= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String fechaeEvento=editFechaEvento.getText().toString();
            String codLocal=codigoLocal.getText().toString();
            String horario=spinnerHorario.getSelectedItem().toString();
            ReservaEvento reservaEvento= new ReservaEvento();
            reservaEvento.setLocal(codLocal);
            reservaEvento.setFechaReservaEvento(fechaeEvento);
            reservaEvento.setHorario(horario);
            helper.abrir();
            verify = helper.verificarReserva(reservaEvento);
            helper.cerrar();
            if (fechaeEvento.equals("")||codLocal.equals("")||horario.equals("Seleccione Horario...")){
                Toast.makeText(ReservaEventoInsertarActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
            }
            else {
                if (verify == true) {
                    SweetAlertDialog pDialog = new SweetAlertDialog(ReservaEventoInsertarActivity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("No esta Disponible");
                    pDialog.setContentText("Elige otro horario o local");
                    pDialog.setConfirmText("Ok :(");
                    pDialog.show();
                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(ReservaEventoInsertarActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText("Disponible");
                    pDialog.setConfirmText("Ok");
                    pDialog.setContentText("Ahora puedes seguir llenando tu solicitud.");
                    pDialog.show();
                }
            }
        }
    };
}
