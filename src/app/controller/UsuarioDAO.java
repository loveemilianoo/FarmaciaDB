package app.controller;

import app.entity.Usuario;
import javax.swing.table.DefaultTableModel;

public interface UsuarioDAO {
    public abstract Usuario validarUsuario(String login, String password);
    public abstract void insertaUsuario(Usuario usuario);
    public abstract void construirTabla (DefaultTableModel tabla);
    
}
