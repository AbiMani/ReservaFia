package ues.fia.eisi.reservalocalfia;

public class ReservaEvento {
    private int idReservaEvento;
    private String codigoEscuela;
    private String idTipoEvento, nombreEvento;
    private  int capacidadTotalEvento;
    private String fechaReservaEvento;

    public ReservaEvento() {
    }

    public ReservaEvento(int idReservaEvento, String codigoEscuela, String idTipoEvento, String nombreEvento, int capacidadTotalEvento, String fechaReservaEvento) {
        this.idReservaEvento = idReservaEvento;
        this.codigoEscuela = codigoEscuela;
        this.idTipoEvento = idTipoEvento;
        this.nombreEvento = nombreEvento;
        this.capacidadTotalEvento = capacidadTotalEvento;
        this.fechaReservaEvento = fechaReservaEvento;
    }

    public int getIdReservaEvento() {
        return idReservaEvento;
    }

    public void setIdReservaEvento(int idReservaEvento) {
        this.idReservaEvento = idReservaEvento;
    }

    public String getCodigoEscuela() {
        return codigoEscuela;
    }

    public void setCodigoEscuela(String codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }

    public String getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(String idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public int getCapacidadTotalEvento() {
        return capacidadTotalEvento;
    }

    public void setCapacidadTotalEvento(int capacidadTotalEvento) {
        this.capacidadTotalEvento = capacidadTotalEvento;
    }

    public String getFechaReservaEvento() {
        return fechaReservaEvento;
    }

    public void setFechaReservaEvento(String fechaReservaEvento) {
        this.fechaReservaEvento = fechaReservaEvento;
    }
}
