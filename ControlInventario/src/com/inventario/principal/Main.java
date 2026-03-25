package com.inventario.principal;

import com.inventario.dao.UsuarioDAO;
import com.inventario.modelo.Usuario;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para probar el CRUD de usuarios.
 * Menú interactivo en consola.
 */
public class Main {
    
    // Objeto DAO para las operaciones de base de datos
    private static final UsuarioDAO dao = new UsuarioDAO();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE CONTROL DE USUARIOS");
        System.out.println("========================================");
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    insertarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    buscarUsuario();
                    break;
                case 4:
                    actualizarUsuario();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            
            // Pausa para leer el resultado antes de volver al menú
            if (continuar) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    // Mostrar el menú de opciones
    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Insertar usuario");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Buscar usuario por ID");
        System.out.println("4. Actualizar usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Salir");
    }
    
    // 1. INSERTAR USUARIO
    private static void insertarUsuario() {
        System.out.println("\n--- INSERTAR USUARIO ---");
        
        Usuario usuario = new Usuario();
        usuario.setNombre(leerTexto("Nombre: "));
        usuario.setUsername(leerTexto("Username: "));
        usuario.setPassword(leerTexto("Password: "));
        usuario.setEmail(leerTexto("Email: "));
        
        if (dao.insertar(usuario)) {
            System.out.println("✅ Usuario insertado exitosamente.");
        } else {
            System.out.println("❌ Error al insertar usuario.");
        }
    }
    
    // 2. LISTAR USUARIOS
    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        
        List<Usuario> lista = dao.listar();
        
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : lista) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + u.getIdUsuario());
                System.out.println("Nombre: " + u.getNombre());
                System.out.println("Username: " + u.getUsername());
                System.out.println("Email: " + u.getEmail());
            }
            System.out.println("--------------------------------");
            System.out.println("Total: " + lista.size() + " usuario(s).");
        }
    }
    
    // 3. BUSCAR USUARIO
    private static void buscarUsuario() {
        System.out.println("\n--- BUSCAR USUARIO POR ID ---");
        
        int id = leerEntero("ID del usuario: ");
        Usuario usuario = dao.buscarPorId(id);
        
        if (usuario != null) {
            System.out.println("\n✅ Usuario encontrado:");
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Username: " + usuario.getUsername());
            System.out.println("Email: " + usuario.getEmail());
        } else {
            System.out.println("❌ Usuario no encontrado.");
        }
    }
    
    // 4. ACTUALIZAR USUARIO
    private static void actualizarUsuario() {
        System.out.println("\n--- ACTUALIZAR USUARIO ---");
        
        int id = leerEntero("ID del usuario a actualizar: ");
        Usuario usuario = dao.buscarPorId(id);
        
        if (usuario != null) {
            System.out.println("\nDatos actuales del usuario:");
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Username: " + usuario.getUsername());
            System.out.println("Email: " + usuario.getEmail());
            
            System.out.println("\nIngrese los nuevos datos (deje vacío para mantener el actual):");
            
            String nombre = leerTexto("Nombre [" + usuario.getNombre() + "]: ");
            String username = leerTexto("Username [" + usuario.getUsername() + "]: ");
            String password = leerTexto("Password [" + usuario.getPassword() + "]: ");
            String email = leerTexto("Email [" + usuario.getEmail() + "]: ");
            
            // Si el usuario deja vacío, mantenemos el valor actual
            if (!nombre.isEmpty()) usuario.setNombre(nombre);
            if (!username.isEmpty()) usuario.setUsername(username);
            if (!password.isEmpty()) usuario.setPassword(password);
            if (!email.isEmpty()) usuario.setEmail(email);
            
            if (dao.actualizar(usuario)) {
                System.out.println("✅ Usuario actualizado exitosamente.");
            } else {
                System.out.println("❌ Error al actualizar usuario.");
            }
        } else {
            System.out.println("❌ Usuario no encontrado.");
        }
    }
    
    // 5. ELIMINAR USUARIO
    private static void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        
        int id = leerEntero("ID del usuario a eliminar: ");
        Usuario usuario = dao.buscarPorId(id);
        
        if (usuario != null) {
            System.out.println("\n¿Está seguro de eliminar al usuario?");
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Username: " + usuario.getUsername());
            
            System.out.print("Confirme (SI/NO): ");
            String confirmacion = scanner.nextLine().toUpperCase();
            
            if (confirmacion.equals("SI")) {
                if (dao.eliminar(id)) {
                    System.out.println("✅ Usuario eliminado exitosamente.");
                } else {
                    System.out.println("❌ Error al eliminar usuario.");
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("❌ Usuario no encontrado.");
        }
    }
    
    // Métodos auxiliares para leer entrada del usuario
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número válido.");
            }
        }
    }
}