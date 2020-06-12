package ues.fia.eisi.reservalocalfia;

public class Escuela {
    private  String codigoEscuela, nomEscuela;

    public Escuela() {
    }

    public Escuela(String codigoEscuela, String nomEscuela) {
        this.codigoEscuela = codigoEscuela;
        this.nomEscuela = nomEscuela;
    }

    public String getCodigoEscuela() {
        return codigoEscuela;
    }

    public void setCodigoEscuela(String codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }

    public String getNomEscuela() {
        return nomEscuela;
    }

    public void setNomEscuela(String nomEscuela) {
        this.nomEscuela = nomEscuela;
    }
}
