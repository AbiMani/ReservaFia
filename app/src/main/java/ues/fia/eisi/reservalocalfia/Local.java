package ues.fia.eisi.reservalocalfia;

public class Local {
    public Local() {

    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    private String codigoLocal;

    public void setCapacidadLocal(Integer capacidadLocal) {
        this.capacidadLocal = capacidadLocal;
    }

    public Local(String codigoLocal, String idEncargadoLocal, String idTipoLocal, String ubicacionLocal, Integer capacidadLocal) {
        this.codigoLocal = codigoLocal;
        this.idEncargadoLocal = idEncargadoLocal;
        this.idTipoLocal = idTipoLocal;
        this.ubicacionLocal = ubicacionLocal;
        this.capacidadLocal = capacidadLocal;
    }

    public String getIdEncargadoLocal() {
        return idEncargadoLocal;
    }

    public void setIdEncargadoLocal(String idEncargadoLocal) {
        this.idEncargadoLocal = idEncargadoLocal;
    }

    private String idEncargadoLocal;

    public String getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setIdTipoLocal(String idTipoLocal) {

    }

    private String idTipoLocal;

    public String getUbicacionLocal() {
        return ubicacionLocal;
    }

    public void setUbicacionLocal(String ubicacionLocal) {
        this.ubicacionLocal = ubicacionLocal;
    }

    private String ubicacionLocal;

    public Integer getCapacidadLocal() {
        return capacidadLocal;
    }

    public void setCapacidadLocal(Float capacidadLocal) {

    }

    private Integer capacidadLocal;
}
