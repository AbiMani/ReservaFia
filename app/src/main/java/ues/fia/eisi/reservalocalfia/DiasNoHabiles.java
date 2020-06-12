package ues.fia.eisi.reservalocalfia;

public class DiasNoHabiles {

    private String idDiasNoHabiles;
    private String codigoCiclo;
    private String nomDiasNoHabiles;
    private String fechaDesde;
    private String fechaHasta;

    public DiasNoHabiles() {
    }

    public DiasNoHabiles(String idDiasNoHabiles, String codigoCiclo, String nomDiasNoHabiles, String fechaDesde, String fechaHasta) {
        this.idDiasNoHabiles = idDiasNoHabiles;
        this.codigoCiclo = codigoCiclo;
        this.nomDiasNoHabiles = nomDiasNoHabiles;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public String getIdDiasNoHabiles() {
        return idDiasNoHabiles;
    }

    public void setIdDiasNoHabiles(String idDiasNoHabiles) {
        this.idDiasNoHabiles = idDiasNoHabiles;
    }

    public String getCodigoCiclo() {
        return codigoCiclo;
    }

    public void setCodigoCiclo(String codigoCiclo) {
        this.codigoCiclo = codigoCiclo;
    }

    public String getNomDiasNoHabiles() {
        return nomDiasNoHabiles;
    }

    public void setNomDiasNoHabiles(String nomDiasNoHabiles) {
        this.nomDiasNoHabiles = nomDiasNoHabiles;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
