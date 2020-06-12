package ues.fia.eisi.reservalocalfia;

public class Grupo {

    private int idRolDocente;
    private String codigoAsignatura;
    private String codigoCiclo;
    private String carnetDocente;
    private String idGrupo;
    private int numMaximoEstudiantes;

    public Grupo() {
    }

    public Grupo(int idRolDocente, String codigoAsignatura, String codigoCiclo, String carnetDocente, String idGrupo, int numMaximoEstudiantes) {
        this.idRolDocente = idRolDocente;
        this.codigoAsignatura = codigoAsignatura;
        this.codigoCiclo = codigoCiclo;
        this.carnetDocente = carnetDocente;
        this.idGrupo = idGrupo;
        this.numMaximoEstudiantes = numMaximoEstudiantes;
    }

    public int getIdRolDocente() {
        return idRolDocente;
    }

    public void setIdRolDocente(int idRolDocente) {
        this.idRolDocente = idRolDocente;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getCodigoCiclo() {
        return codigoCiclo;
    }

    public void setCodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public String getCarnetDocente() {
        return carnetDocente;
    }

    public void setCarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getNumMaximoEstudiantes() {
        return numMaximoEstudiantes;
    }

    public void setNumMaximoEstudiantes(int numMaximoEstudiantes) {
        this.numMaximoEstudiantes = numMaximoEstudiantes;
    }
}
