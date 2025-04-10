/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreriaconexion;
import java.sql.*;

/**
 *
 * @author jpobl
 */ 
public class ConexionMySQL     {
    
    private static Connection conexion = null;  
    
    public static boolean conectar(String puerto, String nombreBD, String USUARIO, String CONTRASEÑA) {
        
        String URL = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD ;
        
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa");
            return true;
            
        } catch (SQLException e) {
            int codigo = e.getErrorCode();
            String estado = e.getSQLState();
            System.out.println("Error al conectar a la base de datos:");

            switch (codigo) {
                case 1045:
                    System.out.println(" - Usuario o contraseña incorrectos.");
                    break;
                case 1049:
                    System.out.println(" - La base de datos '" + nombreBD + "' no existe.");
                    break;
                case 0:
                    System.out.println(" - No se pudo conectar al servidor MySQL (Posible error de puerto).");
                    break;
                default:
                    System.out.println(" - Código de error: " + codigo + " | Estado SQL: " + estado);
                    System.out.println(" - Detalles técnicos: " + e.getMessage());
            }
            conexion = null;
            return false;
        }
    }
    
    public static void crearTabla(String nombreTabla, String Columnas) {
        if (conexion == null) {
            System.out.println("No hay conexión activa.");
            return;
        }

        String[] columnas = Columnas.split(",");

        StringBuilder consulta = new StringBuilder("CREATE TABLE IF NOT EXISTS " + nombreTabla + " (");
                                consulta.append("id INT AUTO_INCREMENT PRIMARY KEY");

        for (String columna : columnas) {
            String columnaLimpia = columna.trim(); // columnaLimpia -> una columna sin espacios antes o despues de la cadena
            if (!columnaLimpia.isEmpty()) {
                consulta.append(", ").append(columnaLimpia).append(" VARCHAR(100) NOT NULL");
            }
        }

        consulta.append(")");

        try (Statement stmt = conexion.createStatement()) {
            
            stmt.executeUpdate(consulta.toString());
            System.out.println("Tabla '" + nombreTabla + "' creada con columnas: id, " + String.join(", ", columnas));
            
        } catch (SQLException e) {
            int codigo = e.getErrorCode();
            System.out.println("Error al crear la tabla '" + nombreTabla + "':");

            switch (codigo) {
                case 1064:
                    System.out.println(" - Error de sintaxis en la consulta SQL.");
                    break;
                case 1050:
                    System.out.println(" - La tabla ya existe.");
                    break;
                default:
                    System.out.println(" - Código de error: " + codigo);
                    System.out.println(" - Detalles técnicos: " + e.getMessage());
            }
        }
    }

    
    public static void eliminarTabla(String nombreTabla){
        
        if(conexion == null){
            System.out.println("No hay conexión activa.");
            return;
        }
        
        String sql= "DROP TABLE " + nombreTabla ;
        try(Statement stmt = conexion.createStatement()){
            stmt.executeUpdate(sql);
            System.out.println("Tabla eliminada correctamente. ");
        } catch(SQLException e){
            int codigo = e.getErrorCode();
            System.out.println("Error al eliminar la tabla '" + nombreTabla + "':");

            switch (codigo) {
                case 1051:
                    System.out.println(" - La tabla no existe.");
                    break;
                default:
                    System.out.println(" - Código de error: " + codigo);
                    System.out.println(" - Detalles técnicos: " + e.getMessage());
            }
        }
    }
    
    public static Connection getConexion(){
        return conexion;
    }
    
    public static void buscarTabla(String nombreTabla) {
        if (conexion == null) {
            System.out.println("No hay conexión activa.");
            return;
        }

        String sql = "SELECT * FROM " + nombreTabla;
        boolean mostrarTodo = true; 

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean hayResultados = false;

            while (rs.next()) {
                hayResultados = true;
                if (mostrarTodo) {
                    int columnas = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnas; i++) {
                        String nombreCol = rs.getMetaData().getColumnName(i);
                        String dato = rs.getString(i);
                        System.out.print(nombreCol + ": " + dato + " | ");
                    }
                    System.out.println();
                } else {
                    System.out.println(rs.getString(1)); 
                }
            }

            if (!hayResultados) {
                System.out.println("La tabla está vacía o no se encontraron registros.");
            }

        } catch (SQLException e) {
            System.out.println("No se pudo consultar la tabla '" + nombreTabla + "'. Verifica que exista. Detalles: " + e.getMessage());
        }
    }

    public static void buscarRegistro(String nombreTabla, String columna, String valor) {
        if (conexion == null) {
            System.out.println("No hay conexión.");
            return;
        }

        String sql = "SELECT * FROM " + nombreTabla + " WHERE " + columna + " = '" + valor + "'";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrado = false;
            while (rs.next()) {
                encontrado = true;
                int columnas = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= columnas; i++) {
                    String nombre = rs.getMetaData().getColumnName(i);
                    String dato = rs.getString(i);
                    System.out.println(nombre + ": " + dato);
                }
                System.out.println("--------------------");
            }

            if (!encontrado) {
                System.out.println("No se encontró ningún registro.");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar en la tabla '" + nombreTabla + "'. Verifica que el nombre de la tabla y columna sean correctos. Detalles: " + e.getMessage());
        }
    }
    
}
