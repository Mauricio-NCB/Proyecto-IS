import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("cdf49310-6a92-4c05-ba8f-f4eb20c7674e")
public class Cliente {
    @objid ("f1d4791d-303d-426e-ab47-3b2c1d65358a")
    private int numSocio;

    @objid ("a426ed6e-d063-4053-8a8d-8b1f1b2f0d5b")
    private String nombre;

    @objid ("8de46b3f-c69c-4471-9c74-dacd04b3a9be")
    private String direccion;

    @objid ("ac424dfd-8a7c-4395-9e51-7c270a569618")
    private String correo;

    @objid ("d13137dc-ed18-46ef-8f1e-1688a6beb0a2")
    public List<Factura>  = new ArrayList<Factura> ();

    @objid ("0fe1baa4-47b7-4e7d-9ec1-ffb978fa34b5")
    public List<Venta>  = new ArrayList<Venta> ();

    @objid ("598b3fce-1d56-4a40-a1d2-b4a3a0519fdc")
    public Cliente(final String nombre, final String direccion, final String correo) {
    }

    @objid ("8a47f3c3-3c2d-4905-9906-ac1f69cfd5af")
    public int getNumSocio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numSocio;
    }

    @objid ("6100defb-211d-4dae-84f8-4e46b0321b4b")
    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    @objid ("d2c06eba-cd22-4d73-bcc2-5cc22bbf9cf0")
    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    @objid ("208c6501-90be-42d9-9a6e-7d129ab047f0")
    public String getDireccion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.direccion;
    }

    @objid ("8872f4cf-df9e-4160-b427-b6b474e08240")
    public void setDireccion(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.direccion = value;
    }

    @objid ("9052f4b8-3aaf-413b-a974-284c7c9db6fb")
    public String getCorreo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.correo;
    }

    @objid ("9bafffe5-8a38-4fa5-aaa3-398495939c0d")
    public void setCorreo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.correo = value;
    }

}
