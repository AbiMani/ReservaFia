package ues.fia.eisi.reservalocalfia;

public class Usuario {

    //iduser, username, password, is_admin, is_active, nombres, apellidos, correo, carnetDocente
    private Integer iduser;
    private String username;
    private String password;
    private String is_admin;
    private String is_docente;
    private String nombres;
    private String apellidos;
    private String correo;
    private String carnetDocente;

    public Usuario() {
    }

    public Usuario(Integer iduser, String username, String password, String is_admin, String is_docente, String nombres, String apellidos, String correo, String carnetDocente) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
        this.is_docente = is_docente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.carnetDocente = carnetDocente;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getIs_docente() {
        return is_docente;
    }

    public void setIs_docente(String is_docente) {
        this.is_docente = is_docente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarnetDocente() {
        return carnetDocente;
    }

    public void setCarnetDocente(String carnetDocente) {
        this.carnetDocente = carnetDocente;
    }

}
