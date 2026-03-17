package Presentacion;

import java.util.Collection;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;
import negocio.dto.TCliente;


public class controlador {
	
	public SAClienteImp sAClienteImp;

    public void accion(final TCliente evento, final int datos) {
    }
	
    public static void accion(int evento, Object datos) {

        switch (evento) {

            // -------------------------
            // CLIENTE
            // -------------------------

            case Evento.ALTA_CLIENTE: {   // 1 = ALTA_CLIENTE
                // TBD (To Be Developed)
                break;
            }

            case Evento.MOSTRAR_CLIENTES: {   // 2 = MOSTRAR_CLIENTES

                SACliente saCliente = new SAClienteImp();
                Collection<TCliente> res = saCliente.leerTodosClientes();

                if (!res.isEmpty()) {
                    VistaCliente.actualizar(Evento.RES_MOSTRAR_CLIENTES_OK, res);
                } else {
                    VistaCliente.actualizar(Evento.RES_MOSTRAR_CLIENTES_KO, null);
                }

                break;
            }

            case Evento.BUSCAR_CLIENTE: {   // 3 = BUSCAR_CLIENTE
                // TBD
                break;
            }

            case Evento.ELIMINAR_CLIENTE: {   // 4 = ELIMINAR_CLIENTE
                // TBD
                break;
            }

            case Evento.MODIFICAR_CLIENTE: {   // 5 = MODIFICAR_CLIENTE
                // TBD
                break;
            }
        }
    }
}
