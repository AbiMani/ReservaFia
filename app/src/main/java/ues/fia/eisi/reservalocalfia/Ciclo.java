package ues.fia.eisi.reservalocalfia;

public class Ciclo{

    private String codigoCiclo;
    private String fechaInicio;
    private String fechaFin;

    public Ciclo(){
    }

    public Ciclo(String codigoCiclo, String fechaInicio, String fechaFin) {
        this.codigoCiclo = codigoCiclo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {

        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {

        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCodigoCiclo() {

        return codigoCiclo;
    }

    public void setCodigoCiclo(String codigoCiclo) {

        this.codigoCiclo = codigoCiclo;
    }
}
