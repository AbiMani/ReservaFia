package ues.fia.eisi.reservalocalfia;

public class AccesoUsuario {
    private String idOpcion, idUsuario;

    public AccesoUsuario() {
    }

    public AccesoUsuario(String idOpcion, String idUsuario) {
        this.idOpcion = idOpcion;
        this.idUsuario = idUsuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
