package ues.fia.eisi.reservalocalfia;

public class RolDocente {

    private Integer idRolDocente;
    private String nomRolDocente;

    public RolDocente(){

    }

    public RolDocente(Integer idRolDocente, String nomRolDocente) {
        this.idRolDocente = idRolDocente;
        this.nomRolDocente = nomRolDocente;
    }

    public Integer getIdRolDocente() {
        return idRolDocente;
    }

    public String getNomRolDocente() {
        return nomRolDocente;
    }

    public void setIdRolDocente(Integer idRolDocente) {
        this.idRolDocente = idRolDocente;
    }

    public void setNomRolDocente(String nomRolDocente) {
        this.nomRolDocente = nomRolDocente;
    }

}
