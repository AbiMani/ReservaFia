package ues.fia.eisi.reservalocalfia;

public class CargaAcademica {
    private Integer idRolDocente;
    private String codigoCiclo;
    private String carnetDocente;
    private String codigoAsignatura;

    public CargaAcademica(){}

    public CargaAcademica(Integer idRolDocente, String codigoAsignatura, String codigoCiclo, String carnetDocente){
        this.idRolDocente = idRolDocente;
        this.codigoAsignatura = codigoAsignatura;
        this.codigoCiclo = codigoCiclo;
        this.carnetDocente = carnetDocente;
    }

    public Integer getidRolDocente() {
        return idRolDocente;
    }

    public void setidRolDocente(Integer idRolDocente) {
        this.idRolDocente = idRolDocente;
    }

    public String getcarnetDocente() {
        return carnetDocente;
    }

    public void setcarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

    public String getcodigoCiclo() {
        return codigoCiclo;
    }

    public void setcodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public String getcodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setcodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

}
