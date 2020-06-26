package ues.fia.eisi.reservalocalfia;

public class ReservaEvento {
    private int idReservaEvento;
    private String codigoEscuela;
    private String nombreTipoEvento, nombreEvento;
    private  int capacidadTotalEvento;
    private String fechaReservaEvento, fechaFinalizacion, local, codigoCiclo;
    private String horario;
    private  String estado="Pendiente";

    public ReservaEvento() {
    }

    public ReservaEvento(int idReservaEvento, String codigoEscuela, String nombreTipoEvento, String nombreEvento, int capacidadTotalEvento,
                         String fechaReservaEvento, String fechaFinalizacion, String local, String codigoCiclo, String horario,
                         String estado) {
        this.idReservaEvento = idReservaEvento;
        this.codigoEscuela = codigoEscuela;
        this.nombreTipoEvento = nombreTipoEvento;
        this.nombreEvento = nombreEvento;
        this.capacidadTotalEvento = capacidadTotalEvento;
        this.fechaReservaEvento = fechaReservaEvento;
        this.fechaFinalizacion=fechaFinalizacion;
        this.local=local;
        this.codigoCiclo=codigoCiclo;
        this.horario=horario;
        this.estado=estado;
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

    public String getNombreTipoEvento() {
        return nombreTipoEvento;
    }

    public void setNombreTipoEvento(String idTipoEvento) {
        this.nombreTipoEvento = idTipoEvento;
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

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCodigoCiclo() {
        return codigoCiclo;
    }

    public void setCodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
