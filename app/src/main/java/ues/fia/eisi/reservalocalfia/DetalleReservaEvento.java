package ues.fia.eisi.reservalocalfia;

public class DetalleReservaEvento {
    private int iddetalle;
    private int  idHorario;
    private int idReservaEvento;
    private  String codigoLocal;

    public DetalleReservaEvento() {
    }

    public DetalleReservaEvento(int idHorario, int idReservaEvento, String codigoLocal) {
        this.idHorario = idHorario;
        this.idReservaEvento = idReservaEvento;
        this.codigoLocal = codigoLocal;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdReservaEvento() {
        return idReservaEvento;
    }

    public void setIdReservaEvento(int idReservaEvento) {
        this.idReservaEvento = idReservaEvento;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }
}

