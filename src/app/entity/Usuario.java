package app.entity;

public class Usuario extends Persona{
    private String login, password;

    public Usuario(String login, String password, String nombre, String direccion, int edad, int telefono, char sexo) {
        super(nombre, direccion, edad, telefono, sexo);
        this.login = login;
        this.password = password;
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
