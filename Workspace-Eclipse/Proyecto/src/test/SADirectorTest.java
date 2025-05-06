package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.sa.SADirectorImp;
import util.HashUtil;

class SADirectorTest {

	private SADirectorImp saDirector;
	
	@BeforeEach
	public void setUp() {
		saDirector = new SADirectorImp();
	}
	
	
	@Test
    public void testAltaDirectoryEliminar() {
		
        // Datos de usuario 1 en la BD
        String id = "EMP006";
        String nombre = "Juan Jose";
        Float sueldo = 7500.50F;
        String contrasena = "123";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saDirector.altaDirector(new TDirector(id, nombre, sueldo, contrasenaHash, "DIRECTOR PRUEBA"));
        
        assertTrue(exito, "El director debería existir");

        exito = saDirector.eliminarDirector(id);

        assertTrue(exito, "El director debería haber sido eliminado");
    }
	
	@Test
    public void TestActualizaDirector() {
		
        // Datos de usuario 1 en la BD
        String id = "EMP001";
        Float sueldo = 7500.50F;
        String contrasena = "nuevapass";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saDirector.actualizaDatosDirector(id, sueldo, contrasenaHash);
        
        assertTrue(exito, "El director debería haber sido actualizado");
    }
	
	@Test
    public void TestErrorActualizaDirector() {
		
        // No existe usuario
        String id = "1";
        Float sueldo = 7500.50F;
        String contrasena = "nuevapass";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saDirector.actualizaDatosDirector(id, sueldo, contrasenaHash);
        
        assertTrue(exito, "El director debería haber sido actualizado");
    }
}
