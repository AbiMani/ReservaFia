package ues.fia.eisi.reservalocalfia;

public class Docente {

    private String carnetDocente;
    private String nombreDocente;
    private String apellido;
    private String rol;
    private String nomEscuela;

    public Docente(){

    }

    public Docente(String carnetDocente, String nombreDocente, String apellido, String rol, String nomEscuela) {
        this.carnetDocente = carnetDocente;
        this.nombreDocente = nombreDocente;
        this.apellido = apellido;
        this.rol = rol;
        this.nomEscuela = nomEscuela;
    }

    public String getCarnetDocente() {
        return carnetDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRol() {
        return rol;
    }

    public String getNomEscuela() {
        return nomEscuela;
    }

    public void setCarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setNomEscuela(String nomEscuela) {
        this.nomEscuela = nomEscuela;
    }

}
