package ues.fia.eisi.reservalocalfia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ControlReserveLocal {

    //campos de las tablas de la base de datos
    private static final String[] camposLocal = new String[]{"codigoLocal", "idEncargadoLocal", "idTipoLocal", "ubicacionLocal", "capacidadLocal"};
    private static final String[] camposAsignatura = new String[] {"codigoAsignatura","codigoLocal","codigoEscuela","nomAsignatura","idPrioridad"};
    private static final String[] camposCargaAcademica = new String[] {"idRolDocente","codigoAsignatura", "codigoCiclo","carnetDocente"};
    private static final String[] camposHorario = new String[] {"idHorario","idDia","horaInicio","horaFin"};
    private static final String[] camposDia = new String[] {"idDia","nomDia"};
    private static final String[] camposPri = new String[] {"idPrioridad","nivelPrioridad"};

    private static final String[]camposDetalleReserva = new String [] {"idhorario","idreservaevento","codigolocal"};
    private static final String[]camposReservaEvento = new String [] {"idreservaevento","codigoescuela","idtipoevento","nombreevento", "capacidadtotalevento", "fechareserva"};
    private static final String[] camposTipoEvento = new String [] {"idtipoEvento","nomtipoevento"};

    //campos de las tablas docente
    private static final String[]camposDocente = new String [] {"carnetDocente","nombreDocente","apellido", "rol", "nomEscuela"};
    private static final String[]camposRolDocente = new String [] {"idRolDocente","nomRolDocente"};
    private static final String[]camposTipoLocal = new String [] {"idTipoLocal","nomTipoLocal"};
    private static final String[] camposEscuela= new String[] {"codigoEscuela", "nomEscuela"};

    //campos tabla ciclo, grupo y dias no habiles
    private static final String[]camposCiclo = new String [] {"codigoCiclo","Fechainicio","Fechafin"};
    private static final String[]camposGrupo = new String [] {"idRolDocente","codigoAsignatura","codigoCiclo","carnetDocente","idGrupo","numMaximoEstudiantes"};
    private static final String[] camposDiasNoHabiles = new String [] {"idDiasNoHabiles","codigoCiclo","nomDiasNoHabiles","fechaDesde","fechaHasta"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final String DROP_TABLE1 ="DROP TABLE IF EXISTS detallereserva; ";
    private static final String DROP_TABLE2 ="DROP TABLE IF EXISTS reservaevento; ";
    private static final String DROP_TABLE3 ="DROP TABLE IF EXISTS tipoevento; ";


    public ControlReserveLocal(Context ctx) {
        this.context=ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "reserveLocal.s3db"; //nombre de la base de datos SQLite
        private static final int VERSION= 1;

        DatabaseHelper(Context context){
            super(context,BASE_DATOS,null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE Local(codigoLocal VARCHAR(20) NOT NULL PRIMARY KEY,idEncargadoLocal VARCHAR(2),idTipoLocal VARCHAR(2),ubicacionLocal VARCHAR(40),capacidadLocal INTEGER);");
                db.execSQL("CREATE TABLE asignatura(codigoAsignatura VARCHAR(6) NOT NULL PRIMARY KEY, codigoLocal VARCHAR(20),codigoEscuela VARCHAR(20),nomAsignatura VARCHAR(30),idPrioridad Varchar(1) not null);");
                db.execSQL("CREATE TABLE horario(idHorario INTEGER NOT NULL PRIMARY KEY,idDia VARCHAR(1),horaInicio VARCHAR(30),horaFin VARCHAR(30));");
                db.execSQL("CREATE TABLE cargaAcademica(idRolDocente INTEGER NOT NULL , codigoAsignatura VARCHAR(6) NOT NULL ,codigoCiclo VARCHAR(6) ,carnetDocente Varchar(7) ,PRIMARY KEY(codigoAsignatura,carnetDocente,codigoCiclo));");
                db.execSQL("CREATE TABLE dia(idDia VARCHAR(4) NOT NULL PRIMARY KEY,nomDia varchar(20) not null);");
                //db.execSQL("CREATE TABLE rolDocente(idRolDocente VARCHAR(2) NOT NULL PRIMARY KEY,nomRolDocente VARCHAR(20) not null);");
                db.execSQL("CREATE TABLE prioridad(idPrioridad VARCHAR(1) NOT NULL PRIMARY KEY, nivelPrioridad VARCHAR(20));");
                db.execSQL("create trigger fk_carga_asignatura before insert on cargaAcademica for each row begin select " +
                        "case when ((select codigoAsignatura from asignatura where codigoAsignatura=new.codigoAsignatura) is null)" +
                        " then raise(abort,'no puede insertar, no existe asignatura') end; end;");
             /**   db.execSQL("create trigger fk_carga_ciclo before insert on cargaAcademica for each row begin select " +
                        "case when ((select codigoCiclo from ciclo where codigoCiclo=new.codigoCiclo) is null)" +
                        " then raise(abort,'no puede insertar, no existe ciclo') end; end;");
                db.execSQL("create trigger fk_carga_docente before insert on cargaAcademica for each row begin select " +
                        "case when ((select carnetDocente from docente where carnetDocente=new.carnetDocente) is null)" +
                        " then raise(abort,'no puede insertar, no existe docente') end; end;");
                db.execSQL("create trigger fk_carga_roldocente before insert on cargaAcademica for each row begin select " +
                        "case when ((select idRolDocente from rolDocente where idRolDocente=new.idRolDocente) is null)" +
                        " then raise(abort,'no puede insertar, no existe rol docente') end; end;");
                db.execSQL("create trigger fk_asignatura_local before insert on asignatura for each row begin select " +
                        "case when ((select codigoLocal from local where codigoLocal=new.codigoLocal) is null)" +
                        " then raise(abort,'no puede insertar, no existe local') end; end;");
                db.execSQL("create trigger fk_asignatura_escuela before insert on asignatura for each row begin select " +
                        "case when ((select codigoEscuela from escuela where codigoEscuela=new.codigoEscuela) is null)" +
                        " then raise(abort,'no puede insertar, no existe escuela') end; end;"); **/
                db.execSQL("create trigger fk_asignatura_prioridad before insert on asignatura for each row begin select " +
                        "case when ((select idPrioridad from prioridad where idPrioridad=new.idPrioridad) is null)" +
                        " then raise(abort,'no puede insertar, no existe escuela') end; end;");
                db.execSQL("create trigger dependencias_asignatura after update of codigoAsignatura on asignatura begin " +
                        "update cargaAcademica set codigoAsignatura= new.codigoAsignatura where cargaAcademica.codigoAsignatura=old.codigoAsignatura; end;");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('L-X','Lunes-Miercoles');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('M-V','Martes-Viernes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('L','Lunes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('M','Martes');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('X','Miercoles');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('J','Jueves');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('S','Sabado');");
                db.execSQL("INSERT INTO dia (idDia,nomDia) VALUES ('D','Domingo');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('A','Alta');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('M','Media');");
                db.execSQL("INSERT INTO prioridad (idPrioridad,nivelPrioridad) VALUES ('B','Baja');");

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
                db.execSQL("CREATE TABLE detallereserva(iddetalle INTEGER PRIMARY KEY AUTOINCREMENT, idhorario INTEGER NOT NULL, idreservaevento INTEGER NOT NULL  ,codigolocal VARCHAR(7) NOT NULL)");
                db.execSQL("CREATE TABLE reservaevento(idreservaevento INTEGER PRIMARY KEY AUTOINCREMENT,codigoescuela VARCHAR(20) NOT NULL,idtipoevento VARCHAR(2) NOT NULL,nombreevento VARCHAR(30),capacidadtotalevento INTEGER, fechareserva VARCHAR(10))");
                db.execSQL("CREATE TABLE tipoevento(idtipoevento VARCHAR(2) NOT NULL ,nomtipoevento VARCHAR(30),PRIMARY KEY(idtipoevento))");

                db.execSQL("CREATE TABLE escuela(codigoEscuela VARCHAR(20) PRIMARY KEY, nomEscuela VARCHAR(50))");

                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T1', 'Evaluacion')");
                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T2', 'Capacitacion')");
                db.execSQL("INSERT INTO tipoevento(idtipoevento, nomtipoevento)  VALUES('T3', 'Clases')");
                //TRIGGER VERIFICAR EXISTENCIA DE IDHORARIO EN TABLA HORARIO
                db.execSQL("CREATE TRIGGER fk_horario_detallereserva\n" +
                        "BEFORE INSERT ON detallereserva\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SELECT CASE\n" +
                        "    WHEN ((SELECT idHorario FROM horario\n" +
                        "          WHERE idHorario=new.idHorario)IS NULL )\n" +
                        "     THEN RAISE(ABORT, 'No existe Horario')\n" +
                        "    END;\n" +
                        "END");
                //TRIGGER EXISTENCIA DE CODIGO ESCUELA EN TABLA ESCUELA
                db.execSQL("CREATE TRIGGER fk_escuela_reservaevento\n" +
                        "BEFORE INSERT ON reservaevento\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SELECT CASE\n" +
                        "    WHEN ((SELECT codigoEscuela FROM escuela\n" +
                        "          WHERE codigoEscuela=new.codigoEscuela)IS NULL )\n" +
                        "     THEN RAISE(ABORT, 'No existe codigo escuela')\n" +
                        "    END;\n" +
                        "END");
                //INTEGRIDAD RESERVA DE EVENTO Y DETALLE DE RESERVA
                db.execSQL("CREATE TRIGGER fk_reserva_detallereserva\n" +
                        "BEFORE INSERT ON detallereserva\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    SELECT CASE\n" +
                        "    WHEN ((SELECT idReservaEvento FROM reservaevento\n" +
                        "          WHERE idReservaEvento=new.idReservaEvento)IS NULL )\n" +
                        "     THEN RAISE(ABORT, 'No existe ID reserva de evento')\n" +
                        "    END;\n" +
                        "END");
                        //TIGGER EXISTENCIA DE CODIGOLOCAL EN TABLA LOCAL
                 db.execSQL("CREATE TRIGGER fk_local_detallereserva\n" +
                       "BEFORE INSERT ON detallereserva\n" +
                       "FOR EACH ROW\n" +
                       "BEGIN\n" +
                       "    SELECT CASE\n" +
                       "    WHEN ((SELECT codigoLocal FROM Local\n" +
                       "          WHERE codigoLocal=new.codigoLocal)IS NULL )\n" +
                       "     THEN RAISE(ABORT, 'No existe el Local seleccionado')\n" +
                       "    END;\n" +
                       "END");


                //tabla tipo local
                db.execSQL("CREATE TABLE tipoLocal(idTipoLocal INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomTipoLocal varchar(30));");
                //tabla rol docente
                db.execSQL("CREATE TABLE rolDocente(idRolDocente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomRolDocente varchar(30));");
                //tabla docente
                db.execSQL("CREATE TABLE docente(carnetDocente VARCHAR(7) NOT NULL,nombreDocente VARCHAR(30)," +
                        "apellido VARCHAR(30),rol VARCHAR(30),nomEscuela VARCHAR(30),PRIMARY KEY(carnetDocente)," +
                        "CONSTRAINT fk_docente_rolDocente FOREIGN KEY (rol) REFERENCES rolDocente(nomRolDocente) ON DELETE RESTRICT);");
                //Tablas de login
                //Usuario
                db.execSQL("CREATE TABLE usuario(iduser integer not null primary key AUTOINCREMENT, username varchar(30) not null, password varchar(30) not null, is_admin varchar(4), " +
                        "is_docente varchar(4), nombres varchar(30), apellidos varchar(30), correo varchar(30), carnetDocente VARCHAR(7)," +
                        "constraint fk_docente_usuario foreign key(carnetDocente)references docente(carnetDocente) on delete restrict)");
                db.execSQL("CREATE TABLE AccesoUsuario(idOpcion VARCHAR (3) NOT NULL, idUsuario VARCHAR(2) NOT NULL, PRIMARY KEY(idOpcion, idUsuario));");
                db.execSQL("CREATE TABLE OpcionCrud(idOpcion VARCHAR(3) NOT NULL PRIMARY KEY, desOpcion VARCHAR(30), numCrud INTEGER);");
                //TABLAS CICLO, DIAS NO HABILES Y GROUP
                db.execSQL("CREATE TABLE ciclo(codigociclo VARCHAR(7) NOT NULL PRIMARY KEY,fechainicio VARCHAR(10),fechafin VARCHAR(10));");
                db.execSQL("CREATE TABLE grupo(idroldocente INTEGER NOT NULL,codigoasignatura VARCHAR(30) NOT NULL,codigociclo VARCHAR(1) NOT NULL,carnetdocente VARCHAR(7) NOT NULL,idgrupo VARCHAR(7) NOT NULL, nummaximoestudiantes INTEGER, PRIMARY KEY(idgrupo, idroldocente, codigoasignatura, codigociclo, carnetdocente));");
                db.execSQL("CREATE TABLE diasnohabiles(iddiasnohabiles VARCHAR(2) NOT NULL PRIMARY KEY,codigociclo VARCHAR(7) NOT NULL ,nomdiasnohabiles VARCHAR(30) ,fechadesde VARCHAR(10), fechahasta VARCHAR(10));");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    //Metodo para leer datos de la base
    public SQLiteDatabase abrirConsultar() throws SQLException{
        db = DBHelper.getReadableDatabase();
        return db;
    }
    public void cerrar(){
        DBHelper.close();
    }

    ////////////////////////////////////CRUD DE TABLAS CICLO DIAS NO HABILES Y GRUPO /////////////////////////////////////////////////////////
    public String insertar(Ciclo ciclo){
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues cic = new ContentValues();
        cic.put("codigoCiclo", ciclo.getCodigoCiclo());
        cic.put("fechaInicio", ciclo.getFechaInicio());
        cic.put("fechaFin", ciclo.getFechaFin());
        contador = db.insert("ciclo", null, cic);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    public String insertar(Local local){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues lol = new ContentValues();
        lol.put("codigoLocal", local.getCodigoLocal());
        lol.put("idEncargadoLocal", local.getIdEncargadoLocal());
        lol.put("idTipoLocal", local.getIdTipoLocal());
        lol.put("ubicacionLocal", local.getUbicacionLocal());
        lol.put("capacidadLocal", local.getCapacidadLocal());
        contador=db.insert("Local", null, lol);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción"; }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Grupo grupo){
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        if (verificarIntegridadLM(grupo, 2)) {
            ContentValues grupos = new ContentValues();
            grupos.put("idroldocente", grupo.getIdRolDocente());
            grupos.put("codigoasignatura", grupo.getCodigoAsignatura());
            grupos.put("codigociclo", grupo.getCodigoCiclo());
            grupos.put("carnetdocente", grupo.getCarnetDocente());
            grupos.put("idgrupo", grupo.getIdGrupo());
            grupos.put("nummaximoestudiantes", grupo.getNumMaximoEstudiantes());
            contador = db.insert("grupo", null, grupos);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(DiasNoHabiles diasNoHabiles) {

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if (verificarIntegridadLM(diasNoHabiles,7)){
            return "registro ya existe";
        }
        else {
            if (verificarIntegridadLM(diasNoHabiles, 1)) {
                ContentValues diano = new ContentValues();
                diano.put("iddiasnohabiles", diasNoHabiles.getIdDiasNoHabiles());
                diano.put("codigociclo", diasNoHabiles.getCodigoCiclo());
                diano.put("nomdiasnohabiles", diasNoHabiles.getNomDiasNoHabiles());
                diano.put("fechadesde", diasNoHabiles.getFechaDesde());
                diano.put("fechahasta", diasNoHabiles.getFechaHasta());
                contador=db.insert("diasnohabiles", null, diano);
            }
            if(contador==-1 || contador==0) {
                regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
            else {
                regInsertados=regInsertados+contador;
            }
        }
        return regInsertados;
    }

    public String actualizarC(Ciclo ciclo) {
        if(verificarIntegridadLM(ciclo,4)){
            String[] id = {ciclo.getCodigoCiclo()};
            ContentValues cv = new ContentValues();
            cv.put("fechainicio", ciclo.getFechaInicio());
            cv.put("fechafin", ciclo.getFechaFin());
            db.update("ciclo",cv, "codigoCiclo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigoCiclo " + ciclo.getCodigoCiclo() + " no existe";
        }
    }

    public String actualizarG(Grupo grupo) {

        if(verificarIntegridadLM(grupo, 2)){
            String[] id = {String.valueOf(grupo.getIdRolDocente()),grupo.getCodigoAsignatura(), grupo.getCodigoCiclo(),grupo.getCarnetDocente(),grupo.getIdGrupo()};
            ContentValues cv = new ContentValues();
            cv.put("nummaximoestudiantes", grupo.getNumMaximoEstudiantes());
            db.update("grupo", cv, "idRolDocente = ? AND codigoasignatura = ? AND codigociclo = ? AND carnetDocente = ? AND idgrupo = ? ", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String actualizarD(DiasNoHabiles diasNoHabiles) {
        if(verificarIntegridadLM(diasNoHabiles, 7)){
            String[] id = {diasNoHabiles.getIdDiasNoHabiles()};
            ContentValues cv = new ContentValues();
            cv.put("nomdiasnohabiles", diasNoHabiles.getNomDiasNoHabiles());
            cv.put("fechadesde", diasNoHabiles.getFechaDesde());
            cv.put("fechahasta", diasNoHabiles.getFechaHasta());
            db.update("diasnohabiles", cv, "iddiasnohabiles = ? ", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String eliminarCiclo(Ciclo ciclo) {
        String regAfectados= "filas afectadas= ";
        int contador=0;
        if(verificarIntegridadLM(ciclo,4)){
            if(verificarIntegridadLM(ciclo,3)){
                regAfectados+="tiene dependencias";
            }else{
                contador+=db.delete("ciclo","codigociclo ='"+ciclo.getCodigoCiclo()+"'",null);
                regAfectados+=contador;
            }
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public String eliminarG(Grupo grupo) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where=" idroldocente='"+grupo.getIdRolDocente()+"'";
        where=where+" AND codigoasignatura='"+grupo.getCodigoAsignatura()+"'";
        where=where+" AND codigociclo='"+grupo.getCodigoCiclo()+"'";
        where=where+" AND carnetdocente='"+grupo.getCarnetDocente()+"'";
        where=where+" AND idgrupo="+grupo.getIdGrupo();
        contador+=db.delete("grupo", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminarD(DiasNoHabiles diasNoHabiles) {

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where = "iddiasnohabiles='" + diasNoHabiles.getIdDiasNoHabiles() + "'";
        if (verificarIntegridadLM(diasNoHabiles,7)) {
            contador += db.delete("diasnohabiles", where, null);
            regAfectados += contador;
        }
        else{
            return "Registro" + diasNoHabiles.getIdDiasNoHabiles() + "no existe";
        }
        return regAfectados;
    }

    public Ciclo consultarCiclo(String codigociclo) {
        String[] id = {codigociclo};
        Cursor cursor = db.query("ciclo", camposCiclo, "codigociclo = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Ciclo ciclo01 = new Ciclo();
            ciclo01.setCodigoCiclo(cursor.getString(0));
            ciclo01.setFechaInicio(cursor.getString(1));
            ciclo01.setFechaFin(cursor.getString(2));
            return ciclo01;
        }else{ return null;
        }
    }

    public Grupo consultarGrupo(String idgrupo, String idroldocente, String codigoasignatura, String codigociclo, String carnetdocente) {

        String[] id = {idgrupo,idroldocente, codigoasignatura, codigociclo,carnetdocente};
        Cursor cursor = db.query("grupo", camposGrupo, "idgrupo = ? AND idroldocente = ? AND codigoasignatura = ?" +
                " AND codigociclo = ? AND carnetdocente =? ", id, null, null, null);
        if (cursor.moveToFirst()) {
            Grupo grupo = new Grupo();
            grupo.setIdRolDocente(cursor.getInt(0));
            grupo.setCodigoAsignatura(cursor.getString(1));
            grupo.setCodigoCiclo(cursor.getString(2));
            grupo.setCarnetDocente(cursor.getString(3));
            grupo.setIdGrupo(cursor.getString(4));
            grupo.setNumMaximoEstudiantes(cursor.getInt(5));
            return grupo;
        } else {
            return null;
        }
    }

    public DiasNoHabiles consultarD(String iddiasnohabiles){
        String[] id = {iddiasnohabiles};
        Cursor cursor = db.query("diasnohabiles", camposDiasNoHabiles, "iddiasnohabiles = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            DiasNoHabiles diasno = new DiasNoHabiles();
            diasno.setIdDiasNoHabiles(cursor.getString(0));
            diasno.setCodigoCiclo(cursor.getString(1));
            diasno.setNomDiasNoHabiles(cursor.getString(2));
            diasno.setFechaDesde(cursor.getString(3));
            diasno.setFechaHasta(cursor.getString(4));
            return diasno;
        } else {
            return null;
        }
    }
///////////////////////////////////////////////// FIN DEL CRUD DIAS NO HABILES, CICLO Y GRUPO ////////////////////////////////////////////////

    public String insertar(Asignatura asignatura){

        String regInsertados="Registro Insertado No= ";
        long contador=0;

        ContentValues asig= new ContentValues();
        asig.put("codigoAsignatura", asignatura.getCodigoAsignatura());
        asig.put("codigoLocal", asignatura.getCodigoLocal());
        asig.put("codigoEscuela", asignatura.getCodigoEscuela());
        asig.put("nomAsignatura", asignatura.getNomAsignatura());
        asig.put("idPrioridad", asignatura.getIdPrioridad());
        contador=db.insert("asignatura",null,asig);
        if(contador==-1||contador==0){
            regInsertados="Error al insertar el registro, Registro Duplicado. Vereficar insercion";
        }else{
            regInsertados= regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(CargaAcademica cargaAcademica){
        String regInsertados = "Registro Insertado No= ";
        long contador=0;
        if(verificarIntegridad(cargaAcademica,1)){
            ContentValues cargaAcademicas = new ContentValues();
            cargaAcademicas.put("idRolDocente", cargaAcademica.getidRolDocente());
            cargaAcademicas.put("codigoAsignatura", cargaAcademica.getcodigoAsignatura());
            cargaAcademicas.put("codigoCiclo", cargaAcademica.getcodigoCiclo());
            cargaAcademicas.put("carnetDocente", cargaAcademica.getcarnetDocente());
            contador=db.insert("cargaAcademica", null,cargaAcademicas);
        }
        if(contador==-1|| contador==0){
            regInsertados= "Error al insertar el registro, Registro Dublicado. Verificar insercion";
        }else {
            regInsertados= regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Horario horario){
        String regInsertados = "Registro Insertado No= ";
        long contador=0;
        ContentValues hor = new ContentValues();
        hor.put("idHorario", horario.getidHorario());
        hor.put("idDia", horario.getidDia());
        hor.put("horaInicio", horario.gethoraInicio());
        hor.put("horaFin", horario.gethoraFin());
        contador= db.insert("horario",null,hor);
        if(contador==1||contador==0){
            regInsertados= "Error al insertar el registro, Registro Duplicado. Verificar insercion";
        }else{
            regInsertados= regInsertados+ contador;
        }
        return regInsertados;
    }

    public String actualizar(Asignatura asignatura){
        if(verificarIntegridad(asignatura, 5)){
            String[] id= {asignatura.getCodigoAsignatura()};
            ContentValues cv = new ContentValues();
            cv.put("codigoLocal", asignatura.getCodigoLocal());
            cv.put("codigoEscuela", asignatura.getCodigoEscuela());
            cv.put("nomAsignatura", asignatura.getNomAsignatura());
            db.update("asignatura", cv, "codigoAsignatura = ?", id);
            return "Registro actualizado correctamente";
        }else{
            return "Registro con codigoAsignatura "+ asignatura.getCodigoAsignatura() + "no existe";
        }
    }

    public String actualizar(Horario horario){
        if(verificarIntegridad(horario,6)){
            String [] id= {String.valueOf(horario.getidHorario())};
            ContentValues cv = new ContentValues();
            cv.put("idDia",horario.getidDia());
            cv.put("horaInicio",horario.gethoraInicio());
            cv.put("horaFin",horario.gethoraFin());
            db.update("horario",cv,"idHorario = ?", id);
            return "Registro actualizado correctamente";
        }else {
            return "Registro con codigo de horario " +horario.getidHorario()+ "no existe";
        }

    }

    public String actualizar(CargaAcademica cargaAcademica){

        if(verificarIntegridad(cargaAcademica, 2)){
            String[] id = { cargaAcademica.getcodigoAsignatura(), cargaAcademica.getcodigoCiclo(), cargaAcademica.getcarnetDocente()};
            ContentValues cv = new ContentValues();
            cv.put("idRolDocente", cargaAcademica.getidRolDocente());
            db.update("cargaAcademica", cv, "codigoAsignatura = ? AND codigoCiclo = ? AND carnetDocente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String eliminar(Asignatura asignatura){
        String regAfectados= "filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(asignatura,5)) {
            if (verificarIntegridad(asignatura, 3)) {
                regAfectados += "No se puede eliminar, Tiene dependencias en otra tabla";
            } else {
                contador += db.delete("asignatura", "codigoAsignatura='" + asignatura.getCodigoAsignatura() + "'", null);
                regAfectados += contador;
            }
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public String eliminar(CargaAcademica cargaAcademica){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="codigoAsignatura='"+cargaAcademica.getcodigoAsignatura()+"'";
        where=where+" AND carnetDocente='"+cargaAcademica.getcarnetDocente()+"'";
        where=where+" AND codigoCiclo="+cargaAcademica.getcodigoCiclo();
        if (verificarIntegridad(cargaAcademica,2)) {
            contador += db.delete("cargaAcademica", where, null);
            regAfectados += contador;
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public String eliminar(Horario horario){
        String regAfectados= "filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(horario,6)){
            if(verificarIntegridad(horario,4)){
                regAfectados+="No se puede eliminar, Tiene dependencias en otra tabla";
            }else{
                contador+= db.delete("horario", "idHorario='"+horario.getidHorario()+"'",null);
                regAfectados+= contador;
            }
        }else{
            return "Registro no existe";
        }
        return regAfectados;
    }

    public Asignatura consultarAsignatura(String codigoAsignatura){
        String[] id= {codigoAsignatura};
        Cursor cursor = db.query("asignatura", camposAsignatura, "codigoAsignatura = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Asignatura asignatura= new Asignatura();
            asignatura.setCodigoAsignatura(cursor.getString(0));
            asignatura.setCodigoLocal(cursor.getString(1));
            asignatura.setCodigoEscuela(cursor.getString(2));
            asignatura.setNomAsignatura(cursor.getString(3));
            asignatura.setIdPrioridad(cursor.getString(4));
            return asignatura;
        }else{
            return null;
        }
    }

    public Horario consultarHorario(String idHorario){
        String[] id= {idHorario};
        Cursor cursor = db.query("horario", camposHorario, "idHorario = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Horario horario = new Horario();
            horario.setidHorario(cursor.getInt(0));
            horario.setidDia(cursor.getString(1));
            horario.sethoraInicio(cursor.getString(2));
            horario.sethoraFin(cursor.getString(3));
            return horario;
        }else{
            return null;
        }
    }

    public ArrayList<Dia> listaDia(){
        abrirConsultar();
        Dia dia;
        ArrayList<Dia> lista = new ArrayList<Dia>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM dia", null);
        while(cursor.moveToNext()){
            dia = new Dia();
            dia.setIdDia(cursor.getString(0));
            dia.setNomDia(cursor.getString(1));
            lista.add(dia);

        }return lista;
    }

    public ArrayList<Prioridad> listaPrioridad(){
        abrirConsultar();
        Prioridad prioridad;
        ArrayList<Prioridad> lista = new ArrayList<Prioridad>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM prioridad", null);
        while(cursor.moveToNext()){
            prioridad = new Prioridad();
            prioridad.setIdprioridad(cursor.getString(0));
            lista.add(prioridad);
        }return lista;
    }


    public CargaAcademica consultarCargaAcademica( String carnetDocente,String codigoAsignatura, String codigoCiclo){
        String[] id= {carnetDocente,codigoAsignatura,codigoCiclo};
        Cursor cursor= db.query("cargaAcademica",camposCargaAcademica, "carnetDocente = ? AND codigoAsignatura = ?  AND codigoCiclo= ?", id,null,null,null);
        if (cursor.moveToFirst()){
            CargaAcademica cargaAcademica = new CargaAcademica();
            cargaAcademica.setcarnetDocente(cursor.getString(3));
            cargaAcademica.setcodigoAsignatura(cursor.getString(1));
            cargaAcademica.setcodigoCiclo(cursor.getString(2));
            cargaAcademica.setidRolDocente(cursor.getInt(0));
            return cargaAcademica;
        }else {
            return null;
        }
    }
    //---------------------------------TipoEvento---------------------------------
    public String insertar(TipoEvento tipoEvento){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        //*
        if(verificarIntegridad(tipoEvento,7))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {

            ContentValues tipoEv = new ContentValues();
            tipoEv.put("idtipoevento", tipoEvento.getIdTipoEvento());
            tipoEv.put("nomtipoevento", tipoEvento.getNomTipoEvento());
            contador=db.insert("tipoevento", null, tipoEv);
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }

    public String actualizar(TipoEvento tipoEvento){

        // * 2 Verificar que el registro que exista
            String[] id = {tipoEvento.getIdTipoEvento()};
            ContentValues cv = new ContentValues();
            cv.put("nomtipoevento", tipoEvento.getNomTipoEvento());
            db.update("tipoEvento", cv, "idtipoevento = ? ", id);
            return "Registro Actualizado Correctamente";
    }
    public String eliminar(TipoEvento tipoEvento){

        String regAfectados;
        int contador=0;
        if (verificarIntegridad(tipoEvento, 12)){
            regAfectados="El registro tiene dependencias en otra tabla ";
        }
        else {
            String where = "idtipoevento='" + tipoEvento.getIdTipoEvento() + "'";

            contador += db.delete("tipoEvento", where, null);
            regAfectados="Registro eliminado";
        }
            return regAfectados;

    }
    public TipoEvento consultarTipoEvento(String idtipoevento){

        String[] id = {idtipoevento};
        Cursor cursor = db.query("tipoevento", camposTipoEvento, "idtipoevento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoEvento(cursor.getString(0));
            tipoEvento.setNomTipoEvento(cursor.getString(1));
            return tipoEvento;
        }else{
            return null;
        }
    }
    public ArrayList<TipoEvento> ListTipoEventos() {

        abrirConsultar();
        TipoEvento tipoEvento;
        ArrayList<TipoEvento> lista = new ArrayList<TipoEvento>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM tipoevento", null);
        while(cursor.moveToNext()){
            tipoEvento = new TipoEvento();
            tipoEvento.setIdTipoEvento(cursor.getString(0));
            lista.add(tipoEvento);
        }
        return lista;
    }
    //---------------------------------ReservaEvento------------------------------------------------------
    public String insertar(ReservaEvento reservaEvento){
        String regInsertados;
        long contador=0;
        ContentValues reservas = new ContentValues();
        reservas.put("codigoEscuela", reservaEvento.getCodigoEscuela());
        reservas.put("idTipoEvento", reservaEvento.getIdTipoEvento());
        reservas.put("nombreEvento", reservaEvento.getNombreEvento());
        reservas.put("capacidadTotalEvento", reservaEvento.getCapacidadTotalEvento());
        reservas.put("fechaReserva", reservaEvento.getFechaReservaEvento());
        contador = db.insert("reservaevento", "idReservaEvento", reservas);
        if (contador==-1 || contador==0) {
            regInsertados = "Error al Insertar el registro, no se encontro codigo escuela";
        } else {

            regInsertados = "Registro Insertado con exito.";
        }
        return regInsertados;

    }

    public String actualizar(ReservaEvento reservaEvento){
        int contador=0;
        String[] id = {String.valueOf(reservaEvento.getIdReservaEvento())};
        ContentValues cv = new ContentValues();
        cv.put("codigoEscuela", reservaEvento.getCodigoEscuela());
        cv.put("idTipoevento", reservaEvento.getIdTipoEvento());
        cv.put("nombreEvento", reservaEvento.getNombreEvento());
        cv.put("capacidadTotalEvento", reservaEvento.getCapacidadTotalEvento());
        cv.put("fechaReserva", reservaEvento.getFechaReservaEvento());
        String where="idReservaEvento= ?";
        contador+=db.update("reservaevento",cv, where, id);
        if (contador==-1 || contador==0){
            return "El codigo de escuela no se encontro";
        }
        else {
            return "Registro actualizado con exito";
        }

    }

    public String eliminar(ReservaEvento reservaEvento){


        String regAfectados;
        int contador=0;
        //
        if(verificarIntegridad(reservaEvento, 8))
        {
            if (verificarIntegridad(reservaEvento,13)){
                regAfectados="No se puede eliminar. Tiene registros asociados. ";
            }
            else {
                String where = "idReservaEvento='" + reservaEvento.getIdReservaEvento() + "'";
                where = where + " AND codigoEscuela='" + reservaEvento.getCodigoEscuela() + "'";
                where = where + " AND idTipoEvento='" + reservaEvento.getIdTipoEvento() + "'";
                contador += db.delete("reservaevento", where, null);
                regAfectados="Registro eliminado con éxito ";
            }
            return regAfectados ;
        }
        else
        {
            return "Registro con identificador " + reservaEvento.getCodigoEscuela() + " no existe";
        }

    }
    public ReservaEvento consultarReserva(int idreservaevento){

        String[] id = {String.valueOf(idreservaevento)};
        Cursor cursor = db.query("reservaevento", camposReservaEvento, "idReservaEvento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            ReservaEvento reservaEvento = new ReservaEvento();
            reservaEvento.setIdReservaEvento(cursor.getInt(0));
            reservaEvento.setCodigoEscuela(cursor.getString(1));
            reservaEvento.setIdTipoEvento(cursor.getString(2));
            reservaEvento.setNombreEvento(cursor.getString(3));
            reservaEvento.setCapacidadTotalEvento(cursor.getInt(4));
            reservaEvento.setFechaReservaEvento(cursor.getString(5));
            return reservaEvento;
        }else{
            return null;
        }
    }
    public ArrayList<ReservaEvento> consultarReservas() {

        abrirConsultar();
        ReservaEvento reservaEvento;
        ArrayList<ReservaEvento> lista = new ArrayList<ReservaEvento>();

        Cursor cursor = abrirConsultar().rawQuery("SELECT * FROM reservaevento", null);
        while(cursor.moveToNext()){
            reservaEvento = new ReservaEvento();
            reservaEvento.setIdReservaEvento(cursor.getInt(0));
            lista.add(reservaEvento);
        }
        return lista;
    }
    //-----------------------DetalleReserva-----------------------------------------------------------------------
    public String insertar(DetalleReservaEvento detalleReservaEvento){

        String regInsertados;
        long contador=0;


        if (verificarIntegridad(detalleReservaEvento, 9)) {
            regInsertados = "Error. Registro duplicado";
        } else {
            ContentValues detalle = new ContentValues();
            detalle.put("idHorario", detalleReservaEvento.getIdHorario());
            detalle.put("idReservaEvento", detalleReservaEvento.getIdReservaEvento());
            detalle.put("codigoLocal", detalleReservaEvento.getCodigoLocal());
            contador = db.insert("detallereserva", null, detalle);
            if (contador==-1){
                regInsertados = "Error. No se encontro alguno de los valores insertados, verifique.";
            }
            else {
                regInsertados = "Registro insertado exitosamente ";
            }
        }
        return regInsertados;
    }
    public String actualizar(DetalleReservaEvento detalleReservaEvento){
        int contador=0;
        String[] id = {String.valueOf(detalleReservaEvento.getIdReservaEvento())};
        ContentValues reservas = new ContentValues();
        reservas.put("idHorario", detalleReservaEvento.getIdHorario());
        reservas.put("idReservaEvento", detalleReservaEvento.getIdReservaEvento());
        reservas.put("codigoLocal", detalleReservaEvento.getCodigoLocal());
        contador+=db.update("detallereserva", reservas, "idReservaEvento = ?", id);
        if (contador==0 || contador==-1){
            return "Error. Verifique que los datos sean correctos";
        }
        else {
            return "Registro Actualizado Correctamente";
        }
    }
    public String eliminar(DetalleReservaEvento detalleReservaEvento){
        String regAfectados="El registro se elimino ";
        int contador=0;

        if (verificarIntegridad(detalleReservaEvento,9)) {
            String where="idHorario = '"+detalleReservaEvento.getIdHorario()+"'";
            where+=" AND idReservaEvento = '"+detalleReservaEvento.getIdReservaEvento()+"'";
            where+=" AND codigoLocal = '"+detalleReservaEvento.getCodigoLocal()+"'";
            contador+=db.delete("detallereserva", where, null);
            regAfectados+=contador;
            return regAfectados;
        }
        else{
            return "El registro no se encontró.";
        }


    }
    public DetalleReservaEvento consultarDetalle( int idHorario, int idReservaEvento, String codLocal){
        String[] id = {String.valueOf(idHorario), String.valueOf(idReservaEvento), codLocal};
        Cursor cursor = db.query("detallereserva", camposDetalleReserva, "idHorario = ? AND idReservaEvento = ? AND codigoLocal= ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleReservaEvento detalleReservaEvento=new DetalleReservaEvento();
            detalleReservaEvento.setIdHorario(cursor.getInt(0));
            detalleReservaEvento.setIdReservaEvento(cursor.getInt(1));
            detalleReservaEvento.setCodigoLocal(cursor.getString(2));

            return detalleReservaEvento;
        }else{
            return null;
        }
    }

    // Insertar Docente
    public String insertar(Docente docente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        // verificar que no exista docente
        if(verificarIntegridadJavier(docente,1))
        {
            regInsertados= "Error al Insertar el registro ya existe, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues doce = new ContentValues();
            doce.put("carnetDocente", docente.getCarnetDocente());
            doce.put("nombreDocente", docente.getNombreDocente());
            doce.put("apellido", docente.getApellido());
            doce.put("rol", docente.getRol());
            doce.put("nomEscuela", docente.getNomEscuela());
            contador=db.insert("docente", null, doce);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Metodo Consultar Docente
    public Docente consultarDocente(String carnetDocente){

        String[] id = {carnetDocente};
        Cursor cursor = db.query("docente", camposDocente, "carnetDocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente doce = new Docente();
            doce.setCarnetDocente(cursor.getString(0));
            doce.setNombreDocente(cursor.getString(1));
            doce.setApellido(cursor.getString(2));
            doce.setRol(cursor.getString(3));
            doce.setNomEscuela(cursor.getString(4));
            return doce;
        }else{
            return null;
        }
    }

    //Metodo de Actualizar Docente
    public String actualizar(Docente docente){
        //Si existe
        if(verificarIntegridadJavier(docente, 1)){
            String[] id = {docente.getCarnetDocente()};
            ContentValues cv = new ContentValues();
            cv.put("nombreDocente", docente.getNombreDocente());
            cv.put("apellido", docente.getApellido());
            cv.put("rol", docente.getRol());
            cv.put("nomEscuela", docente.getNomEscuela());
            db.update("docente", cv, "carnetDocente = ?", id);
            return "Registro del Docente se Actualizo Correctamente";
        }else{
            return "Registro con carnet del Docente " + docente.getCarnetDocente() + " no existe";
        }
    }

    //Metodo eliminar docente
    public String eliminar(Docente docente){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(docente, 1))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("docente", "carnetDocente='"+docente.getCarnetDocente()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con carnet del Docente  " + docente.getCarnetDocente() + " no existe";
        }

        return regAfectados;
    }

    //Insertar Rol docente
    public String insertar(RolDocente rolDocente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        // verificar que no exista el rol docente
        if(verificarIntegridadJavier(rolDocente,2))
        {
            regInsertados= "Error al Insertar el rol para el docente ya existe, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues rol = new ContentValues();
            rol.put("nomRolDocente", rolDocente.getNomRolDocente());
            contador=db.insert("rolDocente", "idRolDocente", rol);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Consultar el rol del Docente
    public RolDocente consultarRolDocente(String nomRolDocente){

        String[] id = {nomRolDocente};
        Cursor cursor = db.query("rolDocente", camposRolDocente, "nomRolDocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            RolDocente rol = new RolDocente();
            rol.setIdRolDocente(cursor.getInt(0));
            rol.setNomRolDocente(cursor.getString(1));
            return rol;
        }else{
            return null;
        }
    }

    //Actualizar el rol del Docente
    public String actualizar(RolDocente rolDocente){
        //Si existe
        if(verificarIntegridadJavier(rolDocente, 2)){
            String[] id = {rolDocente.getNomRolDocente()};
            ContentValues variable = new ContentValues();
            variable.put("nomRolDocente", rolDocente.getNomRolDocente());
            db.update("rolDocente", variable, "nomRolDocente = ?", id);
            return "Registro del Rol del Docente Actualizado Correctamente";
        }else{
            return "El Rol Docente " + rolDocente.getNomRolDocente() + " no existe";
        }
    }

    public String actualizar1(RolDocente rolDocente){
        //verifica si existe un rol docente existente
        if(verificarIntegridadJavier(rolDocente, 2)){
            return "Registro del Rol del Docente que quiere actualizar ya existe";
        }else{
            String[] id = {String.valueOf(rolDocente.getIdRolDocente())};
            ContentValues variable = new ContentValues();
            variable.put("idRolDocente", rolDocente.getIdRolDocente());
            variable.put("nomRolDocente", rolDocente.getNomRolDocente());
            db.update("rolDocente", variable, "idRolDocente = ?", id);
            return "Registro del Rol del Docente se Actualizo Correctamente";
        }

    }

    //eliminar el rol del Docente
    public String eliminar(RolDocente rolDocente){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(rolDocente, 2))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("rolDocente", "nomRolDocente='"+rolDocente.getNomRolDocente()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con el Rol Docente " + rolDocente.getNomRolDocente() + " no existe";
        }

        return regAfectados;
    }

    //Insertar Tipo Local
    public String insertar(TipoLocal tipoLocal){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista el tipo de local
        if(verificarIntegridadJavier(tipoLocal,3))
        {
            regInsertados= "Error al Insertar el registro del Tipo Local, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues tipo = new ContentValues();
            tipo.put("nomTipoLocal", tipoLocal.getNomTipoLocal());
            contador=db.insert("tipoLocal", "idTipoLocal", tipo);
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    //Consultar el Tipo Local
    public TipoLocal consultarTipoLocal(String nomTipoLocal){

        String[] id = {nomTipoLocal};
        Cursor cursor = db.query("tipoLocal", camposTipoLocal, "nomTipoLocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoLocal local = new TipoLocal();
            local.setIdTipoLocal(cursor.getInt(0));
            local.setNomTipoLocal(cursor.getString(1));
            return local;
        }else{
            return null;
        }
    }

    //Actualizar el Tipo de Local
    public String actualizar(TipoLocal tipoLocal){
        //verifica si existe un tipo Local
        if(verificarIntegridadJavier(tipoLocal, 3)){
            return "Registro del Tipo Local que quiere actualizar ya existe";
        }else{
            String[] id = {String.valueOf(tipoLocal.getIdTipoLocal())};
            ContentValues variable = new ContentValues();
            variable.put("idTipoLocal", tipoLocal.getIdTipoLocal());
            variable.put("nomTipoLocal", tipoLocal.getNomTipoLocal());
            db.update("tipoLocal", variable, "idTipoLocal = ?", id);
            return "Registro del Tipo Local se Actualizo Correctamente";
        }

    }

    //eliminar el rol del Docente
    public String eliminar(TipoLocal tipoLocal){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe carnet
        if(verificarIntegridadJavier(tipoLocal, 3))        {
            //si tiene registros relacionados, se eliminan primero
            /*if (verificarIntegridad(alumno,3)) {
                contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
            }*/
            contador+=db.delete("tipoLocal", "nomTipoLocal='"+tipoLocal.getNomTipoLocal()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con el Tipo Local " + tipoLocal.getNomTipoLocal() + " no existe";
        }

        return regAfectados;
    }

    //consultar todos los roles
    public ArrayList<RolDocente> consultarTodosRol(){
        abrir();
        ArrayList<String> listaString =new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM rolDocente",null);
        ArrayList<RolDocente> lista = new ArrayList<RolDocente>();
        while(cursor.moveToNext()){
            RolDocente rolDocente = new RolDocente();
            rolDocente.setIdRolDocente(cursor.getInt(0));
            rolDocente.setNomRolDocente(cursor.getString(1));
            lista.add(rolDocente);
        }
        return lista;
    }

    //Insertar Usuario
    public String insertar(Usuario usuario){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista el usuario
        if(verificarIntegridadJavier(usuario,4))
        {
            regInsertados= "Error al Insertar el registro del Usuario, Registro Duplicado. Verificar inserción";
        }else if(verificarIntegridadJavier(usuario,6)){
            ContentValues cv = new ContentValues();
            cv.put("username", usuario.getUsername());
            cv.put("password", usuario.getPassword());
            cv.put("is_admin", usuario.getIs_admin());
            cv.put("is_docente", usuario.getIs_docente());
            cv.put("nombres", usuario.getNombres());
            cv.put("apellidos", usuario.getApellidos());
            cv.put("correo", usuario.getCorreo());
            cv.put("carnetDocente", usuario.getCarnetDocente());
            contador=db.insert("usuario", "iduser", cv);
            regInsertados=regInsertados+contador;
        } else if (verificarIntegridadJavier(usuario,5)){
            ContentValues cv = new ContentValues();
            cv.put("username", usuario.getUsername());
            cv.put("password", usuario.getPassword());
            cv.put("is_admin", usuario.getIs_admin());
            cv.put("is_docente", usuario.getIs_docente());
            cv.put("nombres", usuario.getNombres());
            cv.put("apellidos", usuario.getApellidos());
            cv.put("correo", usuario.getCorreo());
            cv.put("carnetDocente", usuario.getCarnetDocente());
            contador=db.insert("usuario", "iduser", cv);
            regInsertados=regInsertados+contador;
        } else{
            regInsertados = "Error al Insertar el registro el Carnet no existe, Verificar inserción";
        }
        return regInsertados;
    }

    //Actualizar Usuario
    public String actualizar(Usuario usuario){
        //Si existe
        if(verificarIntegridadJavier(usuario, 4)){
            String[] id = {usuario.getUsername()};
            ContentValues cv = new ContentValues();
            cv.put("password", usuario.getPassword());
            cv.put("nombres", usuario.getNombres());
            cv.put("apellidos", usuario.getApellidos());
            cv.put("correo", usuario.getCorreo());
            db.update("usuario", cv, "username = ?", id);
            return "Registro del Docente se Actualizo Correctamente";
        }else{
            return "Registro con el Username: " + usuario.getUsername() + " no existe";
        }
    }

    //Consultar Usuario
    public Usuario consultarUsuario(String username){

        String[] id = {username};
        Cursor cursor = db.rawQuery("select password, nombres, apellidos, correo from usuario where username='"+username+"'", null  );
        if(cursor.moveToFirst()){
            Usuario u = new Usuario();
            u.setPassword(cursor.getString(0));
            u.setNombres(cursor.getString(1));
            u.setApellidos(cursor.getString(2));
            u.setCorreo(cursor.getString(3));
            return u;
        }else{
            return null;
        }
    }

    //Eliminar Usuario
    public String eliminar(Usuario usuario){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //Si existe usuario
        if(verificarIntegridadJavier(usuario, 4))        {
            contador+=db.delete("usuario", "username='"+usuario.getUsername()+"'", null);
            regAfectados+=contador;
        }
        else
        {
            return "Registro con username del Usuario:  " + usuario.getUsername() + " no existe";
        }

        return regAfectados;
    }

    //LOGIN
    public boolean verificarUser(Usuario user){
        String[] id = {user.getUsername()};
        abrirConsultar();
        Usuario u = new Usuario();
        Cursor cursor = db.rawQuery("select username, password from usuario where username = '"+user.getUsername()+"' ",null);
        if (cursor.moveToFirst()){

            u.setUsername(cursor.getString(0));
            u.setPassword(cursor.getString(1));
        }

        if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    public Usuario verificarAdmin(Usuario user){
        String [] id = {user.getUsername()};
        Usuario u = new Usuario();
        Cursor cursor = db.rawQuery("select is_admin,username,is_docente from usuario where username = '"+user.getUsername()+"' ",null);
        if (cursor.moveToFirst()){
            u.setIs_admin(cursor.getString(0));
            u.setUsername(cursor.getString(1));
            u.setIs_docente(cursor.getString(2));
            return u;
        }else {
            return null;
        }
    }

    public Usuario verificarCarnet(Usuario user){
        String [] id = {user.getUsername()};
        Usuario u = new Usuario();
        Cursor cursor = db.rawQuery("select carnetDocente from usuario where username = '"+user.getUsername()+"' ",null);
        if (cursor.moveToFirst()){
            u.setCarnetDocente(cursor.getString(0));
            return u;
        }else {
            return null;
        }
    }

    public Docente verificarUsuarios(Usuario user){
        String [] id = {user.getCarnetDocente()};
        Docente d = new Docente();
        Cursor cursor = db.rawQuery("select rol from docente where carnetDocente = '"+user.getCarnetDocente()+"' ",null);
        if (cursor.moveToFirst()){
            d.setRol(cursor.getString(0));
            return d;
        }else {
            return null;
        }
    }

    //------Escuela--------------------------------------------------------------------------
    public String insertar(Escuela escuela){

        String regInsertados;
        long contador=0;
        if(verificarIntegridad(escuela, 10))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues escuela1 = new ContentValues();
            escuela1.put("codigoEscuela", escuela.getCodigoEscuela());
            escuela1.put("nomEscuela", escuela.getNomEscuela());
            contador=db.insert("escuela", null, escuela1);
            regInsertados="Insertado con exito.";
        }

        return regInsertados;
    }
    public String actualizar(Escuela escuela){

        // * 2 Verificar que el registro que exista
        if(verificarIntegridad(escuela, 10)){
            String[] id = {escuela.getCodigoEscuela()};
            ContentValues cv = new ContentValues();
            cv.put("nomEscuela", escuela.getNomEscuela());
            db.update("escuela", cv, "codigoEscuela = ? ", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }
    public String eliminar(Escuela escuela){

        String regAfectados;
        int contador=0;

        if(verificarIntegridad(escuela, 10))
        {
            if (verificarIntegridad(escuela,11)){
                regAfectados="No puede eliminar este resgistro, es usado en otra tabla";
            }
            else {
                String where = "codigoEscuela='" + escuela.getCodigoEscuela() + "'";
                contador += db.delete("escuela", where, null);
                regAfectados="Registro eliminado";
            }
            return regAfectados;
        }
        else
        {
            return "Registro no Existe";
        }
    }
    public Escuela consultarEscuela(String codEscuela){

        String[] id = {codEscuela};
        Cursor cursor = db.query("escuela", camposEscuela, "codigoEscuela = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Escuela escuela = new Escuela();
            escuela.setCodigoEscuela(cursor.getString(0));
            escuela.setNomEscuela(cursor.getString(1));
            return escuela;
        }else{
            return null;
        }
    }
    //---------------------------------------------------------------------------------------
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch (relacion){
            case 1:
            {
                //verificar que al insertar cargaAcademica exista codigoAsignatura de asignatura
                CargaAcademica cargaAcademica = (CargaAcademica)dato;
                String[] id1 = {cargaAcademica.getcodigoAsignatura()};
                String[] id2 = {cargaAcademica.getcodigoCiclo()};
                String[] id3 = {String.valueOf(cargaAcademica.getidRolDocente())};
                String[] id4 = {cargaAcademica.getcarnetDocente()};
                abrir();
                Cursor cursor1 = db.query("asignatura", null, "codigoAsignatura = ?", id1, null, null, null);
                //Cursor cursor2 = db.query("ciclo", null, "codigoCiclo = ?", id2, null, null, null);
                Cursor cursor3 = db.query("roldocente", null, "idroldocente = ?", id3, null, null, null);
                Cursor cursor4 = db.query("docente", null, "carnetDocente = ?", id4, null, null, null);
                if(cursor1.moveToFirst() && cursor3.moveToFirst() && cursor4.moveToFirst() ){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 2:
            {
                //verificar que al modificar cargaAcademica exista codigoAsignatura del asignatura, el codigo de horario y el codigoCiclo
                CargaAcademica cargaAcademica1 = (CargaAcademica)dato;
                String[] ids = {cargaAcademica1.getcodigoAsignatura(),cargaAcademica1.getcodigoCiclo(),cargaAcademica1.getcarnetDocente() };
                abrir();
                Cursor c = db.query("cargaAcademica", null, "codigoAsignatura = ? AND codigoCiclo = ? AND carnetDocente = ? ", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 3:
            {// verifica que no exista para poder eliminar
                Asignatura asignatura = (Asignatura)dato;
                Cursor c=db.query(true, "cargaAcademica", new String[] {"codigoAsignatura" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);
                //Cursor c1=db.query(true, "detalleGrupoReserva", new String[] {"idHorario" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);
                //Cursor c2=db.query(true, "grupo", new String[] {"idHorario" }, "codigoAsignatura='"+asignatura.getCodigoAsignatura()+"'",null, null, null, null, null);

                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            case 4:
            {//verifica que no exista para poder eliminar
                Horario horario = (Horario)dato;
                Cursor dghor=db.query(true, "detalleGrupoReserva", new String[] {"idHorario" }, "idHorario='"+horario.getidHorario()+"'",null, null, null, null, null);
                Cursor drhor=db.query(true, "detalleReservaEvento", new String[] {"idHorario" }, "idHorario='"+horario.getidHorario()+"'",null, null, null, null, null);

                if(dghor.moveToFirst() && drhor.moveToFirst())
                   return true; //Se encontraron datos
                else
                    return false;
            }
            case 5:
            {
                //verificar que exista asignatura
                Asignatura asignatura2 = (Asignatura)dato;
                String[] id = {asignatura2.getCodigoAsignatura()};
                abrir();
                Cursor c2 = db.query("asignatura", null, "codigoAsignatura = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Asignatura
                    return true;
                }
                return false;
            }
            case 6:
            {
                //verificar que exista Horario
                Horario horario2 = (Horario)dato;
                String[] idm = {String.valueOf(horario2.getidHorario())};
                abrir();
                Cursor cm = db.query("horario", null, "idHorario = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Horario
                    return true;
                }
                return false;
            }
            case 7:{
                TipoEvento tipoEvento = (TipoEvento) dato;
                String[] id = {tipoEvento.getIdTipoEvento()};
                abrir();
                Cursor c2 = db.query("tipoevento", null, "idtipoevento = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Tipo de evento
                    return true;
                }
                return false;
            }
            case 8:{
                //verificar que exista codigoEscuela en ReservaEvento
                ReservaEvento reserva1 = (ReservaEvento) dato;
                String[] id = {String.valueOf(reserva1.getCodigoEscuela())};
                abrir();
                Cursor c2 = db.query("reservaevento", null, "codigoEscuela = ? ", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }
            case 9: {
                DetalleReservaEvento detalleReservaEvento = (DetalleReservaEvento) dato;
                String[] ids = {String.valueOf(detalleReservaEvento.getIdHorario()),String.valueOf(detalleReservaEvento.getIdReservaEvento()), detalleReservaEvento.getCodigoLocal()};
                abrir();
                Cursor c = db.query("detallereserva", null, "idHorario = ? AND idReservaEvento = ? AND codigoLocal= ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 10:{
                Escuela escuela = (Escuela) dato;
                String[] ids = {escuela.getCodigoEscuela()};
                abrir();
                Cursor c = db.query("escuela", null, "codigoEscuela = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 11:
            {
                Escuela escuela = (Escuela) dato;
                Cursor c=db.query(true, "reservaevento", new String[] {"codigoEscuela" }, "codigoEscuela='"+escuela.getCodigoEscuela()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            case 12:
            {
                TipoEvento tipoEvento = (TipoEvento) dato;
                Cursor c=db.query(true, "reservaevento", new String[] {"idtipoevento" }, "idtipoevento='"+tipoEvento.getIdTipoEvento()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            case 13:
            {
                ReservaEvento reservaEvento = (ReservaEvento) dato;
                Cursor c=db.query(true, "detallereserva", new String[] {"idReservaEvento" }, "idReservaEvento='"+reservaEvento.getIdReservaEvento()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true; //Se encontraron datos
                else
                    return false;
            }
            default:
                return false;
        }
    }

    /*verificacion de integridad de javier */
    private boolean verificarIntegridadJavier(Object dato, int relacion) throws SQLException {

        switch (relacion) {

            case 1: {
                //verificar que exista un docente
                Docente docente2 = (Docente) dato;
                String[] id = {docente2.getCarnetDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "carnetDocente = ?", id, null, null, null);
                if (c2.moveToFirst()) {
                    //Se encontro Docente
                    return true;
                }
                return false;
            }

            case 2: {
                //verificar que exista un rol docente
                RolDocente rolDocente2 = (RolDocente) dato;
                String[] id = {rolDocente2.getNomRolDocente()};
                abrir();
                Cursor c2 = db.query("rolDocente", null, "nomRolDocente = ?", id, null, null, null);
                if (c2.moveToFirst()) {
                    //Se encontro el rol docente
                    return true;
                }
                return false;
            }

            case 3: {
                //verificar que exista el tipo de local
                TipoLocal tipoLocal2 = (TipoLocal) dato;
                String[] id = {tipoLocal2.getNomTipoLocal()};
                abrir();
                Cursor c2 = db.query("tipoLocal", null, "nomTipoLocal = ?", id, null, null, null);
                if (c2.moveToFirst()) {
                    //Se encontro el rol docente
                    return true;
                }
                return false;
            }

            case 4:
            {
                //verificar si exsite el usuario
                Usuario usuario2 = (Usuario) dato;
                String[] id = {usuario2.getUsername()};
                abrir();
                Cursor c2 = db.query("usuario", null, "username = ?",id, null, null, null);
                if(c2.moveToFirst()){
                    //el usuario ya existe
                    return true;
                }
                return false;
            }

            case 5:
            {
                //verificar si carnet del docente
                Usuario usuario2 = (Usuario) dato;
                String[] id = {usuario2.getCarnetDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "carnetDocente = ?",id, null, null, null);
                if(c2.moveToFirst()){
                    //el carnet docente existe
                    return true;
                }
                return false;
            }

            case 6:
            {
                Usuario usuario2 = (Usuario) dato;
                //verifica si no es docente
                if(usuario2.getIs_docente().equals("No")){
                    //si no es docente
                    return true;
                }
                return false;
            }

            default:
                return false;

        }


    }


    private boolean verificarIntegridadLM(Object dato, int relacion) throws SQLException{
                switch(relacion){

                    case 1:
                    {
//verificar que al insertar un dia no Habil exista un ciclo
                        DiasNoHabiles dianohabil = (DiasNoHabiles) dato;
                        String[] id1 = {dianohabil.getCodigoCiclo()
                        };
                        abrir();
                        Cursor cursor1 = db.query("ciclo", null, "codigociclo = ?", id1, null, null, null);
                        if (cursor1.moveToFirst()) {
//Se encontraron datos
                            return true;
                        }
                        return false;
                    }
                    case 2: {
//verificar que al insertar grupo exista codigoAsignatura de asignatura y el codigoCiclo de ciclo
                        Grupo grupo = (Grupo) dato;
                        String[] id1 = {String.valueOf(grupo.getIdRolDocente())};
                        String[] id2 = {grupo.getCodigoAsignatura()};
                        String[] id3 = {grupo.getCodigoCiclo()};
                        String[] id4 = {grupo.getCarnetDocente()};
                        abrir();
                        Cursor cursor1 = db.query("rolDocente", null, "idroldocente = ?", id1, null, null, null);
                        Cursor cursor2 = db.query("asignatura", null, "codigoasignatura = ?", id2, null, null, null);
                        Cursor cursor3 = db.query("ciclo", null, "codigociclo = ?", id3, null, null, null);
                        Cursor cursor4 = db.query("docente", null, "carnetdocente = ?", id4, null, null, null);
                        if (cursor1.moveToFirst()&& cursor2.moveToFirst() && cursor3.moveToFirst()&& cursor4.moveToFirst()) {
//Se encontraron datos
                            return true;
                        }
                        return false;
                    }
                    //verificar que no exista codigociclo en dias no habiles
                    case 3:
                    {
                        Ciclo ciclo1 = (Ciclo)dato;
                        abrir();
                        Cursor c=db.query(true, "diasnohabiles", new String[] {
                                "codigociclo" }, "codigociclo='"+ciclo1.getCodigoCiclo()+"'",null, null, null, null, null);
                        if(c.moveToFirst())
                            return true;
                        else
                            return false;
                    }

                    case 4:
                    {
//verificar que exista ciclo
                        Ciclo ciclo = (Ciclo)dato;
                        String[] id = {ciclo.getCodigoCiclo()};
                        abrir();
                        Cursor c2 = db.query("ciclo", null, "codigoCiclo = ?", id, null, null, null);
                        if(c2.moveToFirst()){
//Se encontro Ciclo
                            return true;
                        }
                        return false;
                    }
                    case 5:
                    {
//verificar que exista Grupo
                        Grupo grupo2 = (Grupo)dato;
                        String[] idg = {grupo2.getIdGrupo()};
                        abrir();
                        Cursor cm = db.query("grupo", null, "idgrupo = ?", idg, null, null, null);
                        if(cm.moveToFirst()){
//Se encontro Grupo
                            return true;
                        }
                        return false;
                    }
                    ///eliminar ciclo verificando que no este ni en dias no habiles ni en carga academinca  codigocilo
                    case 6:
                    {
                        Ciclo ciclo2 = (Ciclo)dato;
                        Cursor c=db.query(true, "dianohabil", new String[] {
                                "codigociclo" }, "codigociclo='"+ciclo2.getCodigoCiclo()+"'",null, null, null, null, null);
                        Cursor c1=db.query(true, "cargaacademica", new String[] {
                                "codigociclo" }, "codigociclo='"+ciclo2.getCodigoCiclo()+"'",null, null, null, null, null);
                        if(c.moveToFirst() && c1.moveToFirst())
                            return true;
                        else
                            return false;
                    }
                    case 7:
                    {
//verificar que exista el dia no habil
                        DiasNoHabiles dianohabi = (DiasNoHabiles) dato;
                        String[] id2 = {dianohabi.getIdDiasNoHabiles()
                        };
                        abrir();
                        Cursor cursor1 = db.query("diasnohabiles", null, "iddiasnohabiles = ?", id2, null, null, null);
                        if (cursor1.moveToFirst()) {
//Se encontraron datos
                            return true;
                        }
                        return false;
                    }
                    case 8: {
//verificar que al modificar grupo exista idroldocente y carnetdocente del docente, idgrupo de grupo, el codigo de asognatura y el codigo ciclo
                        Grupo grupo1 = (Grupo) dato;
                        String[] ids = {grupo1.getIdGrupo(), String.valueOf(grupo1.getIdRolDocente()), grupo1.getCodigoAsignatura(),grupo1.getCodigoCiclo(),grupo1.getCarnetDocente()};
                        abrir();
                        Cursor c = db.query("grupo", null, "idgrupo = ? AND idroldocente = ? AND codigoasignatura = ? AND codigociclo = ? AND carnetdocente = ?", ids, null, null, null);
                        if (c.moveToFirst()) {
//Se encontraron datos
                            return true;
                        }
                        return false;
                    }
            default:
                return false;

        }


    }

    public String llenarBD(){
        // tabla local ------------------------- 4 tuplas
        final String[] VLcodigoLocal = {"localesfia01","localesfia02","localesfia03","localesfia04"};
        final String[] VLidEncargadoLocal = {"01","02","03","04"};
        final String[] VLidTipoLocal = {"AU","AL","AL","AU"};
        final String[] VLubicacionLocal = {"EDIFICIO D","EDIFICIO D","EDIFICIO C","EDIFICIO B"};
        final Integer[] VLcapacidadLocal ={200,400,300,100};

        // tabla Asignatura--------------------- 4 tuplas
        final String[] VAcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115"};
        final String[] VAcodigoLocal = {"B11","C31","c31","B11"};
        final String[] VAcodigoEscuela = {"UDCB","EISI","Gonzales","EISI"};
        final String[] VAnomAsignatura = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
        final String[] VAidPrioridad = {"A","B","B","B"};

        //tabla Horario--------------------- 4 tuplas
        final Integer[] VHidHorario = {1,2,3,4};
        final String[] VHidDia = {"L-X","L-X","L-X","L-X"};
        final String[] VHhoraInicio = {"6:20","8:05","9:50","11:35"};
        final String[] VHhoraFin = {"8:00","9:45","11:30","13:15"};

        // tabla CargaAcademica--------------------- 7 tuplas
        final Integer[] VCidRolDocente = {1,1,2,2,3,1,1};
        final String[] VCcodigoAsignatura = {"MAT115","PRN115","IEC115","TSI115","IEC115","MAT115","PRN115"};
        final String[] VCcodigoCiclo = {"12016","12016","22016","22016","22020","22020","22016"};
        final String[] VCcarnetDocente = {"vvvvvvv","OO12035","sssssss","OF12044","ttttttt","vvvvvvv","OO12035"};

        //tabla rol docente
        final String[] VRDnomRolDocente = {"Administrador","Docente","Jefe de Catedra","Encargado de Laboratorio"};

        //tipo de local
        final String[] V_TPnomTipoLocal = {"B11","C11","D11","Lcomp1"};

        //Tabla docente
        final String[] VDcarnetDocente = {"OO12035","OF12044","GG11098","CC12021"};
        final String[] VDnombreDocente = {"Oscar","Fatima","Sara","Gabriela"};
        final String[] VDapellido = {"Rodriguez","Sanchez","Gonzales","Coto"};
        final String[] VDrol = {"Docente","Administrador","Administrador","Docente"};
        final String[] VDnomEscuela = {"Ing. Sistema Informatico","Ing. Sistema Informatico","Ing. Sistema informatico",
                "Ing. Sistema Informatico"};

        //Tabla escuela
        final String[] VEcodigoEscuel={"EISI", "EII", "EIQA"};
        final String[] VEnombreEscuela={"Escuela de Ingenieria de Sistemas Informaticos", "Escuela de Ingenieria Industrial", "Escuela de Ingernieria Quimica y Alimentos"};

        //TABLA CICLO
        final String[] VAcodigo = {"1-2020","2-2020"};
        final String[] VAfechainicio = {"2020-02-15","2020-08-07"};
        final String[] VAfechafin = {"2020-02-15","2021-01-22"};

        //TABLA DIAS NO HABILES
        final String[] VDidDiasNoHabiles = {"1","2","3","4","5"};
        final String[] VDCodigoClico = {"1-2020","2-2020","1-2020","2-2020","1-2020"};
        final String[] VDnomDiasNoHabiles = {"Day of Mother","Day of Dead","Day of Work","Day of Ing. Student","Day of Father"};
        final String[] VDfechaDesde = {"00:00:00 10-05-2020","00:00:00 02-10-2020","00:00:00 01-05-2020","00:00:00 07-08-2020","00:00:00 17-06-2020"};
        final String[] VDfechaHasta = {"24:00:00 10-05-2020","24:00:00 02-10-2020","24:00:00 01-05-2020","24:00:00 07-08-2020","24:00:00 17-06-2020"};

        //TABLA GRUPO
        final Integer[] VGidRolDocente = {1,2,3};
        final String[] VGcodigoAsignatura = {"MAT115","PRN115","IEC115"};
        final String[] VGcodigoCiclo = {"1-2020","2-2020","1-2020"};
        final String[] VGcarnetDocente = {"OO12035","OF12044","GG11098"};
        final String[] VGidGrupo = {"1","2","3"};
        final Integer[] VGnumMaximoEstudiantes = {100,60,89,90};

        abrir();
        db.execSQL("DELETE FROM Local");
        db.execSQL("DELETE FROM asignatura");
        db.execSQL("DELETE FROM horario");
        db.execSQL("DELETE FROM cargaAcademica");

        db.execSQL("DELETE FROM rolDocente;");
        db.execSQL("DELETE FROM tipoLocal;");
        db.execSQL("DELETE FROM docente;");
        db.execSQL("DELETE FROM usuario;");

        db.execSQL("DELETE FROM escuela");

        db.execSQL("DELETE FROM ciclo");
        db.execSQL("DELETE FROM diasnohabiles");
        db.execSQL("DELETE FROM grupo");

        //llenado tabla Asignatura
        Asignatura asignatura = new Asignatura();
        for(int i=0;i<4;i++){
            asignatura.setCodigoAsignatura(VAcodigoAsignatura[i]);
            asignatura.setCodigoLocal(VAcodigoLocal[i]);
            asignatura.setCodigoEscuela(VAcodigoEscuela[i]);
            asignatura.setNomAsignatura(VAnomAsignatura[i]);
            asignatura.setIdPrioridad(VAidPrioridad[i]);
            insertar(asignatura);
        }
        // llenado tabla local
        Local local = new Local();
        for(int i=0;i<4;i++){
            local.setCodigoLocal(VLcodigoLocal[i]);
            local.setIdEncargadoLocal(VLidEncargadoLocal[i]);
            local.setIdTipoLocal(VLidTipoLocal[i]);
            local.setCapacidadLocal(VLcapacidadLocal[i]);
            local.setUbicacionLocal(VLubicacionLocal[i]);
            insertar(local);
        }

        //llenado tabla Horario
        Horario horario = new Horario();
        for(int i=0;i<4;i++){ //i<4 porque son 4 tuplas los que se ingresan
            horario.setidHorario(VHidHorario[i]);
            horario.setidDia(VHidDia[i]);
            horario.sethoraInicio(VHhoraInicio[i]);
            horario.sethoraFin(VHhoraFin[i]);
            insertar(horario);
        }

        //llenado de la tabla rol docente
        RolDocente rolDocente = new RolDocente();
        for(int i=0;i<4;i++){
            rolDocente.setNomRolDocente(VRDnomRolDocente[i]);
            insertar(rolDocente);
        }

        //llenado de la tabla tipo local
        TipoLocal tipoLocal = new TipoLocal();
        for (int i=0;i<4;i++){
            tipoLocal.setNomTipoLocal(V_TPnomTipoLocal[i]);
            insertar(tipoLocal);
        }

        //llenado de las tabla docente
        Docente docente = new Docente();
        for(int i=0;i<4;i++){
            docente.setCarnetDocente(VDcarnetDocente[i]);
            docente.setNombreDocente(VDnombreDocente[i]);
            docente.setApellido(VDapellido[i]);
            docente.setRol(VDrol[i]);
            docente.setNomEscuela(VDnomEscuela[i]);
            insertar(docente);
        }


        //tabla Usuario
        db.execSQL("insert into usuario(iduser, username, password, is_admin, is_docente, nombres, apellidos, correo, carnetDocente) " +
                "values(1, 'admin', 'admin', 'Si', 'No', 'Administrador', 'Admin 1', 'Administrador@gmail.com', null)");
        db.execSQL("insert into usuario(iduser, username, password, is_admin, is_docente, nombres, apellidos, correo, carnetDocente) " +
                "values(2, 'docente', 'docente', 'No', 'Si', 'Oscar', 'Gonzales', 'Coordinador@gmail.com', 'OO12035')");
        db.execSQL("insert into usuario(iduser, username, password, is_admin, is_docente, nombres, apellidos, correo, carnetDocente) " +
                "values(3, 'docente1', 'docente1', 'No', 'Si', 'Fatima', 'Sanchez', 'FatimaSanchez1@gmail.com', 'OF12044')");
        db.execSQL("insert into usuario(iduser, username, password, is_admin, is_docente, nombres, apellidos, correo, carnetDocente) " +
                "values(4, 'usuario', 'usuario', 'No', 'No', 'Pablo', 'Marmol', 'Administrador1@gmail.com', null)");

        //llenado tabla CargaAcademica
        CargaAcademica cargaAcademica = new CargaAcademica();
        for(int i=0;i<7;i++){ //i<7 porque son 7 tuplas los que se ingresan
            cargaAcademica.setidRolDocente(VCidRolDocente[i]);
            cargaAcademica.setcodigoAsignatura(VCcodigoAsignatura[i]);
            cargaAcademica.setcodigoCiclo(VCcodigoCiclo[i]);
            cargaAcademica.setcarnetDocente(VCcarnetDocente[i]);
            insertar(cargaAcademica);
        }

        //Llenado de la tabla escuela
        Escuela escuela=new Escuela();
        for(int i=0; i<3; i++){
            escuela.setCodigoEscuela(VEcodigoEscuel[i]);
            escuela.setNomEscuela(VEnombreEscuela[i]);
            insertar(escuela);
        }
        //CICLO
        Ciclo ciclo = new Ciclo();
        for(int i=0;i<2;i++){
            ciclo.setCodigoCiclo(VAcodigo[i]);
            ciclo.setFechaInicio(VAfechainicio[i]);
            ciclo.setFechaFin(VAfechafin[i]);
            insertar(ciclo);
        }
        //DIAS NO HABILES
        DiasNoHabiles dnh = new DiasNoHabiles();
        for(int i=0;i<5;i++){
            dnh.setIdDiasNoHabiles(VDidDiasNoHabiles[i]);
            dnh.setCodigoCiclo(VDCodigoClico[i]);
            dnh.setNomDiasNoHabiles(VDnomDiasNoHabiles[i]);
            dnh.setFechaDesde(VDfechaDesde[i]);
            dnh.setFechaHasta(VDfechaHasta[i]);
            insertar(dnh);
        }
        //GRUPO
        Grupo grup = new Grupo();
        for(int i=0;i<3;i++){
            grup.setIdRolDocente(VGidRolDocente[i]);
            grup.setCodigoAsignatura(VGcodigoAsignatura[i]);
            grup.setCodigoCiclo(VGcodigoCiclo[i]);
            grup.setCarnetDocente(VGcarnetDocente[i]);
            grup.setIdGrupo(VGidGrupo[i]);
            grup.setNumMaximoEstudiantes(VGnumMaximoEstudiantes[i]);
            insertar(grup);
        }



        cerrar();
        return "Guardado Correctamente";
    }
}
