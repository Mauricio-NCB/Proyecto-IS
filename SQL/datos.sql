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
DROP TABLE IF EXISTS Venta;
DROP TABLE IF EXISTS Envio;

CREATE TABLE Producto (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio FLOAT NOT NULL,
    stock INT NOT NULL
);

-- Relacionado con Producto 
CREATE TABLE Juguete(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    tamaño VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

-- Relacionado con Producto
CREATE TABLE Camiseta(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dorsal VARCHAR(50) NOT NULL,
    numero INT NOT NULL,
    talla INT NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

-- Relacionado con Producto
CREATE TABLE Poster(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tamano INT(10) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

--Relacionado con Producto
CREATE TABLE Entrada (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hora VARCHAR(10) NOT NULL,
	fecha DATE NOT NULL,
    ubicacion VARCHAR(20) NOT NULL,
    numero_asiento VARCHAR(5) NOT NULL,
    partido VARCHAR(20) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE
);

CREATE TABLE Empleado (
    identificador VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    sueldo FLOAT NOT NULL
);

-- Tabla DIRECTOR (relacionada con EMPLEADO)
CREATE TABLE Director (
    id VARCHAR(20) NOT NULL,
    cargo VARCHAR(50) DEFAULT 'DIRECTOR',
    FOREIGN KEY (id) REFERENCES Empleado(identificador)
);

-- Tabla DEPENDIENTE (relacionada con EMPLEADO)
CREATE TABLE Dependiente (
	id VARCHAR(20) PRIMARY KEY,
    num_ventas INT DEFAULT 0,
    FOREIGN KEY (id) REFERENCES Empleado(identificador)
);

CREATE TABLE Cliente (
    num_socio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    correo VARCHAR(255) UNIQUE
);

CREATE TABLE Factura(
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    fecha DATE NOT NULL,
    hora INT(10) NOT NULL,
    importe FLOAT NOT NULL  
);

CREATE TABLE Venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    precio FLOAT NOT NULL,
    cantidad INT NOT NULL,
    idProducto INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES producto(id) DELETE ON CASCADE UPDATE ON CASCADE
);

CREATE TABLE Envio (
	id VARCHAR(15) PRIMARY KEY,
    coste FLOAT NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_envio DATE NOT NULL,
    fecha_entrega DATE
);    