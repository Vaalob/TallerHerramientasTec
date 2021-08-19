package Datos;

public class Trabajador extends Persona {
    
    private String acceso;
    private String login;
    private String password;

    public Trabajador() {
    }

    public Trabajador(String acceso, String login, String password) {
        this.acceso = acceso;
        this.login = login;
        this.password = password;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
