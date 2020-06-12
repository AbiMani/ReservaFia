package ues.fia.eisi.reservalocalfia;

public class TipoLocal {

    private Integer idTipoLocal;
    private String nomTipoLocal;

    public TipoLocal(){

    }

    public TipoLocal(Integer idTipoLocal, String nomTipoLocal) {
        this.idTipoLocal = idTipoLocal;
        this.nomTipoLocal = nomTipoLocal;
    }

    public Integer getIdTipoLocal() {
        return idTipoLocal;
    }

    public String getNomTipoLocal() {
        return nomTipoLocal;
    }

    public void setIdTipoLocal(Integer idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }

    public void setNomTipoLocal(String nomTipoLocal) {
        this.nomTipoLocal = nomTipoLocal;
    }

}
