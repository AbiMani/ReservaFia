package ues.fia.eisi.reservalocalfia;

public class TipoEvento {
    private String idTipoEvento, nomTipoEvento;

    public TipoEvento() {

    }
    public TipoEvento(String idTipoEvento, String nomTipoEvento) {
        this.idTipoEvento = idTipoEvento;
        this.nomTipoEvento=nomTipoEvento;
    }

    public String getNomTipoEvento() {
        return nomTipoEvento;
    }

    public void setNomTipoEvento(String nomTipoEvento) {
        this.nomTipoEvento = nomTipoEvento;
    }

    public String getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(String idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }
}
