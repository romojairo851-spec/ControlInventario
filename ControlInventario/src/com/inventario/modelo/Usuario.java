package com.inventario.modelo;

/**
 * Clase Modelo que representa la entidad 'usuarios' de la base de datos.
 * Los atributos coinciden exactamente con las columnas de la tabla SQL.
 */
public class Usuario {
    
    // 1. Atributos (Privados por encapsulamiento)
    // Usamos tipos de datos de Java equivalentes a los de MySQL
    private int idUsuario;          // INT AUTO_INCREMENT
    private String nombre;          // VARCHAR(100)
    private String username;        // VARCHAR(100)
    private String password;        // VARCHAR(100)
    private String email;           // VARCHAR(50)

    // 2. Constructores
    // Constructor vacío: Necesario para crear objetos sin datos iniciales
    public Usuario() {
    }

    // Constructor completo: Útil para crear un objeto con todos los datos de una vez
    public Usuario(int idUsuario, String nombre, String username, String password, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        
    }

    // 3. Métodos Getters y Setters (Accesores y Mutadores)
    // Permiten leer y modificar los atributos privados desde otras clases (como el DAO)
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    // 4. Método toString()
    // Útil para imprimir los datos del objeto en la consola de forma legible
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", username=" + username + ", email=" + email +  + '}';
    }
}