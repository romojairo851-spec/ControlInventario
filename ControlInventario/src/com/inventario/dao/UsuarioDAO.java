package com.inventario.dao;

import com.inventario.conexion.Conexion;
import com.inventario.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para gestionar operaciones CRUD de usuarios en la base de datos.
 * CRUD: Create (Insertar), Read (Consultar), Update (Actualizar), Delete (Eliminar)
 */
public class UsuarioDAO {
    
    // 1. INSERTAR USUARIO (Create)
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, username, password, email) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Asignamos los valores a los signos de interrogación (?)
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getEmail());
            
            // Ejecutamos la inserción
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si insertó al menos 1 fila, retornamos true
            
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // 2. CONSULTAR TODOS LOS USUARIOS (Read - All)
    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios ORDER BY idUsuario ASC";
        List<Usuario> lista = new ArrayList<>();
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            // Recorremos el resultado fila por fila
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEmail(rs.getString("email"));
                
                lista.add(usuario);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        
        return lista;
    }
    
   // 3. CONSULTAR UN USUARIO POR ID (Read - One) - VERSIÓN SIMPLIFICADA
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Usuario usuario = null;
    
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Obtener conexión
            conn = Conexion.getConnection();

            // 2. Validar que la conexión no sea nula
            if (conn != null) {
                // 3. Preparar la consulta
                ps = conn.prepareStatement(sql);
                ps.setInt(1, idUsuario);

                // 4. Ejecutar y procesar resultados
                rs = ps.executeQuery();

                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());

        } finally {
            // 5. Cerrar recursos manualmente en orden inverso (ResultSet -> Statement -> Connection)
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conn != null) conn.close(); } catch (SQLException e) { /* ignorar */ }
        }

        return usuario;
    }
    
    // 4. ACTUALIZAR USUARIO (Update)
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, username=?, password=?, email=? WHERE idUsuario=?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getIdUsuario());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // 5. ELIMINAR USUARIO (Delete)
    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}