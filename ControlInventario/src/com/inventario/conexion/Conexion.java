package com.inventario.conexion;

// 1. Importamos las clases necesarias de Java para trabajar con bases de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // 2. Variables con los datos de acceso a la base de datos
    // 'private static final' significa que son constantes: no cambian durante la ejecución
  // AGREGAMOS: &allowPublicKeyRetrieval=true
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_inventario?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "inventario";        // ← Nuevo usuario
    private static final String PASSWORD = "inventario123";  // ← Nueva contraseña
    
    // 3. Método público y estático para obtener la conexión
    // 'public': para que otras clases (como Main) puedan usarlo
    // 'static': para poder llamarlo sin crear un objeto: Conexion.getConnection()
    // 'Connection': es el tipo de dato que devuelve (el puente a la BD)
    public static Connection getConnection() {
        Connection connection = null; // Iniciamos la variable como nula
        
        try {
            // 4. Cargamos el "driver" o controlador de MySQL
            // Esto le dice a Java: "prepara el traductor para hablar con MySQL"
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 5. Intentamos conectar usando la URL, usuario y contraseña
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Si llegamos aquí, ¡la conexión funcionó!
            System.out.println(">>> Conexión exitosa a la base de datos.");
            
        } catch (ClassNotFoundException e) {
            // Este error sale si no encuentra el driver de MySQL (el archivo .jar)
            System.err.println("Error: No se encontró el driver de MySQL. ¿Lo agregaste a las librerías?");
            System.err.println("Detalle: " + e.getMessage());
            
        } catch (SQLException e) {
            // Este error sale si la contraseña es incorrecta, la BD no existe, o MySQL está apagado
            System.err.println("Error: No se pudo conectar a la base de datos.");
            System.err.println("Detalle: " + e.getMessage());
        }
        
        // 6. Devolvemos la conexión (puede ser válida o null si hubo error)
        return connection;
    }
}