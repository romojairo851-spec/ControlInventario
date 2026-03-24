package com.inventario.principal;

import com.inventario.conexion.Conexion;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando Prueba de Conexión ---");
        
        // Llamamos al método estático de nuestra clase Conexion
        Connection conn = Conexion.getConnection();
        
        if (conn != null) {
            System.out.println("¡Éxito! Estamos conectados a la base de datos.");
            // En una aplicación real, aquí cerraríamos la conexión, 
            // pero para esta prueba la dejamos abierta para verificar.
        } else {
            System.out.println("Falló la conexión. Revisa la consola para más detalles.");
        }
        
        System.out.println("--- Fin de la Prueba ---");
    }
}