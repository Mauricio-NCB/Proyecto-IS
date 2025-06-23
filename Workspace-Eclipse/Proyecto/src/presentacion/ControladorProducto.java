package presentacion;

import java.util.Date;
import java.util.List;

import negocio.dto.TCamiseta;
import negocio.dto.TEntrada;
import negocio.dto.TJuguete;
import negocio.dto.TPoster;
import negocio.dto.TProducto;
import negocio.sa.SAProducto;
import negocio.sa.SAProductoImp;

public class ControladorProducto {

    private static ControladorProducto instancia;
    private SAProducto prod;
    
    private ControladorProducto() {
        this.prod = new SAProductoImp();
    }
    
    public static ControladorProducto getInstance() {
        if (instancia == null) {
            instancia = new ControladorProducto();
        }
        return instancia;
    }
    
    public void nuevoProducto(TProducto producto) throws Exception {
        try {
            prod.altaProducto(producto);
            new VentanaNuevoProd().setVisible(true);
        }
        catch (Exception e) {
            throw new Exception("Error al crear nuevo producto: " + e.getMessage());
        }
    }

    public void deleteProducto(String idStr) throws Exception {
        try {
            int id = Integer.parseInt(idStr);
            prod.deleteProducto(id);
        }
        catch (NumberFormatException e) {
            throw new Exception("ID inválido: " + idStr);
        }
        catch (Exception e) {
            throw new Exception("Error al eliminar producto: " + e.getMessage());
        }
    }
    
    public List<TProducto> listarProductos() throws Exception {
        try {
            return prod.listarProductos();        
        }
        catch(Exception e) {
            throw new Exception("Error al listar productos: " + e.getMessage());
        }
    }

