package ues.fia.eisi.reservalocalfia;

public class Dia {
    String idDia;
    String nomDia;

    public void Dia(){}

    public void Dia(String idDia,String nomDia){
        this.idDia= idDia;
        this.nomDia=nomDia;
    }

    public String getIdDia() {
        return idDia;
    }

    public String getNomDia() {
        return nomDia;
    }

    public void setIdDia(String idDia) {
        this.idDia = idDia;
    }

    public void setNomDia(String nomDia) {
        this.nomDia = nomDia;
    }
}

