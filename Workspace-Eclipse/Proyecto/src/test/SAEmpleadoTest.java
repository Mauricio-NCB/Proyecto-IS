package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import negocio.sa.SAEmpleadoImp;
import util.HashUtil;

class SAEmpleadoTest {

	private SAEmpleadoImp saempleado;
	
	@BeforeEach
	public void setUp() {
		saempleado = new SAEmpleadoImp();
	}
	
	
	@Test
    public void testLoginCorrecto1() {
		
        // Datos de usuario 1 en la BD
        String id = "EMP001";
        String contrasena = "contrasena1";

        TEmpleado emp = saempleado.loguearEmpleado(id, contrasena);

        assertNotNull(emp, "El empleado debería existir");
        assertEquals(id, emp.getIdentificador(), "El ID debería coincidir");

        if (emp instanceof TDirector) {
            System.out.println("Es el director: " + emp.getNombre());
        } 
        else if (emp instanceof TDependiente) {
            System.out.println("Es el dependiente: " + emp.getNombre());
        }
    }
	
	@Test
    public void testLoginCorrecto2() {
		
        // Datos de usuario 2 en la BD
        String id = "EMP002";
        String contrasena = "contrasena2";

        TEmpleado emp = saempleado.loguearEmpleado(id, contrasena);

        assertNotNull(emp, "El empleado debería existir");
        assertEquals(id, emp.getIdentificador(), "El ID debería coincidir");

        if (emp instanceof TDirector) {
            System.out.println("Es el director: " + emp.getNombre());
        } 
        else if (emp instanceof TDependiente) {
            System.out.println("Es el dependiente: " + emp.getNombre());
        }
    }

    @Test
    public void testLoginIncorrecto() {
        TEmpleado emp = saempleado.loguearEmpleado("noexiste", "passwordfalsa");
        assertNull(emp, "No debería loguearse con credenciales inválidas");
    }

    	@Test
    public void testAltaDirectoryEliminar() {
		
        // Datos de usuario 1 en la BD
        String id = "EMP006";
        String nombre = "Juan Jose";
        Float sueldo = 7500.50F;
        String contrasena = "123";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saempleado.altaEmpleado(new TDirector(id, nombre, sueldo, contrasenaHash, "DIRECTOR PRUEBA"));
        
        assertTrue(exito, "El empleado debería existir");

        exito = saempleado.eliminarEmpleado(id);

        assertTrue(exito, "El empleado debería haber sido eliminado");
    }

    @Test
    public void TestActualizaDirector() {
		
        // Datos de usuario 1 en la BD
        String id = "EMP001";
        Float sueldo = 7500.50F;
        String contrasena = "nuevapass";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saempleado.actualizaDatosEmpleado(id, sueldo, contrasenaHash);
        
        assertTrue(exito, "El empleado debería haber sido actualizado");
    }
	
	@Test
    public void TestErrorActualizaDirector() {
		
        // No existe usuario
        String id = "1";
        Float sueldo = 7500.50F;
        String contrasena = "nuevapass";

        String contrasenaHash = HashUtil.hashPassword(contrasena);
        boolean exito = saempleado.actualizaDatosEmpleado(id, sueldo, contrasenaHash);
        
        assertTrue(exito, "El empleado debería haber sido actualizado");
    }
}
