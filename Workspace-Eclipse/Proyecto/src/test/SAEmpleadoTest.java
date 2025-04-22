package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import negocio.sa.SAEmpleadoImp;

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
}