    public void registrarPoster(String nombre, String precioStr, String stockStr, String tamano) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || 
                stockStr.trim().isEmpty() || tamano == null || tamano.trim().isEmpty()) {
                throw new Exception("Datos inválidos para el poster");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            TPoster nuevoPoster = new TPoster(nombre.trim(), precio, stock, tamano.trim());
            prod.altaProducto(nuevoPoster); 
        } 
        catch (NumberFormatException e) {
            throw new Exception("Formato de precio o stock inválido");
        } 
        catch (Exception e) {
            throw new Exception("Error al registrar poster: " + e.getMessage());
        }
    }

    public void actualizarPoster(String idStr, String nombre, String precioStr, String stockStr, String tamano) throws Exception {
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || 
                precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || 
                tamano == null || tamano.trim().isEmpty()) {
                throw new Exception("Datos incorrectos al actualizar el poster");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            int id = Integer.parseInt(idStr);
            TPoster nuevoPoster = new TPoster(id, nombre.trim(), precio, stock, tamano.trim());
            prod.updateProducto(nuevoPoster); 
        } 
        catch (NumberFormatException e) {
            throw new Exception("Formato de precio, stock o ID inválido");
        } 
        catch (Exception e) {
            throw new Exception("Error al actualizar poster: " + e.getMessage());
        }
    }

    public void registrarCamiseta(String nombre, String precioStr, String stockStr, String tallaStr, 
                                String dorsal, String numJugadorStr) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || 
                stockStr.trim().isEmpty() || tallaStr.trim().isEmpty() || 
                dorsal == null || numJugadorStr.trim().isEmpty()) {
                throw new Exception("Datos inválidos para la camiseta");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            int talla = Integer.parseInt(tallaStr);
            int numJugador = Integer.parseInt(numJugadorStr);
            
            TCamiseta nuevaCamiseta = new TCamiseta(nombre.trim(), precio, stock, talla, dorsal.trim(), numJugador);
            prod.altaProducto(nuevaCamiseta); 
        }
        catch (NumberFormatException e) {
            throw new Exception("Formato numérico inválido en los datos de la camiseta");
        }
        catch (Exception e) {
            throw new Exception("Error al registrar camiseta: " + e.getMessage());
        }
    }

    public void actualizarCamiseta(String idStr, String nombre, String precioStr, String stockStr, 
                                 String tallaStr, String dorsal, String numJugadorStr) throws Exception {
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || 
                precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || 
                tallaStr.trim().isEmpty() || dorsal == null || numJugadorStr.trim().isEmpty()) {
                throw new Exception("Datos inválidos para actualizar la camiseta");
            }
            
            int id = Integer.parseInt(idStr);
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            int talla = Integer.parseInt(tallaStr);
            int numJugador = Integer.parseInt(numJugadorStr);
            
            TCamiseta nuevaCamiseta = new TCamiseta(id, nombre.trim(), precio, stock, talla, dorsal.trim(), numJugador);
            prod.updateProducto(nuevaCamiseta); 
        }
        catch (NumberFormatException e) {
            throw new Exception("Formato numérico inválido en los datos de la camiseta");
        }
        catch (Exception e) {
            throw new Exception("Error al actualizar camiseta: " + e.getMessage());
        }
    }

    public void registrarEntrada(String nombre, String precioStr, String stockStr, Date fecha, String hora, 
                              String ubicacion, String numeroAsiento, String partido) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || 
                stockStr.trim().isEmpty() || fecha == null || hora == null || 
                hora.trim().isEmpty() || ubicacion == null || ubicacion.trim().isEmpty() || 
                numeroAsiento == null || partido == null || partido.trim().isEmpty()) {
                throw new Exception("Datos inválidos para la entrada");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            TEntrada nuevaEntrada = new TEntrada(nombre.trim(), precio, stock, fecha, hora.trim(), 
                                               ubicacion.trim(), numeroAsiento.trim(), partido.trim());
            prod.altaProducto(nuevaEntrada); 
        }
        catch (NumberFormatException e) {
            throw new Exception("Formato de precio o stock inválido");
        }
        catch (Exception e) {
            throw new Exception("Error al registrar entrada: " + e.getMessage());
        }
    }

    public void actualizarEntrada(String idStr, String nombre, String precioStr, String stockStr, Date fecha, 
                               String hora, String ubicacion, String numeroAsiento, String partido) throws Exception {
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || 
                precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || fecha == null || 
                hora == null || hora.trim().isEmpty() || ubicacion == null || 
                ubicacion.trim().isEmpty() || numeroAsiento == null || partido == null || 
                partido.trim().isEmpty()) {
                throw new Exception("Datos inválidos para actualizar la entrada");
            }
            
            int id = Integer.parseInt(idStr);
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            TEntrada nuevaEntrada = new TEntrada(id, nombre.trim(), precio, stock, fecha, hora.trim(), 
                                               ubicacion.trim(), numeroAsiento.trim(), partido.trim());
            prod.updateProducto(nuevaEntrada); 
        }
        catch (NumberFormatException e) {
            throw new Exception("Formato de ID, precio o stock inválido");
        }
        catch (Exception e) {
            throw new Exception("Error al actualizar entrada: " + e.getMessage());
        }
    }
    
    public void registrarJuguete(String nombre, String precioStr, String stockStr, String tipo, String tamano) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || 
                stockStr.trim().isEmpty() || tipo.trim().isEmpty() || tamano.trim().isEmpty()) {
                throw new Exception("Datos inválidos para el juguete");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            TJuguete nuevoJuguete = new TJuguete(nombre.trim(), precio, stock, tipo.trim(), tamano.trim());
            prod.altaProducto(nuevoJuguete); 
        } 
        catch (NumberFormatException e) {
            throw new Exception("Formato de precio o stock inválido");
        } 
        catch (Exception e) {
            throw new Exception("Error al registrar juguete: " + e.getMessage());
        }
    }

    public void actualizarJuguete(String idStr, String nombre, String precioStr, String stockStr, 
                                String tipo, String tamano) throws Exception {
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || 
                precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || 
                tipo.trim().isEmpty() || tamano.trim().isEmpty()) {
                throw new Exception("Datos inválidos para el juguete");
            }
            
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            int id = Integer.parseInt(idStr);
            TJuguete nuevoJuguete = new TJuguete(id, nombre.trim(), precio, stock, tipo.trim(), tamano.trim());
            prod.updateProducto(nuevoJuguete); 
        } 
        catch (NumberFormatException e) {
            throw new Exception("Formato de ID, precio o stock inválido");
        } 
        catch (Exception e) {
            throw new Exception("Error al actualizar juguete: " + e.getMessage());
        }
    }

    public void eliminarProducto(String idStr) throws Exception {
        try {
            if (idStr == null || idStr.trim().isEmpty()) {
                throw new Exception("El ID del producto a eliminar no puede estar vacío");
            }
            
            int id = Integer.parseInt(idStr);
            prod.deleteProducto(id);
        }
        catch (NumberFormatException e) {
            throw new Exception("ID inválido: " + idStr);
        }
        catch (Exception e) {
            throw new Exception("Error al eliminar producto: " + e.getMessage());
        }
    }
}