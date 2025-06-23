use e9clubshop;


-- Reinicia las tablas antes de crearlas 
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS Juguete;
DROP TABLE IF EXISTS Camiseta;
DROP TABLE IF EXISTS Poster;
DROP TABLE IF EXISTS Entrada;
DROP TABLE IF EXISTS Empleado;
DROP TABLE IF EXISTS Director;
DROP TABLE IF EXISTS Dependiente;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Factura;
DROP TABLE IF EXISTS Linea_venta;
DROP TABLE IF EXISTS Venta;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE Producto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio FLOAT NOT NULL,
    stock INT NOT NULL
);

-- Relacionado con Producto 
CREATE TABLE Juguete(
    id INT NOT NULL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    tamano VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

-- Relacionado con Producto
CREATE TABLE Camiseta(
    id INT NOT NULL PRIMARY KEY,
    talla INT NOT NULL,
    dorsal VARCHAR(50) NOT NULL,
    numero INT NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

-- Relacionado con Producto
CREATE TABLE Poster(
	id INT NOT NULL PRIMARY KEY,
    tamano VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

-- Relacionado con Producto
CREATE TABLE Entrada (
	id INT NOT NULL PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    numero_asiento VARCHAR(15) NOT NULL,
    partido VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

CREATE TABLE Empleado (
    identificador VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    sueldo FLOAT NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla DIRECTOR (relacionada con EMPLEADO)
CREATE TABLE Director (
    id VARCHAR(20) NOT NULL PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL DEFAULT 'DIRECTOR',
    FOREIGN KEY (id) REFERENCES Empleado(identificador)
);

-- Tabla DEPENDIENTE (relacionada con EMPLEADO)
CREATE TABLE Dependiente (
	id VARCHAR(20) NOT NULL PRIMARY KEY,
    sum_ventas FLOAT NOT NULL DEFAULT 0,
    FOREIGN KEY (id) REFERENCES Empleado(identificador)
);

CREATE TABLE Cliente (
    num_socio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Factura(
    codigo VARCHAR(50) NOT NULL PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    importe FLOAT NOT NULL
);

CREATE TABLE Linea_venta (
    venta VARCHAR(50) NOT NULL,
    producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario FLOAT NOT NULL,
    PRIMARY KEY (venta, producto),
    FOREIGN KEY (venta) REFERENCES Venta(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (producto) REFERENCES Producto(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Venta (
    codigo VARCHAR(50) NOT NULL PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    cliente INT NOT NULL,
    dependiente VARCHAR(20) NOT NULL,
    factura VARCHAR(50) NOT NULL,
    FOREIGN KEY (cliente) REFERENCES Cliente(num_socio) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dependiente) REFERENCES Empleado(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (factura) REFERENCES Factura(codigo) ON DELETE CASCADE ON UPDATE CASCADE
);
